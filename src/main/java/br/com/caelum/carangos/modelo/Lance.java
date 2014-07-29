package br.com.caelum.carangos.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Lance implements Comparable<Lance>{
	private Usuario usuario;
	private double valor;
	private Calendar horario;
	private String dataFormatada;
	
	public Lance() {
	}
	
	public Lance(Usuario usuario, Calendar data) {
		this.usuario = usuario;
		this.horario = data;
		this.dataFormatada = new SimpleDateFormat("ddMMyy-HHmmss").format(this.horario.getTime());
	}
	
	public String getDataFormatada() {
		return this.dataFormatada;
	}

	@Override
	public String toString() {
		return getDataFormatada()+ "    -> R$"+this.valor;
	}
	
	@Override
	public int compareTo(Lance o) {
		return o.horario.compareTo(this.horario);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Calendar getHorario() {
		return horario;
	}


}
