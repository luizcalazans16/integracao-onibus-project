package br.com.dimed.busIntegration.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.busIntegration.dto.CustomerBusLineDto;
import br.com.dimed.busIntegration.mapper.CustomerMapper;
import br.com.dimed.busIntegration.service.CustomerBusLineService;

@RestController
@RequestMapping("/customer-busLine")
public class CustomerBusLineController {

	@Autowired
	private CustomerBusLineService customerBusLineService;
	
	@GetMapping("/{customerCpf}")
	public List<CustomerBusLineDto> findCustomerBusLineByCustomerCpf(@PathVariable final String customerCpf) {
		return customerBusLineService.findCustomerBusLineByCustomerCpf(customerCpf)
				.stream()
				.map(CustomerMapper::map)
				.collect(Collectors.toList());
	}
	
	@PostMapping("/{customerCpf}/{busLineCode}")
	public CustomerBusLineDto create(@PathVariable final String customerCpf, @PathVariable final String busLineCode) {
		return CustomerMapper.map(customerBusLineService.create(customerCpf, busLineCode));
	}
	
}
