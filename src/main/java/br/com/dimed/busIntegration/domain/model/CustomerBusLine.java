package br.com.dimed.busIntegration.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customer_bus_line")
@Data
public class CustomerBusLine implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "customer_cpf", nullable = false)
	protected String customerCpf;
	
	@JoinColumn(name = "bus_line_code", nullable = false)
	protected String busLineCode;

}
