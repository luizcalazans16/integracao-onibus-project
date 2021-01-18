package br.com.dimed.busIntegration.model;

import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class Itinerary {

	@JsonProperty(value = "idlinha")
	private String lineId;
	
	@JsonProperty(value = "nome")
	private String name;
	
	@JsonProperty(value = "codigo")
	private String code;

	private List<LatitudeLongitude> latitudeLongitude;

}
