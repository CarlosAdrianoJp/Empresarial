package model.dao;

import java.util.List;

import model.entities.Departamento;
import model.entities.Funcionario;

public interface FuncionarioDao {

	void inserir(Funcionario obj);
	void atualizar(Funcionario obj);
	void deletePeloId(Integer id);
	Funcionario encontrePeloId(Integer id);
	List<Funcionario> encontreTudo();
	List<Funcionario> encontrePeloDepartamento(Departamento departamento);
	
}
