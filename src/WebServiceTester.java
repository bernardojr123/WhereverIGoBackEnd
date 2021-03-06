import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import model.dominio.Pessoa;



public class WebServiceTester  {

   private Client client;
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String PASS = "pass";
   private static final String FAIL = "fail";

   private void init(){
      this.client = ClientBuilder.newClient();
   }

   public static void main(String[] args){
      WebServiceTester tester = new WebServiceTester();
      //initialize the tester
      tester.init();
      //test get all users Web Service Method

      //test add user Web Service Method
      tester.testAddUser();
      tester.testExisteUser();
      tester.testGetPessoa();
      /*try {
    	  tester.testGetPessoa();
      }catch(Exception x){ 
    	  x.printStackTrace();
      }*/
   }


   //Test: Add User of id 2
   //Test: Check if result is success XML.
   private void testAddUser(){
      Form form = new Form();
      form.param("email", "ber@nar.do");
      form.param("senha", "bernardo");
      form.param("nome", "clerk");
      java.util.Date date = new java.util.Date();
	  Date data = new java.sql.Date(date.getTime());
	  SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
      form.param("dataNascimento", "14/09/1994");
      form.param("sexo", "Masculino");

      String callResult = client
         .target("http://192.168.25.55:8080/Wherever/rest/UsuarioService/createuser")
         .request(MediaType.APPLICATION_XML)
         .post(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
            String.class);
   
      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testAddUser, Result: " + callResult );
   }
   
   private void testExisteUser(){
	      Form form = new Form();
	      form.param("email", "ber@nar.do");
	      

	      String callResult = client
	         .target("http://localhost:8080/Wherever/rest/UsuarioService/existeusers")
	         .request(MediaType.APPLICATION_XML)
	         .post(Entity.entity(form,
	            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	            String.class);
	   
	      String result = PASS;
	      if(!SUCCESS_RESULT.equals(callResult)){
	         result = FAIL;
	      }

	      System.out.println("Test case name: existeusers, Result: " + result );
	   }
   
   private void testGetPessoa(){
	      Form form = new Form();
	      form.param("email", "ber@nar.do");
	      form.param("senha", "bernardo");

	      Pessoa callResult = client
	         .target("http://localhost:8080/Wherever/rest/UsuarioService/getuser")
	         .request(MediaType.APPLICATION_XML)
	         .post(Entity.entity(form,
	            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	            Pessoa.class);
	   
	      if (callResult != null) {
	    	  System.out.println("Test case name: GetPessoa, Result: " + callResult.getNome() );
	    	  try {
	    		  System.out.println("str pessoa = " + callResult.getStrDataNascimento());
	    	  }catch(Exception e) {
	    		  System.out.println(e);
	    	  }
	      }else {
	    	  System.out.println("Pessoa vazia");
	      }

	      
   }
}