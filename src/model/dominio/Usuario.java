package model.dominio;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "usuario")
public class Usuario implements Serializable {

   private static final long serialVersionUID = 1L;
   private int id;
   private String email;
   private String senha;

   public Usuario(){}

   public Usuario(int id, String email, String senha){
      this.id = id;
      this.email = email;
      this.senha = senha;
   }

   public int getId() {
      return id;
   }
   
   @XmlElement
   public void setId(int id) {
      this.id = id;
   }
   public String getEmail() {
      return email;
   }
   @XmlElement
   public void setEmail(String email) {
      this.email = email;
   }
   public String getSenha() {
      return senha;
   }
   @XmlElement
   public void setSenha(String senha) {
      this.senha = senha;
   }	

   @Override
   public boolean equals(Object object){
      if(object == null){
         return false;
      }else if(!(object instanceof Usuario)){
         return false;
      }else {
         Usuario user = (Usuario)object;
         if(id == user.getId()
            && email.equals(user.getEmail())
            && senha.equals(user.getSenha())
         ){
            return true;
         }			
      }
      return false;
   }	
}