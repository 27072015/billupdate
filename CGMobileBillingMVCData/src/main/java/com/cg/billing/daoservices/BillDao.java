package com.cg.billing.daoservices;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.billing.beans.Bill;

public interface BillDao extends JpaRepository<Bill, Integer> {
	
}
