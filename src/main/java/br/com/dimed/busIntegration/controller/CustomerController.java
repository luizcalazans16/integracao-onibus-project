package br.com.dimed.busIntegration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.busIntegration.dto.CustomerDto;
import br.com.dimed.busIntegration.mapper.CustomerMapper;
import br.com.dimed.busIntegration.model.Customer;
import br.com.dimed.busIntegration.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/{customerCPF}")
	public CustomerDto getCustomerByCpf(@PathVariable final String customerCPF) {
		log.info("Buscando cliente pelo CPF: [{}]", customerCPF);
		return CustomerMapper.map(customerService.getCustomerByCPF(customerCPF));

	}

	@PostMapping
	public CustomerDto create(@RequestBody CustomerDto customerDto) {
		log.info("Iniciando processo de cadastro de cliente com os seguintes parâmetros: CPF: [{}], Nome: [{}]",
				customerDto.getCpf().length(), customerDto.getName());

		Customer entity = CustomerMapper.map(customerDto);
		entity = customerService.create(entity);
		return CustomerMapper.map(entity);

	}
	
	@PutMapping("/{customerCPF")
	public void activateCustomer(@PathVariable String customerCPF) {
		log.info("Iniciando processo de ativação do usuário do CPF: [{}]", customerCPF);
		customerService.activateCustomer(customerCPF);
	}
	
	@DeleteMapping("/{customerCPF}")
	public void inactivateCustomer(@PathVariable String customerCPF) {
		log.info("Iniciando o processo de inativação do usuário do CPF: [{}]", customerCPF);
		customerService.inactivateCustomer(customerCPF);
	}
}
