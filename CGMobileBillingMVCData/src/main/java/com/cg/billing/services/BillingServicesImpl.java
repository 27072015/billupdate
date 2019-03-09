package com.cg.billing.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.daoservices.BillDao;
import com.cg.billing.daoservices.CustomerDao;
import com.cg.billing.daoservices.PlanDao;
import com.cg.billing.daoservices.PostpaidAccountDao;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;
@Component("billingServices")
public class BillingServicesImpl implements BillingServices {
	@Autowired
	CustomerDao customerDao;
	@Autowired
   PlanDao planDao;
	@Autowired
   BillDao billDao;
	@Autowired
    PostpaidAccountDao postpaidAccountDao;
	
	private Customer customer;
	@Override
	public Plan getPlanAllDetails(int planId)throws PlanDetailsNotFoundException {
		Plan plan= planDao.findById(planId).orElseThrow(()->new PlanDetailsNotFoundException("Plan is not present="+planId));
			return plan;
	}

	@Override
	public Customer acceptCustomerDetails(Customer customer) {
		customer=customerDao.save(customer);
		return customer;
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
			PostpaidAccount postpaidAccount=new PostpaidAccount(planDao.findById(planID).orElseThrow(()->new PlanDetailsNotFoundException("Plan is not present "+planID)), customerDao.findById(customerID).orElseThrow(()-> new CustomerDetailsNotFoundException("Customer not Present with Id="+customerID)));
			postpaidAccountDao.save(postpaidAccount);
		return postpaidAccount.getMobileNo();
	}
@Override
	public Customer getCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		 customer=customerDao.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Customer not present with ID="+customerID));
		return customer;
	}

	@Override
	public List<Customer> getAllCustomerDetails() {
		return customerDao.findAll();
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		 customer =customerDao.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Customer not present with ID="+customerID));
		PostpaidAccount postpaidAccount=postpaidAccountDao.findById(mobileNo).orElseThrow(()-> new PostpaidAccountNotFoundException("Account not present with mobileNo="+mobileNo));
		return postpaidAccount;
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException {
		 customer =customerDao.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Customer not present with ID="+customerID));
		return null;//postpaidAccountDao.findAll(customerID);     //check this point
	}
	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException {
	      Bill bill=null;
		 ArrayList<Integer>billSetKey=new ArrayList<Integer>(getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).getBills().keySet());
		for(int i=0;i<billSetKey.size();i++) {
			if(getPostPaidAccountDetails(customerID, mobileNo).getBills().get(billSetKey.get(i)).getBillMonth().equalsIgnoreCase(billMonth))
				bill=billDao.findById(billSetKey.get(i)).orElseThrow(()-> new InvalidBillMonthException("Bill Month is not Correct"+billMonth));		 
		}
		return bill;
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		return new ArrayList<Bill>(getPostPaidAccountDetails(customerID, mobileNo).getBills().values());
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		PostpaidAccount postpaidAccount=new PostpaidAccount(mobileNo, getPlanAllDetails(planID), getCustomerDetails(customerID));
		postpaidAccountDao.save(postpaidAccount);	
		return true;
	}

	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)      
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		postpaidAccountDao.deleteById((long)mobileNo);
		return true;
	}

	@Override
	public boolean removeCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		customerDao.delete(getCustomerDetails(customerID)); 
		return true;
	}
	
	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		getCustomerDetails(customerID);
		PostpaidAccount postpaidAccount=postpaidAccountDao.findById(mobileNo).orElseThrow(()-> new PostpaidAccountNotFoundException("Account not present with mobileNo="+mobileNo));
		return postpaidAccount.getPlan();
	}

	@Override
	public List<Plan> getAllPlanDetails() {
		return planDao.findAll();
	}

	@Override
	public double generateMonthlyMobileBill(int customerID, long mobileNo, Bill bill)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			PlanDetailsNotFoundException {
		float internetdatausageamount=0;
		float localcallcost=0;
		float localsmscost=0;
		float stdcallcost=0;
		float stdsmscost=0;
		float stateGST=0;
		float  centralGST=0;
		float sum=0;
		Plan plan=getCustomerPostPaidAccountPlanDetails(customerID, mobileNo);
		PostpaidAccount postpaidAccount= getPostPaidAccountDetails(customerID, mobileNo);
		if(bill.getInternetDataUsageUnits()>plan.getFreeInternetDataUsageUnits())
			 internetdatausageamount=(bill.getInternetDataUsageUnits()-plan.getFreeInternetDataUsageUnits())*plan.getInternetDataUsageRate();
		if(bill.getNoOfLocalCalls()>plan.getFreeLocalCalls())
			localcallcost=(bill.getNoOfLocalCalls()-plan.getFreeLocalCalls())*plan.getLocalCallRate();
		if(bill.getNoOfLocalSMS()>plan.getFreeLocalSMS())
			localsmscost=(bill.getNoOfLocalSMS()-plan.getFreeLocalSMS()*plan.getLocalSMSRate());
		if(bill.getNoOfStdCalls()>plan.getFreeStdCalls())
			stdcallcost=(bill.getNoOfStdCalls()-plan.getFreeStdCalls())*plan.getStdCallRate();
		if(bill.getNoOfStdSMS()>plan.getFreeStdSMS())
			stdsmscost=(bill.getNoOfStdSMS()-plan.getFreeStdSMS());
		sum=internetdatausageamount+localcallcost+localsmscost+stdcallcost+stdsmscost;
		stateGST=(float) 0.25*sum;
		 centralGST=(float) 0.18*sum;
		return 0;
	}
}