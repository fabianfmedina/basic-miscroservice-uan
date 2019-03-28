package co.edu.uan.customer.microservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uan.customer.microservice.dto.Customer;
import co.edu.uan.customer.microservice.exception.ResourceNotFoundException;
import co.edu.uan.customer.microservice.repository.CustomerRepository;
import co.edu.uan.customer.microservice.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	@Override
	public Customer create(Customer customer) {
		return repository.save(customer);
	}
	
	@Override
	public Iterable<Customer> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Customer findById(int id) throws ResourceNotFoundException {
		if (repository.findById(id).isPresent()) {
			return repository.findById(id).get();
		} else {
			throw new ResourceNotFoundException("Customer", "id", Integer.toString(id));
		}
	}
	
	@Override
	public Customer update(Customer customer) throws ResourceNotFoundException {
		if (repository.findById(customer.getId()).isPresent()) {
			return repository.save(customer);
		}
		throw new ResourceNotFoundException("Customer", "id", Integer.toString(customer.getId()));
	}
	
	@Override
	public void deleteById(int id) throws ResourceNotFoundException {
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Customer", "id", Integer.toString(id));
		}
	}	
	
	@Override
	public void delete(Customer customer) throws ResourceNotFoundException {
		if (repository.findById(customer.getId()).isPresent()) {
			repository.delete(customer);
		}
		throw new ResourceNotFoundException("Customer", "id", Integer.toString(customer.getId()));
	}
}
