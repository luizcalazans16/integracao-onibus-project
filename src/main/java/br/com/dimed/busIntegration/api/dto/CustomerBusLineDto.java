package br.com.dimed.busIntegration.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = CustomerBusLineDto.CustomerBusLineDtoBuilder.class)
public class CustomerBusLineDto {

	private Long id;
	
	private String customerCpf;
	
	private String busLineCode;
	
	@JsonPOJOBuilder(withPrefix = "")
	public static final class CustomerBusLineDtoBuilder {
		
	}
	
}
