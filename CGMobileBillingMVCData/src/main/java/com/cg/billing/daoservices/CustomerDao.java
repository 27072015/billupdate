package com.cg.billing.daoservices;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.billing.beans.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
	
}
