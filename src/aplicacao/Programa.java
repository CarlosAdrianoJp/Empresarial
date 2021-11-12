package aplicacao;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;
import model.entities.Funcionario;

public class Programa {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		DepartamentoDao departamentoDao = DaoFactory.criarDepartamentoDao();
		int opcao;

		do {
			System.out.println();
			System.out.println(" ==== Systema de cadastro - DEPARTAMENTO - ===== \n"
					+ "  --- digite a opcao para continuar --- \n \n" + "1 - listar os departamentos existentes \n"
					+ "2 - cadastrar um novo departamento \n" + "3 - alterar umdepartamento existente \n"
					+ "4 - excluir um departamento \n" + "5 - listar os funcionarios de acordo com um departamento \n"
					+ "6 - sair");
			System.out.println();
			opcao = in.nextInt();

			switch (opcao) {
			case 1:
				System.out.println();
				System.out.println("---------- LISTA DOS DEPARTAMENTOS ----------");
				System.out.println();
				
				List<Departamento> lista = departamentoDao.encontreTudo();
				for (Departamento obj : lista) {
					System.out.println(obj);
				}
				break;
			case 2:
				System.out.println();
				System.out.println("---------- INSERIR DEPARTAMENTO ----------");
				System.out.println();
				System.out.println("digite o nome do departamento");
				String n = in.next();
				System.out.println();
				System.out.println("digite a descricao");
				String d = in.next();
				Departamento NovoDep = new Departamento(null, n, d);
				departamentoDao.inserir(NovoDep);
				System.out.println("Departamento inserido.. id = " + NovoDep.getId());
				break;
			case 3:
				System.out.println();
				System.out.println("---------- ATUALIZAR DEPARTAMENTO ----------");
				System.out.println();
				System.out.println("digite o id  do departamento que voce quer alterar");
				int i = in.nextInt();
				
				Departamento departamento = departamentoDao.encontrePeloId(i);
				
				System.out.println("digite o novo nome do departamento");
				String nome = in.next();
				System.out.println("digite a nova descricao");
				String descricao = in.next();
				
				departamento.setNome(nome);
				departamento.setDescricao(descricao);
				departamentoDao.atualizar(departamento);
				break;

			case 4:
				System.out.println("digite o id no qual vc quer excluir...");
				int tc = in.nextInt();
				departamentoDao.deletePeloId(tc);

				break;

			case 5:
				System.out.println("Digite o id pra lista");
				int id = in.nextInt();

				List<Funcionario> lista1 = departamentoDao.encontreFuncionariosPeloId(id);
				for (Funcionario obj : lista1) {
					System.out.println(obj);
				}
				break;
			case 6:

				break;

			default:
				System.out.println("opção invalida!!!");
			}
		} while (opcao != 6);
	}
}
