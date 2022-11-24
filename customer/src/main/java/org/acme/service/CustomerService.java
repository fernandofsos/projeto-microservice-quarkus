package org.acme.service;

import org.acme.dto.CustomerDTO;
import org.acme.entity.CustomerEntity;
import org.acme.repository.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public List<CustomerDTO> findAllCustomer() {
        List<CustomerDTO> customers = new ArrayList<>();

        customerRepository.findAll().stream().forEach(item -> {
            customers.add(mapCustomerEntityToDto(item));
        });
        return customers;
    }

    public void createNewCustomer(CustomerDTO customerDTO) {

        customerRepository.persist(mapDtoToCustomerEntity(customerDTO));

    }

    public void changeCustomer(Long id, CustomerDTO customerDTO){
        CustomerEntity customer = customerRepository.findById(id);

        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setAge(customerDTO.getAge());

        customerRepository.persist(customer);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    private CustomerDTO mapCustomerEntityToDto(CustomerEntity customerEntity) {

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setName(customerEntity.getName());
        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setPhone(customerEntity.getPhone());
        customerDTO.setAge(customerEntity.getAge());

        return customerDTO;

    }

    private CustomerEntity mapDtoToCustomerEntity(CustomerDTO customerDTO) {

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setName(customerDTO.getName());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setAge(customerDTO.getAge());

        return customerEntity;

    }


}
