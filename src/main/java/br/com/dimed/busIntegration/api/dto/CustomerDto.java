package br.com.dimed.busIntegration.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = CustomerDto.CustomerDtoBuilder.class)
public class CustomerDto {

	private String cpf;
	
	private String name;
	
	
	@JsonPOJOBuilder(withPrefix = "")
	public static final class CustomerDtoBuilder {
		
	}
}
