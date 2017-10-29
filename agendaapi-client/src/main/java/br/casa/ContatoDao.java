package br.casa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ContatoDao implements ContatoDaoInterface {
	
	private Connection con;

	private static final String SQL_BUSCA_TODOS = "SELECT * FROM CONTATO";

	private static final String SQL_INSERT = "insert into contato(nome, telefone,endereco,email,celular)values(?, ?, ?, ?,?)";
	public ContatoDao() {
		ConexaoDB conexao = ConexaoDB.getInstance();
		con = conexao.getConnection();
	}
	@Override
	public List<Contato> getTodos() {
		
		Connection con = ConexaoDB.getInstance().getConnection();
		
		List<Contato> lista = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(SQL_BUSCA_TODOS);
				ResultSet rs = ps.executeQuery()){
			
			while(rs.next()){
				Contato c = new Contato();
				c.setId(rs.getInt(1));
				c.setNome(rs.getString(2));
				c.setTelefone(rs.getString(3));
				c.setEndereco(rs.getString(4));
				c.setEmail(rs.getString(5));
				c.setCelular(rs.getString(6));
				lista.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	/* (non-Javadoc)
	 * @see br.casa.ContatoDaoInterface#insere(br.casa.Contato)
	 */
	@Override
	public Contato insere(Contato c) {
		
		try {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT);
			ps.setString(1, c.getNome());
			ps.setString(2, c.getTelefone());
			ps.setString(3, c.getEndereco());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getCelular());			
			
			ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
		
	
		
	}
	
	/* (non-Javadoc)
	 * @see br.casa.ContatoDaoInterface#atualiza(int, br.casa.Contato)
	 */
	@Override
	public Contato atualiza(int id, Contato c) {
		return c;
		
	}
	
	/* (non-Javadoc)
	 * @see br.casa.ContatoDaoInterface#exclui(int)
	 */
	@Override
	public void exclui(int id) {
		
	}
	
	
	

}
