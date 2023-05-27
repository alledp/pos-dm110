package br.inatel.trabalho_dm110.supplier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPLIER", schema = "public")
public class Supplier implements Serializable {

	private static final long serialVersionUID = -3226154108429480413L;

	public Supplier() {
	} // default constructor

	public Supplier(int cnpj, String name, String email, int cep, String date, int rate) {
		super();
		this.cnpj = cnpj;
		this.name = name;
		this.email = email;
		this.cep = cep;
		this.date = date;
		this.rate = rate;
	}

	@Id
	private int cnpj;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL")
	private String email;
	private int cep;
	private String date;
	private int rate;

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Supplier [cnpj=" + cnpj + ", name=" + name + ", email=" + email + ", cep=" + cep + ", date=" + date
				+ ", rate=" + rate + "]";
	}
	

}
