package br.com.dimed.busIntegration.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder	
public class ItineraryDto {

	private Long id;
	
	private String nome;
	
	private String codigo;
	
	private Double longitude;
	
	private Double latitude;
}