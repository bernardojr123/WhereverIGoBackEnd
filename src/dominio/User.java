package dominio;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "user")
public class User implements Serializable {

   private static final long serialVersionUID = 1L;
   private int id;
   private String email;
   private String senha;

   public User(){}

   public User(int id, String name, String profession){
      this.id = id;
      this.email = name;
      this.senha = profession;
   }

   public int getId() {
      return id;
   }
   
   @XmlElement
   public void setId(int id) {
      this.id = id;
   }
   public String getName() {
      return email;
   }
   @XmlElement
      public void setName(String name) {
      this.email = name;
   }
   public String getProfession() {
      return senha;
   }
   @XmlElement
   public void setProfession(String profession) {
      this.senha = profession;
   }	

   @Override
   public boolean equals(Object object){
      if(object == null){
         return false;
      }else if(!(object instanceof User)){
         return false;
      }else {
         User user = (User)object;
         if(id == user.getId()
            && email.equals(user.getName())
            && senha.equals(user.getProfession())
         ){
            return true;
         }			
      }
      return false;
   }	
}