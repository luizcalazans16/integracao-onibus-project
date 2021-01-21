package br.com.dimed.busIntegration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dimed.busIntegration.model.CustomerBusLine;

@Repository
public interface CustomerBusLineRepository  extends JpaRepository<CustomerBusLine, Long> {

	List<CustomerBusLine> findCustomerBusLineByCustomerCpf(final String customerCpf);
	
}
