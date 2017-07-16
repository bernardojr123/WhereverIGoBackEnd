package com.tutorialspoint;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import connection.InicializarBanco;

import dao.UsuarioDao;
import dominio.Pessoa;
import dominio.Usuario;


@Path("/UserService")
public class UserService {
	
   UsuarioDao usuarioDao = new UsuarioDao();
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>";
   private SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
   
   private void inicializaBanco() {
	   try {
		InicializarBanco ini = new InicializarBanco();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

   @POST
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String createUser(
		   @FormParam("email") String email,
		   @FormParam("senha") String senha,
		   @FormParam("nome") String nome,
		   @FormParam("dataNascimento") String dataNascimento,
		   @FormParam("sexo") String sexo,
		   @Context HttpServletResponse servletResponse) throws IOException{
	   Usuario usu = new Usuario();
	   usu.setEmail(email);
	   usu.setSenha(senha);
	   Pessoa pessoa = new Pessoa();
	   pessoa.setUsuario(usu);
	   pessoa.setNome(nome);
	   pessoa.setSexo(sexo);
	   try {
		   pessoa.setDataNascimento(df.parse(dataNascimento));
	   } catch (ParseException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	   int result = usuarioDao.addUsuario(pessoa);
	   if(result == 1){
		   return SUCCESS_RESULT;
	   }
	   return FAILURE_RESULT;
   }
   
   @POST
   @Path("/existeusers")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String createUsers(
		   @FormParam("email") String email,
		   @Context HttpServletResponse servletResponse) throws IOException{
	   boolean b = usuarioDao.existeUsuario(email);
	   if(b == true){
		   return SUCCESS_RESULT;
	   }
	   return FAILURE_RESULT;
   }
   
   @POST
   @Path("/getuser")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public Pessoa getUser(
		   @FormParam("email") String email,
		   @FormParam("senha") String senha,
		   @Context HttpServletResponse servletResponse) throws IOException{
	   Pessoa result = usuarioDao.getPessoa(email,senha);
	   
	   return result;
	   
   }
   
   /*
   @PUT
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String updateUser(@FormParam("id") int id,
      @FormParam("name") String name,
      @FormParam("profession") String profession,
      @Context HttpServletResponse servletResponse) throws IOException{
      User user = new User(id, name, profession);
      int result = userDao.updateUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @DELETE
   @Path("/users/{userid}")
   @Produces(MediaType.APPLICATION_XML)
   public String deleteUser(@PathParam("userid") int userid){
      int result = userDao.deleteUser(userid);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @OPTIONS
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   public String getSupportedOperations(){
      return "<operations>GET, PUT, POST, DELETE</operations>";
   }*/
}