package br.com.dimed.busIntegration.model;

import java.io.Serializable;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class Itinerary implements Serializable {

	@JsonProperty(value = "idlinha")
	private String id;
	
	@JsonProperty(value = "nome")
	private String name;
	
	@JsonProperty(value = "codigo")
	private String code;

	private Double latitude;
	
	private Double longitude;


}
