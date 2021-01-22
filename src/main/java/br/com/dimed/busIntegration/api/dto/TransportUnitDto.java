package br.com.dimed.busIntegration.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = TransportUnitDto.TransportUnitDtoBuilder.class)
public class TransportUnitDto {
	
	@JsonProperty(value = "codigo")
	private String codigoUnidade;
	
	@JsonProperty(value = "latitude")
	private Double latitude;

	@JsonProperty(value = "longitude")
	private Double longitude;
		
	@JsonPOJOBuilder(withPrefix = "")
	public static final class TransportUnitDtoBuilder {
		
	}
}

