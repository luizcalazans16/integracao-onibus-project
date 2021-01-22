package br.com.dimed.busIntegration.domain.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "itinerary")
public class Itinerary implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty(value = "idlinha")
	@Column(name = "id")
	private String id;

	@JsonProperty(value = "nome")
	@Column(name = "name")
	private String name;

	@JsonProperty(value = "codigo")
	@Column(name = "code")
	private String code;

	@JsonIgnore
	@Column(name = "location")
	@OneToMany
	private Map<String, Location> locationList = new HashMap<>();

}
