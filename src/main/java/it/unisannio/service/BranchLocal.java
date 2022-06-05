package it.unisannio.service;

import it.unisannio.model.Customer;

import javax.ejb.Local;

@Local
public interface BranchLocal {
	public void createCustomer(String cf, String fn, String ln) throws Exception;
	public Customer getCustomer(String cf);

}
