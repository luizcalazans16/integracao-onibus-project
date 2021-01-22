package br.com.dimed.busIntegration.domain.model;

import javax.persistence.Column;
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

	public BusLine() {
	}

	@Id
	@Column(name = "id")
	private Long id;
	
	@JsonProperty(value = "codigo")
	@Column(name = "code")
	private String code;
	
	@JsonProperty(value = "nome")
	@Column(name = "name")
	private String name;
}
