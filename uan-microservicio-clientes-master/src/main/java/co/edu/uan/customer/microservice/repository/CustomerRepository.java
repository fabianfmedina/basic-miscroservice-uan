package co.edu.uan.customer.microservice.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.uan.customer.microservice.dto.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
