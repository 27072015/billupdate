package com.cg.billing.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.billing.beans.PostpaidAccount;

public interface PostpaidAccountDao extends JpaRepository<PostpaidAccount, Long> {
	
}
