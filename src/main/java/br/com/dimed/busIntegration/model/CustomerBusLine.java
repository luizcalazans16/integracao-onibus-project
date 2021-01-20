package br.com.dimed.busIntegration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_busLine")
public class CustomerBusLine implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idCliente", nullable = false)
	protected Long idCliente;

	@Id
	@Column(name = "idLinha", nullable = false)
	protected Long idLinha;

}
