package br.com.dimed.busIntegration.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ItineraryDto.ItineraryDtoBuilder.class)
public class ItineraryDto {

	@JsonProperty(value = "idlinha")
	private String id;
	
	@JsonProperty(value = "nome")
	private String nome;
	
	@JsonProperty(value = "codigo")
	private String codigo;
	
    @JsonIgnore
    private Map<String, LocationDto> locationList = new HashMap<>();

	
	@JsonPOJOBuilder(withPrefix = "")
	public static final class ItineraryDtoBuilder {
		
	}
}