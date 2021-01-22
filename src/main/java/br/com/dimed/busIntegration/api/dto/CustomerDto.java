package br.com.dimed.busIntegration.api.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = CustomerDto.CustomerDtoBuilder.class)
public class CustomerDto {

	@NotNull(message = "CPF deve ser informado")
	private String cpf;
	
	@NotNull(message = "O nome deve ser informado")
	private String name;
	
	
	@JsonPOJOBuilder(withPrefix = "")
	public static final class CustomerDtoBuilder {
		
	}
}
