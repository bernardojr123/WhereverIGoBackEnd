package com.tutorialspoint;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {
	
	
	
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

   public int addUsuario(Usuario usuario){
	   Connection connection = ConnectionFactory.getConnection();
	   java.sql.PreparedStatement stmt = null;
		
	   try{
		   stmt = connection.prepareStatement("INSERT INTO usuario (email, senha)VALUES(?,?)");
		   stmt.setString(1, usuario.getEmail());
		   stmt.setString(2, usuario.getSenha());
			
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