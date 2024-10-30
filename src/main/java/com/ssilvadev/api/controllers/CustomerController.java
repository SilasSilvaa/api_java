package com.ssilvadev.api.controllers;

import com.ssilvadev.api.models.Customer;
import com.ssilvadev.api.repositories.CustomerRepository;
import dtos.NewCustomerRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomers()
    {
        return customerRepository.findAll();
    }


    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @GetMapping("{customerId}")
    public Customer getCustomerByID(@PathVariable("customerId") Integer id){
        return customerRepository.findById(id).orElseThrow();
    }

    @PutMapping("{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") Integer id,
                                   @RequestBody NewCustomerRequest request){
         var customer = customerRepository.findById(id).orElseThrow();
         customer.setName(request.name());
         customer.setEmail(request.email());
         customer.setAge(request.age());

         return customer;
    }
}
