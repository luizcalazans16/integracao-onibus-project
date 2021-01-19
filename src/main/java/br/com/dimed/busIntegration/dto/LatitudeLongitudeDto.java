package br.com.dimed.busIntegration.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LatitudeLongitudeDto {
	
	private String latitude;

	private String longitude;
}

