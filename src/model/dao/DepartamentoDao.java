package model.dao;

import java.util.List;

import model.entities.Departamento;
import model.entities.Funcionario;

public interface DepartamentoDao {
	
	void inserir(Departamento obj);
	void atualizar(Departamento obj);
	void deletePeloId(Integer id);
	Departamento encontrePeloId(Integer id);
	List<Departamento> encontreTudo();
	List<Funcionario> encontreFuncionariosPeloId(Integer id);
}
