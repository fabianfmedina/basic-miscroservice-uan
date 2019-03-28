package co.edu.uan.customer.microservice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.uan.customer.microservice.dto.Customer;
import co.edu.uan.customer.microservice.exception.ResourceNotFoundException;
import co.edu.uan.customer.microservice.service.ICustomerService;

@RestController
@RequestMapping({ "/" })
@CrossOrigin(origins = "*")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping("/")
    public String index() {
        return "Bienvenido al microservicio de gestión de clientes!";
    }
	
	@GetMapping(value = "/health")
	public ResponseEntity<String> health() {
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Customer> create(@RequestBody Customer customer) {
		return new ResponseEntity<>(customerService.create(customer), HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<Iterable<Customer>> findAll() {
		return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/findById")
	public ResponseEntity<Customer> findOne(@RequestParam int id) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> update(@RequestBody Customer customer) throws ResourceNotFoundException {
		return new ResponseEntity<>(customerService.update(customer), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Customer customer) throws ResourceNotFoundException {
		customerService.delete(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById")
	public ResponseEntity<Object> delete(@RequestParam int id) throws ResourceNotFoundException {
		customerService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
