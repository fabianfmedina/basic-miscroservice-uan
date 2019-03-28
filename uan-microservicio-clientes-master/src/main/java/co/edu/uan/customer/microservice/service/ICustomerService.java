package co.edu.uan.customer.microservice.service;

import co.edu.uan.customer.microservice.dto.Customer;
import co.edu.uan.customer.microservice.exception.ResourceNotFoundException;

public interface ICustomerService {
	
	Customer create(Customer customer);
	
	Customer findById(int id) throws ResourceNotFoundException;
	
	Iterable<Customer> findAll();
	
	Customer update(Customer user) throws ResourceNotFoundException;
	
	void delete(Customer customer) throws ResourceNotFoundException;
	
	void deleteById(int id) throws ResourceNotFoundException;
}
