package model.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.ConnectionFactory;
import model.dominio.Local;



public class LocalDao {
	
	public static void main(String[] args) {
		LocalDao local = new LocalDao();
		List<Local> lista = local.getLocais();
		System.out.println(lista);
	}
	
	public ArrayList<Local> getLocais() {
		ArrayList<Local> locais = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String consulta = "select * from whereverigo.cidade;";
		try{	
			stmt = connection.prepareStatement(consulta);
			resultSet = stmt.executeQuery();
			
			
			while (resultSet.next()){
				Local local = new Local();
				local.setCidade(resultSet.getString("nome"));
				local.setId(resultSet.getInt("id"));
				local.setDescricao(resultSet.getString("descricao"));
				local.setEstadoPais(resultSet.getString("estado_pais"));
				local.setStrImagem(resultSet.getString("foto"));
				locais.add(local);
			}
			return locais;
		}catch (SQLException ex){
			return null;
		}
		finally{
			ConnectionFactory.closeConnection(connection, stmt, resultSet);
		}
	}
}
