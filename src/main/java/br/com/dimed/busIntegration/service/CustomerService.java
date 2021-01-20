package br.com.dimed.busIntegration.service;

import br.com.dimed.busIntegration.model.Customer;

public interface CustomerService {

	
	Customer create(Customer entity);

	Customer getCustomerByCpf(String customerCPF);

	Customer updateCustomer(String customerCPF, Customer customer);

	void inactivateCustomer(String customerCPF);

	void activateCustomer(String customerCPF);
	
}
	