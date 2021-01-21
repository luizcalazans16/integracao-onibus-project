package br.com.dimed.busIntegration.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dimed.busIntegration.exceptions.BusinessException;
import br.com.dimed.busIntegration.exceptions.ResourceNotFoundException;
import br.com.dimed.busIntegration.model.Customer;
import br.com.dimed.busIntegration.repository.CustomerRepository;
import br.com.dimed.busIntegration.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer getCustomerByCpf(String customerCPF) {
		return customerRepository.findCustomerByCpf(customerCPF)
				.orElseThrow(() -> new ResourceNotFoundException(Customer.class, customerCPF));
	}

	@Override
	public Customer create(Customer entity) {
		if (!existingCustomer(entity.getCpf())) {
			entity.setAtivo(true); 
			store(entity);
		}
		return entity;
	}


	
	@Override
	public Customer updateCustomer(String customerCPF, Customer customer) {
		Customer customerToBeUpdated = getCustomerByCpf(customerCPF);
		if (customerToBeUpdated == null || customerToBeUpdated.getAtivo()) {
			throw new BusinessException("Um cliente válido deve ser informado");
		}
		if (customer.getCpf() != null) {
			throw new BusinessException("O CPF não pode ser alterado.");
		}
		return store(customerToBeUpdated);
	}
	
	@Override
	public void inactivateCustomer(String customerCPF) {
		final Customer customer = getCustomerByCpf(customerCPF);
		customer.setAtivo(false);
		store(customer);
	}
	
	@Override
	public void activateCustomer(String customerCPF) {
		final Customer customer = getCustomerByCpf(customerCPF);
		customer.setAtivo(true);
		store(customer);
	}

	private boolean existingCustomer(String customerCPF) {
		Optional<Customer> existingCustomer = customerRepository.findCustomerByCpf(customerCPF);

		if (existingCustomer.isPresent()) {
			throw new BusinessException("CPF já cadastrado no sistema. Por favor, valide os dados e tente novamente.");
		} else {
			return false;
		}
	}
	
	private Customer store(Customer entity) {
		return customerRepository.save(entity);
	}
	
	
}
