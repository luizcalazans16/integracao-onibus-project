package br.com.dimed.busIntegration.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class BusLine {

	public BusLine(Long id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}

	@Id
	private Long id;
	
	@JsonProperty(value = "codigo")
	private String code;
	
	@JsonProperty(value = "nome")
	private String name;
}
