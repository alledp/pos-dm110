package br.inatel.trabalho_dm110.audit.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUDITTABLE", schema = "public")
public class Audit implements Serializable {

	private static final long serialVersionUID = -8110964134338109840L;

	public Audit() {
	} // default constructor
	
	public Audit(int id, int cnpj, String operation, String lastModified) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.operation = operation;
		this.lastModified = lastModified;
	}

	@Id
	private int id;

	@Column(name = "CNPJ")
	private int cnpj;

	@Column(name = "OPERATION")
	private String operation;
	private String lastModified;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getTimeStamp() {
		return lastModified;
	}

	public void setTimeStamp(String lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "Audit [id=" + id + ", cnpj=" + cnpj + ", operation=" + operation + ", timeStamp=" + lastModified + "]";
	}
	

}
