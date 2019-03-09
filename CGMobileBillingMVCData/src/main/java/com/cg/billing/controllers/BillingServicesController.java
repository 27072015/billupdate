package com.cg.billing.controllers;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.billing.beans.Customer;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.services.BillingServices;
@Controller
public class BillingServicesController {
	@Autowired
BillingServices billingServices;
	@RequestMapping("/openPostpaidAccount")
	public ModelAndView registerAssociate(@RequestParam int planID,int customerID) throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
		
		long mobile=billingServices.openPostpaidMobileAccount(customerID, planID);
			return new ModelAndView("openpostpaidaccount", "mobile", mobile);	
}
	@RequestMapping("/registerCustomer")
	public ModelAndView registerCustomer(@ModelAttribute Customer customer) {
	 customer=billingServices.acceptCustomerDetails(customer);
	return new ModelAndView("registercustomerpage","customer",customer);
	}
	@RequestMapping("/customerdetails")
	public ModelAndView getCustomer(@RequestParam int customerID) throws CustomerDetailsNotFoundException {
		Customer customer=billingServices.getCustomerDetails(customerID);
		return new ModelAndView("customerdetailspage","customer",customer);
	}
}