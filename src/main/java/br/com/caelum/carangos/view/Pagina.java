package br.com.caelum.carangos.view;

public class Pagina {
	private int tamanho = 25;
	private int numero = 0;
	
	public Pagina proxima() {
		Pagina proxima = new Pagina();
		proxima.numero = numero+1;
		proxima.tamanho = tamanho;
		
		return proxima;
	}
	
	@Override
	public String toString() {
		return String.format("pagina.tamanho=%d&pagina.numero=%d", this.tamanho, this.numero);
	}
	
	public int getInicio() {
		return numero * tamanho;
	}
	
	public int getFim() {
		return (numero+1)*tamanho;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public int getTamanho() {
		return tamanho;
	}

}
