package br.inatel.trabalho_dm110.api.supplier;

import java.io.Serializable;

public class SupplierTO implements Serializable {

	private static final long serialVersionUID = 224684304229394710L;

	private int cnpj;
	private String name;
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
		return "SupplierTO [cnpj=" + cnpj + ", name=" + name + ", email=" + email + ", cep=" + cep + ", date=" + date
				+ ", rate=" + rate + "]";
	}

}
