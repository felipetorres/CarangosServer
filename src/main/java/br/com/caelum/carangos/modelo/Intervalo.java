package br.com.caelum.carangos.modelo;

public class Intervalo {
	private Integer inicio;
	private Integer fim;
	
	public Intervalo(Integer data1, Integer data2) {
		this.inicio = (data1 > data2 ?  data1 : data2);
		this.fim = (data1 > data2 ?  data1 : data2);
	}

	public Integer getInicio() {
		return inicio;
	}

	public Integer getFim() {
		return fim;
	}
}
