package br.com.dimed.busIntegration.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class BusLine {

	@Id
	private String id;
	
	@JsonProperty(value = "codigo")
	private String code;
	
	@JsonProperty(value = "nome")
	private String name;
}
