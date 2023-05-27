package br.inatel.trabalho_dm110.api.supplier;

import java.io.Serializable;

public class AuditTO implements Serializable {

	private static final long serialVersionUID = 8164401929456307751L;

	private int cnpj;
	private String operation;
	
	
	public AuditTO(int cnpj, String operation) {
		super();
		this.cnpj = cnpj;
		this.operation = operation;
	}
	
	public int getCnpj() {
		return cnpj;
	}
	public String getOperation() {
		return operation;
	}
	@Override
	public String toString() {
		return "AuditTO [cnpj=" + cnpj + ", operation=" + operation + "]";
	}
	

}
