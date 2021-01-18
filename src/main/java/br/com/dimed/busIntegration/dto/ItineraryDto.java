package br.com.dimed.busIntegration.dto;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ItineraryDto {

	private String idLinha;
	
	private String nome;
	
	private String codigo;
	
	private List<LatitudeLongitudeDto> latitudeLongitude;
}