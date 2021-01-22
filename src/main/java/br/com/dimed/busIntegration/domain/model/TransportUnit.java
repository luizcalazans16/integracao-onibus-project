package br.com.dimed.busIntegration.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class TransportUnit {
	
	@Id
	@JsonProperty(value = "codigo")
	private String codigoUnidade;
	
	@JsonProperty(value = "latitude")
	private Double latitude;

	@JsonProperty(value = "longitude")
	private Double longitude;
}
