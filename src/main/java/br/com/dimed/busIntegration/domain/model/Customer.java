package br.com.dimed.busIntegration.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import br.com.dimed.busIntegration.exceptions.BusinessException;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {

	@Id
	@Column(name = "cpf")
	@NotNull
	private String cpf;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "ativo")
	private Boolean ativo;

	public void setCpf(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", "");
		if (cpf.length() != 11) {
			throw new BusinessException("O CPF deve conter 11 dígitos.");
		}
		validateCpf(cpf);
		this.cpf = cpf;
	}

	private void validateCpf(String cpf) {
		int somaCpf = 0;
		int peso = 10;
		for (int i = 0; i < 9; i++) {
			somaCpf += ((int) (cpf.charAt(i) - '0')) * peso;
			peso -= 1;
		}
		int digito = 11 - (somaCpf % 11);
		if (digito == 10 || digito == 11) {
			digito = 0;
		}
		if (((int) (cpf.charAt(9) - '0')) != digito) {
			throw new BusinessException("CPF inválido!");
		}
		somaCpf = 0;
		peso = 11;
		for (int i = 0; i < 10; i++) {
			somaCpf += ((int) (cpf.charAt(i) - '0')) * peso;
			peso -= 1;
		}
		digito = 11 - (somaCpf % 11);
		if (digito == 10 || digito == 11) {
			digito = 0;
		}
		if (((int) (cpf.charAt(10) - '0')) != digito) {
			throw new BusinessException("CPF inválido!");
		}
	}
}
