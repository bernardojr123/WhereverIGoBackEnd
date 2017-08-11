package model.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import control.ConnectionFactory;
import control.InicializarBanco;
import model.dominio.Pessoa;
import model.dominio.Usuario;



public class UsuarioDao {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
/*		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		UsuarioDao usu = new UsuarioDao();
		Pessoa pessoa = new Pessoa();
		Usuario usuario = new Usuario();
		usuario.setEmail("bda@nbr.do");
		usuario.setSenha("bernardo");
		pessoa.setUsuario(usuario);
		String dataa = "14/09/1994";
		pessoa.setNome("bernardo");
		java.util.Date data;
		try {
			data = sdf.parse(dataa);
			pessoa.setDataNascimento(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pessoa.setSexo("Masculino");
		UsuarioDao usua = new UsuarioDao();
		int i = usua.addUsuario(pessoa);
		System.out.println("ainda n sei");*/
		
		UsuarioDao usu = new UsuarioDao();
		ArrayList<ArrayList<Integer>> lista = usu.getNotaOutrosUsuarios(2);
		System.out.println(lista);
		
	}
	
	public UsuarioDao() {
		try {
			InicializarBanco ini = new InicializarBanco();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Pessoa getPessoa(String email, String senha){
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
					Date data = resultSet2.getDate("dataNascimento");
					String strData = sdf.format(data);
					pessoa.setStrDataNascimento(strData);;
					
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
	
	public ArrayList<ArrayList<Integer>> getNotaOutrosUsuarios(int id){
		ArrayList<ArrayList<Integer>> resposta = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> usuarioId = new ArrayList<Integer>();
		ArrayList<Integer> avaliacaoIdLugar = new ArrayList<Integer>();
		ArrayList<Integer> avaliacaoNota = new ArrayList<Integer>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = ConnectionFactory.getConnection();
		java.sql.PreparedStatement stmt = null;
		ResultSet resultSet = null;
		String consulta = "select usuario.id, avaliacao.idlugar, avaliacao.nota from whereverigo.usuario right join whereverigo.avaliacao on usuario.id = avaliacao.id_usuario where id_usuario != ? ";
		try{	
			stmt = connection.prepareStatement(consulta);
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			while (resultSet.next()){
				usuarioId.add(resultSet.getInt("id"));
				avaliacaoIdLugar.add(resultSet.getInt("idlugar"));
				avaliacaoNota.add(resultSet.getInt("nota"));
			}
			resposta.add(usuarioId);
			resposta.add(avaliacaoIdLugar);
			resposta.add(avaliacaoNota);
			return resposta;
			
		}catch (SQLException ex){
			return null;
		}
		finally{
			ConnectionFactory.closeConnection(connection, stmt, resultSet);
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
	   if(!verificarUsuario(pessoa.getUsuario().getEmail(), pessoa.getUsuario().getSenha())) {
		   try{
			   Usuario usuario = pessoa.getUsuario();
			   
			   stmt = connection.prepareStatement("INSERT INTO whereverigo.usuario (email, senha)VALUES(?,?)");
			   stmt.setString(1, usuario.getEmail());
			   stmt.setString(2, usuario.getSenha());
			   stmt.executeUpdate();
			   
			   java.sql.PreparedStatement stmt2 = null;
			   stmt2 = connection.prepareStatement("INSERT INTO whereverigo.pessoa (id_usuario, nome, dataNascimento, sexo) VALUES (?,?,?,?);");
			   Usuario p = getUsuario(usuario.getEmail());
			   int id = p.getId();
			   stmt2.setInt(1, id);
			   stmt2.setString(2, pessoa.getNome());
			   java.sql.Date dataSql = new java.sql.Date(pessoa.getDataNascimento().getTime());
			   String strDate = sdf.format(dataSql);
			   stmt2.setDate(3, dataSql );
			   stmt2.setString(4, pessoa.getSexo());
			   stmt2.executeUpdate();
			   
			   
				
		   }catch (SQLException ex){
			   return 0;
				
		   }finally{
			   ConnectionFactory.closeConnection(connection, stmt);
		   }
	   return 1;
	   }
	   return 0;
   }

   public int atualizarUsuario(Usuario usuario){
      	
	   return 0;
   }
}