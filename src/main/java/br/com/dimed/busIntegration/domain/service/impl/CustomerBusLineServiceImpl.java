package br.com.dimed.busIntegration.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dimed.busIntegration.domain.model.BusLine;
import br.com.dimed.busIntegration.domain.model.Customer;
import br.com.dimed.busIntegration.domain.model.CustomerBusLine;
import br.com.dimed.busIntegration.domain.repository.CustomerBusLineRepository;
import br.com.dimed.busIntegration.domain.service.BusLineService;
import br.com.dimed.busIntegration.domain.service.CustomerBusLineService;
import br.com.dimed.busIntegration.domain.service.CustomerService;
import br.com.dimed.busIntegration.exceptions.BusinessException;

@Service
public class CustomerBusLineServiceImpl implements CustomerBusLineService {

	@Autowired
	private CustomerBusLineRepository customerBusLineRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BusLineService busLineService;
	
	@Override
	public List<CustomerBusLine> findCustomerBusLineByCustomerCpf(final String customerCpf) {
		return customerBusLineRepository.findCustomerBusLineByCustomerCpf(customerCpf);
	}
	
	
	@Override
	public CustomerBusLine create(String customerCPF, String busLineCode) {
		validateData(customerCPF, busLineCode);
		
		CustomerBusLine entity = new CustomerBusLine();
		entity.setCustomerCpf(customerCPF);
		entity.setBusLineCode(busLineCode);
		
		return customerBusLineRepository.save(entity);
	
	}


	private void validateData(String customerCPF, String busLineCode) {
		Customer customer = customerService.getCustomerByCpf(customerCPF);
		if (customer == null || !customer.getAtivo()) {
			throw new BusinessException("Cliente não encontrado ou inativo.");
		}

		BusLine busLine = busLineService.findBusLineByCode(busLineCode);
		if (busLine == null) {
			throw new BusinessException("Linha não encontrada.");
		}
	}
}
