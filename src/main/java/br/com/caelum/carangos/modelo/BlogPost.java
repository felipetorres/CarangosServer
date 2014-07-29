package br.com.caelum.carangos.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BlogPost implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String mensagem;
	@ManyToOne
	private Usuario autor;
	private String foto;
	
	@Enumerated(EnumType.STRING)
	private Humor estadoDeHumor;

	@Deprecated
	public BlogPost() {}

	public BlogPost(String mensagem, Usuario autor, Humor humor, String foto) {
		this.mensagem = mensagem;
		this.autor = autor;
		this.estadoDeHumor = humor;
		this.foto = foto;
	}
	
	@Override
	public String toString() {
		return String.format("(%s) %s",this.autor, this.mensagem);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public Humor getEstadoDeHumor() {
		return estadoDeHumor;
	}
	
	public void setEstadoDeHumor(Humor estadoDeHumor) {
		this.estadoDeHumor = estadoDeHumor;
	}
}
