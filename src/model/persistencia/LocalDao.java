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
	
	private String where;
	
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
	
	public ArrayList<Local> getLocaisPorTag(String[] txtLugares) {
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
		where = "where ";
		for (int i = 0; i < txtLugares.length; i++) {
			if(i < txtLugares.length -1) {
				where = where + "tag.nometag = ? or ";
			}
			else {
				where = where + "tag.nometag = ? ";
			}
		}
		String consulta = "select * from whereverigo.cidade where id in (\r\n" + 
				"SELECT distinct idlugar FROM whereverigo.cidade natural join whereverigo.taglugar natural join whereverigo.tag " + where + ");";
		try{	
			stmt = connection.prepareStatement(consulta);
			for (int i = 1; i <= txtLugares.length; i++) {
				stmt.setString(i, txtLugares[i-1]);
			}
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
	
	public ArrayList<Local> getUltimosLugares(int id) {
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
		String consulta = "SELECT distinct(cidade.id),nome, descricao, estado_pais,foto FROM whereverigo.avaliacao inner join whereverigo.cidade on avaliacao.idlugar=cidade.id where id_usuario = ? order by dataAvaliacao desc;";
		try{	
			stmt = connection.prepareStatement(consulta);
			stmt.setInt(1, id);
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
