package dominio;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pessoa")
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;	
	private Usuario usuario;
	private String nome;
	private String strdataNascimento;
	private Date dataNascimento;
	private String sexo;

	public Pessoa(Usuario usuario, String nome, String dataNascimento, String sexo) {
		this.usuario = usuario;
		this.nome = nome;
		this.strdataNascimento = dataNascimento;
		this.sexo = sexo;
	}

	public Pessoa(){}

	public int getId() {
	    return id;
	}
	
	@XmlElement
	public void setId(int id) {
	    this.id = id;
	}

	public Usuario getUsuario() {
	    return usuario;
	}

	@XmlElement
	public void setUsuario(Usuario usuario) {
	    this.usuario = usuario;
	}

	public String getNome() {
	    return nome;
	}

	@XmlElement
	public void setNome(String nome) {
	    this.nome = nome;
	}

	public String getStrDataNascimento() {
	    return strdataNascimento;
	}

	@XmlElement
	public void setStrDataNascimento(String dataNascimento) {
	    this.strdataNascimento = dataNascimento;
	}
	
	public Date getDataNascimento() {
	    return dataNascimento;
	}
	@XmlElement
	public void setDataNascimento(Date dataNascimento) {
	    this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
	    return sexo;
	}

	@XmlElement
	public void setSexo(String sexo) {
	    this.sexo = sexo;
	}

	/*
	@Override
	public boolean equals(Object object){
		if(object == null){
			return false;
		}else if(!(object instanceof Pessoa)){
			return false;
		}else {
			Pessoa pessoa = (Pessoa)object;
			if(id == pessoa.getId()
					&& email.equals(user.getEmail())
					&& senha.equals(user.getSenha())
					){
				return true;
			}
		}
		return false;
	}*/
}