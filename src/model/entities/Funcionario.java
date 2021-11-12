package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Funcionario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String telefone;
	private Date dataNiver;

	private Departamento departamento;
	
	public Funcionario() {
	}

	public Funcionario(Integer id, String nome, String telefone, Date dataNiver, Departamento departamento) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.dataNiver = dataNiver;
		this.departamento = departamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNiver() {
		return dataNiver;
	}

	public void setDataNiver(Date dataNiver) {
		this.dataNiver = dataNiver;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", dataNiver=" + dataNiver
				+ ", departamento=" + departamento + "]";
	}

	
	
	

}
