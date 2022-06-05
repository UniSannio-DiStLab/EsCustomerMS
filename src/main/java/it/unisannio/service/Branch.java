package it.unisannio.service;


import it.unisannio.model.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class Branch
 */
@Stateless
public class Branch implements BranchLocal {
	
	@PersistenceContext
	EntityManager em;

	
    public Branch() {
    
    }


    public void createCustomer(String cf, String fn, String ln) throws Exception {
    	Customer c = new Customer(cf, fn, ln);
    	em.persist(c);
    }
    
    public Customer getCustomer(String cf) {
    	return em.find(Customer.class, cf);
    }
}
