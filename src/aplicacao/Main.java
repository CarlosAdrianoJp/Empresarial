package aplicacao;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;
import model.entities.Funcionario;

public class Main {

	public static void main(String[] args) {
		
		DepartamentoDao departamentoDao = DaoFactory.criarDepartamentoDao();
		Scanner in = new Scanner(System.in);
		
		System.out.println("--- Inserir departamento --- \n \n");
		
		/*
		Departamento NovoDep = new  Departamento(null, "Decoracao", "Responsavel pela decoracao nas areas festivas");
		departamentoDao.inserir(NovoDep);
		System.out.println("DEpartamento inserido.. id = " + NovoDep.getId());
		
		*/
		
		System.out.println("--- Atualizando Departamento ---- \n \n");
		Departamento departamento = departamentoDao.encontrePeloId(4);
		departamento.setNome("Decoracao");
		departamento.setDescricao("Decoracao de natal");
		departamentoDao.atualizar(departamento);
		
		/*
		System.out.println("--- Deletando Departamento ----");
		
		System.out.println("digite o id no qual vc quer excluir...");
		int tc = in.nextInt();
		departamentoDao.deletePeloId(tc);
		
		*/
		
		System.out.println(" \n \n --- encontre todos os Departamentos ----  \n \n");
		List<Departamento> lista = departamentoDao.encontreTudo();
		for(Departamento obj : lista) {
			System.out.println(obj);
		}
		
		System.out.println("--- Bonus Encontrando os funcionarios pelo id do departamento ----");
		System.out.println("Digite o id pra lista");
		int id = in.nextInt();
		
		List<Funcionario> lista1 = departamentoDao.encontreFuncionariosPeloId(id);
		for(Funcionario obj : lista1) {
			System.out.println(obj);
		}
		
	}
	
	
}
