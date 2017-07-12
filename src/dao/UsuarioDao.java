package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.*;
import dominio.Pessoa;
import dominio.Usuario;

public class UsuarioDao {
	
	public UsuarioDao() {
		try {
			InicializarBanco ini = new InicializarBanco();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			
			
			while (resultSet.next()){
		
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("id"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setSenha(resultSet.getString("senha"));
			   
				return usuario;
			}
			
		}catch (SQLException ex){
			return null;
		}
		finally{
			ConnectionFactory.closeConnection(connection, stmt, resultSet);
		}
		return null;
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
		   int id = getUsuario(usuario.getEmail()).getId();
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