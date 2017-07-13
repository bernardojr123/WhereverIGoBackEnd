package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import connection.*;
import dominio.Pessoa;
import dominio.Usuario;

public class UsuarioDao {
		
	/*public static void main(String[] args) {
		UsuarioDao usu = new UsuarioDao();
		boolean pessoa = usu.verificarUsuario("bernardodems","1234568");

	}*/
	
	public UsuarioDao() {
		try {
			InicializarBanco ini = new InicializarBanco();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Pessoa getPessoa(String email){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String consulta = "SELECT * FROM whereverigo.usuario WHERE usuario.email = ?";
		try{	
			stmt = connection.prepareStatement(consulta);
			stmt.setString(1, email);
			resultSet = stmt.executeQuery();
			
			
			while (resultSet.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("id"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setSenha(resultSet.getString("senha"));
				
				Pessoa pessoa = new Pessoa();
				String consulta2 = "SELECT * FROM whereverigo.pessoa WHERE pessoa.id_usuario = ?";
				stmt = connection.prepareStatement(consulta2);
				stmt.setInt(1, usuario.getId());
				ResultSet resultSet2 = stmt.executeQuery();
				
				while (resultSet2.next()){
					pessoa.setId(resultSet2.getInt("id"));
					pessoa.setNome(resultSet2.getString("nome"));
					pessoa.setSexo(resultSet2.getString("sexo"));
					pessoa.setUsuario(usuario);
					pessoa.setDataNascimento(resultSet2.getDate("dataNascimento"));
					
					return pessoa;
				
				}
			}
			
		}catch (SQLException ex){
			return null;
		}
		finally{
			ConnectionFactory.closeConnection(connection, stmt, resultSet);
		}
		return null;
	}
	
	public Usuario getUsuario(String email){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String consulta = "SELECT * FROM whereverigo.usuario WHERE usuario.email = ?";
		try{	
			stmt = connection.prepareStatement(consulta);
			stmt.setString(1, email);
			resultSet = stmt.executeQuery();
			
			Usuario usuario = new Usuario();
			while (resultSet.next()){
				usuario.setId(resultSet.getInt("id"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setSenha(resultSet.getString("senha"));
				
			}
			return usuario;
			
		}catch (SQLException ex){
			return null;
		}
		finally{
			ConnectionFactory.closeConnection(connection, stmt, resultSet);
		}
	}
	
	public boolean existeUsuario(String email){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String consulta = "SELECT * FROM whereverigo.usuario WHERE usuario.email = ?";
		try{	
			stmt = connection.prepareStatement(consulta);
			stmt.setString(1, email);
			resultSet = stmt.executeQuery();
			
			
			if (resultSet.next()){
				return true;
			}
			
		}catch (SQLException ex){
			return false;
		}
		finally{
			ConnectionFactory.closeConnection(connection, stmt, resultSet);
		}
		return false;
	}
	
	public boolean verificarUsuario(String email,String senha){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String consulta = "SELECT * FROM whereverigo.usuario WHERE usuario.email = ? and usuario.senha = ?";
		try{	
			stmt = connection.prepareStatement(consulta);
			stmt.setString(1, email);
			stmt.setString(2, senha);
			resultSet = stmt.executeQuery();
			
			
			if (resultSet.next()){
				return true;
			}
			
		}catch (SQLException ex){
			return false;
		}
		finally{
			ConnectionFactory.closeConnection(connection, stmt, resultSet);
		}
		return false;
	}

   public int addUsuario(Pessoa pessoa){
	   Connection connection = ConnectionFactory.getConnection();
	   java.sql.PreparedStatement stmt = null;
		
	   try{
		   Usuario usuario = pessoa.getUsuario();
		   stmt = connection.prepareStatement("INSERT INTO whereverigo.usuario (email, senha)VALUES(?,?)");
		   stmt.setString(1, usuario.getEmail());
		   stmt.setString(2, usuario.getSenha());
			
		   stmt.executeUpdate();
		   
		   stmt = connection.prepareStatement("INSERT INTO whereverigo.pessoa (id_usuario, nome, dataNascimento, sexo) VALUES (?,?,?,?);");
		   Usuario p = getUsuario(usuario.getEmail());
		   int id = p.getId();
		   stmt.setInt(1, id);
		   stmt.setString(2, pessoa.getNome());
		   java.sql.Date dataSql = new java.sql.Date(pessoa.getDataNascimento().getTime());
		   stmt.setDate(3, dataSql );
		   stmt.setString(4, pessoa.getSexo());
		   
		   stmt.executeUpdate();
		   
		   
			
	   }catch (SQLException ex){
		   return 0;
			
	   }finally{
		   ConnectionFactory.closeConnection(connection, stmt);
	   }
	   return 1;
   }

   public int atualizarUsuario(Usuario usuario){
      	
	   return 0;
   }
}