package br.com.dimed.busIntegration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dimed.busIntegration.exceptions.BusinessException;
import br.com.dimed.busIntegration.model.Customer;
import br.com.dimed.busIntegration.repository.CustomerRepository;
import br.com.dimed.busIntegration.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer getCustomerByCPF(String customerCPF) {
		Customer customer = customerRepository.getCustomerByCpf(customerCPF);

		if (customer == null) {
			return customer;
		} else {
			throw new BusinessException("Cliente não encontrado");
		}
	}

	@Override
	public Customer create(Customer entity) {
		Customer customer = new Customer();
		if (!existingCustomer(entity.getCpf())) {
			customer = customerRepository.save(entity);
		}
		return customer;
	}
	
	@Override
	public Customer updateCustomer(String customerCPF, Customer customer) {
		Customer customerToBeUpdated = customerRepository.getCustomerByCpf(customerCPF);
		if (customerToBeUpdated == null || customerToBeUpdated.getAtivo()) {
			throw new BusinessException("Um cliente válido deve ser informado");
		}
		if (customer.getCpf() != null) {
			throw new BusinessException("O CPF não pode ser alterado.");
		}
		return customerRepository.save(customerToBeUpdated);
	}
	
	@Override
	public void inactivateCustomer(String customerCPF) {
		final Customer customer = getCustomerByCPF(customerCPF);
		customer.setAtivo(false);
	}
	
	@Override
	public void activateCustomer(String customerCPF) {
		final Customer customer = getCustomerByCPF(customerCPF);
		customer.setAtivo(true);
	}

	private boolean existingCustomer(String customerCPF) {
		Customer existingCustomer = customerRepository.getCustomerByCpf(customerCPF);

		if (existingCustomer != null)
			throw new BusinessException("CPF já cadastrado no sistema. Por favor, valide os dados e tente novamente.");
		return false;
	}
	
	
}
