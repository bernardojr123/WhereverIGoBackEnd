package dominio;

import java.io.IOException;
import java.sql.SQLException;
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

@Path("/UserService")
public class UserService {
	
   UsuarioDao userDao = new UsuarioDao();
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>";
   

/*   @GET
   @Path("/users")
   @Produces(MediaType.APPLICATION_JSON)
   public List<Usuario> getUsers(){
      return userDao.getAllUsers();
   }*/

   @GET
   @Path("/users/{useremail}")
   @Produces(MediaType.APPLICATION_XML)
   public Usuario getUser(@PathParam("useremail") String userEmail){
	   Usuario usuario = new Usuario(1,"1","1");
	   //return userDao.getUsuario(userEmail);
	   return usuario;
   }

   @POST
   @Path("/users")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String criarUsuario(@FormParam("id") int id,
      @FormParam("email") String email,
      @FormParam("senha") String senha,
      @Context HttpServletResponse servletResponse) throws IOException{
      Usuario usuario = new Usuario(id, email, senha);
      int result = userDao.addUsuario(usuario);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

/*   @PUT
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String updateUser(@FormParam("id") int id,
      @FormParam("name") String name,
      @FormParam("profession") String profession,
      @Context HttpServletResponse servletResponse) throws IOException{
      Usuario user = new Usuario(id, name, profession);
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