package com.cg.billing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.billing.beans.Customer;
@Controller
public class URIController {
	private Customer customer;
	@RequestMapping("/")
	public String getIndexPage() {
		return "indexPage";
	}
	@RequestMapping("/openpostpaidaccount")
	public String getOpenAccount() {
		return "openpostpaidaccount";
	}
	@RequestMapping("/registerCustomerPage")
	public String registerCustomer() {
		return "registercustomerpage";
	}
	@RequestMapping("/getCustomerDetails")
	public String getCustDetails() {
		return "customerdetailspage";
	}
	@RequestMapping("/getPostPaidBill")
	public String calculateBill() {
		return "postpaidbill";
	}
	@ModelAttribute
	public Customer getCustomer() {
		customer=new Customer();
		return customer;
	}
}
