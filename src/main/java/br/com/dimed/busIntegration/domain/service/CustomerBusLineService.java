package br.com.dimed.busIntegration.domain.service;

import java.util.List;

import br.com.dimed.busIntegration.domain.model.CustomerBusLine;

public interface CustomerBusLineService {

	CustomerBusLine create(String customerCPF, String busLineCode);

	List<CustomerBusLine> findCustomerBusLineByCustomerCpf(final String customerCpf);

}
