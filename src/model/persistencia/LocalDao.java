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
		/*
	    String[] tagsDoUsuario = new String[9];  
	    tagsDoUsuario[0] = "praia";  
	    tagsDoUsuario[1] = "gastronomia";
		List<Local> lista = local.getLocaisPorTag(tagsDoUsuario);
		System.out.println(lista.size());
		for(int i=0; i<lista.size();i++) {
			System.out.println(lista.get(i).getCidade());
		System.out.println(local.getId("fortaleza"));*/
		
		ArrayList<Local> resposta = local.getUltimosLugares(1);
		System.out.println("oi");
		
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
	
	public boolean insetAvaliacao(Integer id_usuario, Integer id_lugar, String nota) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		ResultSet resultSet = null;
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		String query = "insert into whereverigo.avaliacao (id_usuario,idlugar,nota,dataAvaliacao) values (?, ?, ?, ?);";
		try{	
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id_usuario);
			stmt.setInt(2, id_lugar);
			int iNota = Integer.parseInt(nota);
			stmt.setInt(3, iNota);
			stmt.setDate(4, sqlDate);
			stmt.executeUpdate();
			return true;
		}catch (SQLException ex){
			return false;
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
		String consulta = "SELECT distinct(cidade.id),nome, descricao, estado_pais,foto FROM whereverigo.avaliacao inner join whereverigo.cidade on avaliacao.idlugar=cidade.id where id_usuario = ? and  dataAvaliacao in (select max(dataAvaliacao) from whereverigo.avaliacao where avaliacao.id_usuario = ?);";
		try{	
			stmt = connection.prepareStatement(consulta);
			stmt.setInt(1, id);
			stmt.setInt(2, id);
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
	
	public boolean  insertAvaliacao(String query) {
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
		String consulta = query;
		try{	
			stmt = connection.prepareStatement(consulta);
			stmt.executeUpdate();
			
			return true;
		}catch (SQLException ex){
			return false;
		}
		finally{
			ConnectionFactory.closeConnection(connection, stmt, resultSet);
		}
	}
	
	public int getId(String nome) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String consulta = "SELECT id FROM whereverigo.cidade where cidade.nome = ? ;";
		try{	
			stmt = connection.prepareStatement(consulta);
			stmt.setString(1, nome);
			resultSet = stmt.executeQuery();
			
			int result = 0;
			while (resultSet.next()){
				result = resultSet.getInt("id");
			}
			return result;
		}catch (SQLException ex){
			return 0;
		}
		finally{
			ConnectionFactory.closeConnection(connection, stmt, resultSet);
		}
	}
	}
