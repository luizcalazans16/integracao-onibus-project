package br.com.dimed.busIntegration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {

	@Id
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany
	private BusLine busLine;

}
