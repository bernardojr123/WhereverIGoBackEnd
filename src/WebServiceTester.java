import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import dominio.Pessoa;

public class WebServiceTester  {

   private Client client;
   private String REST_SERVICE_URL_ADD = "http://localhost:8080/WhereverIgo/rest/UserService/users";
   private String REST_SERVICE_URL_VERIFICAR = "http://localhost:8080/WhereverIgo/rest/UserService/verificarlogin";
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
      form.param("email", "5");
      form.param("senha", "naresh");
      form.param("nome", "clerk");
      java.util.Date date = new java.util.Date();
	  Date data = new java.sql.Date(date.getTime());
	  SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
      form.param("dataNascimento", df.format(data));
      form.param("sexo", "Masculino");

      String callResult = client
         .target("http://localhost:8080/WhereverIgo/rest/UserService/users")
         .request(MediaType.APPLICATION_XML)
         .post(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
            String.class);
   
      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testAddUser, Result: " + result );
   }
   
   private void testExisteUser(){
	      Form form = new Form();
	      form.param("email", "5");
	      

	      String callResult = client
	         .target("http://localhost:8080/WhereverIgo/rest/UserService/existeusers")
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
	      form.param("email", "5");
	      form.param("senha", "naresh");

	      Pessoa callResult = client
	         .target("http://localhost:8080/WhereverIgo/rest/UserService/getuser")
	         .request(MediaType.APPLICATION_XML)
	         .post(Entity.entity(form,
	            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	            Pessoa.class);
	   
	      if (callResult != null) {
	    	  System.out.println("Test case name: GetPessoa, Result: " + callResult.getNome() );	    	  
	      }else {
	    	  System.out.println("Pessoa vazia");
	      }

	      
   }
}