package dominio;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "local")
public class Local implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String cidade;
    private String estadoPais;
    private String descricao;
    private String strImagem;
    
    public int getId() {
    	return id;
    }
    
    @XmlElement
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getCidade() {
        return cidade;
    }
    
    @XmlElement
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstadoPais() {
        return estadoPais;
    }
    
    @XmlElement
    public void setEstadoPais(String estadoPais) {
        this.estadoPais = estadoPais;
    }

    public String getDescricao() {
        return descricao;
    }
    
    @XmlElement
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getStrImagem() {
    	return strImagem;
    }
    
    @XmlElement
    public void setStrImagem(String str) {
    	this.strImagem = str;
    }

    public Local() {
    }

    public Local(String cidade, String estadoPais, String strImagem, String descricao) {
    	this.cidade = cidade;
        this.estadoPais = estadoPais;
        this.descricao = descricao;
        this.strImagem = strImagem;
    }

}
