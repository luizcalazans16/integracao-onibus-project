package br.com.dimed.busIntegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dimed.busIntegration.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

	Customer getCustomerByCpf(String customerCPF);
}
