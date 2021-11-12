package model.dao;

import db.DB;
import model.dao.impl.DepartamentoDaoJDBC;
import model.dao.impl.FuncionarioDaoJDBC;

public class DaoFactory {
	
	public static FuncionarioDao criarFuncionarioDao() {
		return new FuncionarioDaoJDBC(DB.getConnection());
	}
	
	public static DepartamentoDao criarDepartamentoDao() {
		return new DepartamentoDaoJDBC(DB.getConnection());
	}
	

}
