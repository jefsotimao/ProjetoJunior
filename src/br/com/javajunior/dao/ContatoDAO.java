package br.com.javajunior.dao;

public class ContatoDAO {
  
  package br.com.javajunior.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.javajunior.domain.Contato;
import br.com.javajunior.factory.ConexaoFactory;



public class ContatoDAO {
	
	
	// INSERT
	public void salvar(Contato c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO agenda ");
		sql.append("(nome,email,observacao,dataNascimento) ");
		sql.append("VALUES (?,?,?,?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, c.getNome());
		comando.setString(2, c.getEmail());
		comando.setString(3, c.getObservacao());
		comando.setDate(4, new Date(c.getDataNascimento().getTimeInMillis()));
		comando.executeUpdate();
		comando.close();
	}

	// DELETE
	public void excluir(Contato c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM agenda ");
		sql.append("WHERE id=?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, c.getId());
		comando.executeUpdate();
		comando.close();
	}

	// UPDATE
	public void editar(Contato c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE agenda ");
		sql.append("SET nome=?, email=?, observacao=?, dataNascimento=? ");
		sql.append("WHERE id=?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1,c.getNome());
		comando.setString(2,c.getEmail());
		comando.setString(3,c.getObservacao());
		comando.setDate(4, new Date(c.getDataNascimento().getTimeInMillis()));
		comando.setLong(5,c.getId());
		comando.executeUpdate();
		comando.close();
	}

	// SELECT
	public ArrayList<Contato> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM agenda ");
		sql.append("ORDER BY nome");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Contato> lista = new ArrayList<Contato>();

		while (resultado.next()) {
			Contato c = new Contato();
			c.setId(resultado.getLong("id"));
			c.setEmail(resultado.getString("email"));
			c.setObservacao(resultado.getString("observacao"));
			
			Calendar data =  Calendar.getInstance();
			data.setTime(resultado.getDate("dataNascimento"));
			c.setDataNascimento(data);
			
			lista.add(c);

		}
		return lista;

}
}	


}
