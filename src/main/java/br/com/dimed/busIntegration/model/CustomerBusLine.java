package br.com.dimed.busIntegration.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class CustomerBusLine {
	
    @Id
    @Column(name = "idCliente", nullable = false)
    protected Long idCliente;

    @Id
    @Column(name = "idLinha", nullable = false)
    protected Long idLinha;

}

