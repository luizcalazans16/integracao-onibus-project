package br.com.dimed.busIntegration.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = BusLineDto.BusLineDtoBuilder.class)
public class BusLineDto {

	private String id;

	private String codigo;

	private String nome;

	@JsonPOJOBuilder(withPrefix = "")
	public static final class BusLineDtoBuilder {

	}
}
