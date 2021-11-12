package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entities.Departamento;
import model.entities.Funcionario;


public class DepartamentoDaoJDBC implements DepartamentoDao {

	private Connection conn;

	public DepartamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Departamento obj) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("INSERT INTO departamento ( Nome, Descricao)" + " VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setString(2, obj.getDescricao());

			int linhaAfetada = st.executeUpdate();

			if (linhaAfetada > 0) {
				System.out.println("departamento add com sucesso...");
				rs = st.getGeneratedKeys();

				if (rs.next()) {

					int id = rs.getInt(1);
					obj.setId(id);
				}
			} else {
				throw new DbException("erro no sistema nao conseguiu adicionar o departamento");
			}
		} catch (SQLException e) {
			throw new DbException("erro interno" + e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public void atualizar(Departamento obj) {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("UPDATE departamento " + "SET Nome = ?, Descricao = ? WHERE Id = ?");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getDescricao());
			// pegando o id
			st.setInt(3, obj.getId());

			int linhaAfetada = st.executeUpdate();

			if (linhaAfetada > 0) {
				System.out.println("Atualizado com sucesso");
			} else {
				throw new DbException("nao conseguiu atualizar");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deletePeloId(Integer id) {

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("Delete from departamento WHERE Id = ?");

			st.setInt(1, id);

			int linha = st.executeUpdate();

			if (linha == 0) {
				System.out.println("essa ID nao existe");
			} else {
				System.out.println("Deletado com sucesso");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Departamento encontrePeloId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * from departamento where id = ?");

			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				Departamento dep = instaciarDepartamento(rs);
				return dep;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Departamento> encontreTudo() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Departamento> lista = new ArrayList<>();

		try {

			st = conn.prepareStatement("SELECT * from departamento");

			rs = st.executeQuery();

			while (rs.next()) {

				Departamento dep = instaciarDepartamento(rs);
				lista.add(dep);
			}

			return lista;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

	}

	private Departamento instaciarDepartamento(ResultSet rs) throws SQLException {

		Departamento dep = new Departamento();
		dep.setId(rs.getInt("Id"));
		dep.setNome(rs.getString("Nome"));
		dep.setDescricao(rs.getString("Descricao"));
		return dep;
	}

	public List<Funcionario> encontreFuncionariosPeloId(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			st = conn.prepareStatement("SELECT f.Id, f.Nome, f.Telefone, f.dataNiver, f.IdDepartamento"
					+ " FROM funcionario as f inner join departamento as d"
					+ " on f.IdDepartamento = d.Id where f.IdDepartamento = ? ");

			st.setInt(1, id);
			rs = st.executeQuery();

			List<Funcionario> lista = new ArrayList<>();

			while (rs.next()) {

				Funcionario fun = new Funcionario();
				fun.setId(rs.getInt("Id"));
				fun.setNome(rs.getString("Nome"));
				fun.setTelefone(rs.getString("Telefone"));
				fun.setDataNiver(rs.getDate("dataNiver"));

				lista.add(fun);
			}
				return lista;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
