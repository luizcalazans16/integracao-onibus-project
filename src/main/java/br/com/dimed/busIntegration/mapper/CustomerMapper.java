package br.com.dimed.busIntegration.mapper;

import br.com.dimed.busIntegration.dto.CustomerBusLineDto;
import br.com.dimed.busIntegration.dto.CustomerDto;
import br.com.dimed.busIntegration.model.Customer;
import br.com.dimed.busIntegration.model.CustomerBusLine;
import lombok.experimental.UtilityClass;

@UtilityClass

public class CustomerMapper {

	public static CustomerDto map(Customer entity) {
		return (entity == null) ? null : CustomerDto.builder().name(entity.getName()).cpf(entity.getCpf()).build();
	}

	public static Customer map(CustomerDto dto) {
		if (dto == null) {
			return null;
		}

		Customer entity = new Customer();

		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());

		return entity;
	}

	public static CustomerBusLineDto map(CustomerBusLine entity) {
		return (entity == null) ? null :
			CustomerBusLineDto.builder()
			.id(entity.getId())
			.customerCpf(entity.getCustomerCpf())
			.busLineCode(entity.getBusLineCode())
			.build();
	}
}
