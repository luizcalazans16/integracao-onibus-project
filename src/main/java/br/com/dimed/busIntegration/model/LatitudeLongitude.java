package br.com.dimed.busIntegration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LatitudeLongitude {

	@JsonProperty(value = "lat")
	private String latitude;

	@JsonProperty(value = "lng")
	private String longitude;
}
