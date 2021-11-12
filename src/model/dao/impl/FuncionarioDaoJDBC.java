package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.FuncionarioDao;
import model.entities.Departamento;
import model.entities.Funcionario;

public class FuncionarioDaoJDBC implements FuncionarioDao {

	private Connection conn;

	public FuncionarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Funcionario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO funcionario " + "(Nome, Telefone, dataNiver, IdDepartamento) "
					+ "VALUES " + "(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getTelefone());
			st.setDate(3, new java.sql.Date(obj.getDataNiver().getTime()));
			st.setInt(4, obj.getDepartamento().getId());

			int linhasAfetadas = st.executeUpdate();

			// se esse if der certo quer dizer que ele add
			// e que o mysql add umid que nao sei qual e
			if (linhasAfetadas > 0) {
				// ele pega u result set e pega o id que foi criado automaticamente e coloca na
				// rs
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					// vale lembrar que esse 1 referese ao resut set que pegou o id
					// nao e o 1 que adiciona um novo vendedor
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("erro no sistema nao conseguiu adicionar o Funcionario.");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void atualizar(Funcionario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE funcionario SET Nome = ?, Telefone = ?, dataNiver = ?, IdDepartamento = ? "
							+ "WHERE Id = ?");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getTelefone());
			st.setDate(3, new java.sql.Date(obj.getDataNiver().getTime()));
			st.setInt(4, obj.getDepartamento().getId());

			st.setInt(5, obj.getId());

			int linhasAfetadas = st.executeUpdate();
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
			st = conn.prepareStatement("DELETE FROM funcionario WHERE Id = ?");

			st.setInt(1, id);

			int linha = st.executeUpdate();

			if (linha == 0) {
				throw new DbException("erro id nao existe");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Funcionario encontrePeloId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT *, d.Nome as DepNome FROM funcionario as f inner join departamento as d on f.IdDepartamento = d.Id where f.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Departamento dep = instaciarDepartamento(rs);

				Funcionario fun = instanciarFuncionario(rs, dep);
				return fun;
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
	public List<Funcionario> encontreTudo() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT *, d.Nome as DepNome FROM funcionario as f inner join departamento as d on f.IdDepartamento = d.Id order by f.Nome");

			rs = st.executeQuery();

			List<Funcionario> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();

			while (rs.next()) {

				Departamento dep = map.get(rs.getInt("IdDepartamento"));

				if (dep == null) {
					dep = instaciarDepartamento(rs);
					map.put(rs.getInt("IdDepartamento"), dep);
				}

				Funcionario fun = instanciarFuncionario(rs, dep);
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

	@Override
	public List<Funcionario> encontrePeloDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT *, d.Nome as DepNome FROM funcionario as f inner join departamento as d on f.IdDepartamento = d.Id where d.Id = ?");

			st.setInt(1, departamento.getId());
			rs = st.executeQuery();

			List<Funcionario> lista = new ArrayList<>();

			Map<Integer, Departamento> map = new HashMap<>();

			while (rs.next()) {
				Departamento dep = map.get(rs.getInt("Id"));

				if (dep == null) {
					dep = instaciarDepartamento(rs);
					map.put(rs.getInt("Id"), dep);
				}

				Funcionario fun = instanciarFuncionario(rs, dep);
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

	private Funcionario instanciarFuncionario(ResultSet rs, Departamento dep) throws SQLException {

		Funcionario obj = new Funcionario();

		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Nome"));
		obj.setTelefone(rs.getString("Telefone"));
		obj.setDataNiver(rs.getDate("dataNiver"));
		obj.setDepartamento(dep);

		return obj;
	}

	private Departamento instaciarDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("IdDepartamento"));
		dep.setNome(rs.getString("DepNome"));
		dep.setDescricao(rs.getString("Descricao"));
		// verificar se esta correto depois
		return dep;
	}

	
}
