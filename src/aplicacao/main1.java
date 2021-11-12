package aplicacao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.FuncionarioDao;
import model.entities.Departamento;
import model.entities.Funcionario;
import model.entities.Vendedor;

public class main1 {

	public static void main(String[] args) {
		FuncionarioDao funcionarioDao = DaoFactory.criarFuncionarioDao();
		
		Scanner in = new Scanner(System.in);
		
		/*
		System.out.println();
		System.out.println("inserir funcionario");
		System.out.println();
		
		Funcionario novoFuncionario = new Funcionario(null, "arimateia", "83987456321", new Date(), new Departamento(2, null, null));
		funcionarioDao.inserir(novoFuncionario);
		System.out.println("inserido.. id do funcionario é = " + novoFuncionario.getId());


		System.out.println();
		System.out.println("localizar  funcionario pelo id");
		System.out.println();

		Funcionario funcionario = funcionarioDao.encontrePeloId(3);
		System.out.println(funcionario);
	
		
		System.out.println();
		System.out.println("atualizar funcionario");
		System.out.println();
		
		Funcionario funcionario = funcionarioDao.encontrePeloId(3);
		funcionario.setNome("aaaaaa");
		funcionario.setTelefone("1111111");
		funcionarioDao.atualizar(funcionario);
		System.out.println("deu certo verifica o workbench");
	}



		
		
		System.out.println();
		System.out.println("deletar funcionario");
		System.out.println();
		
		
		System.out.println("Entre com o id do vendedor para excluir");
		int id = in.nextInt();
		funcionarioDao.deletePeloId(id);
		System.out.println("Deletado com sucesso");

	}
	
	
	
	
		
		
		List<Funcionario> lista = funcionarioDao.encontreTudo();
		for (Funcionario f : lista) {
			System.out.println(f);
		}
		
		
		*/
		
		
		Departamento depart = new Departamento(2, null, null);
		List<Funcionario> lista = funcionarioDao.encontrePeloDepartamento(depart);
		for (Funcionario obj : lista) {
			System.out.println(obj);
		}
		
	}
}
