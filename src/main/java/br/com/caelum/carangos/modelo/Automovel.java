package br.com.caelum.carangos.modelo;

public class Automovel {
	private String cor;
	private ModeloDeAutomovel modelo;
	private Integer anoFabricacao;
	
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public ModeloDeAutomovel getModelo() {
		return modelo;
	}
	public void setModelo(ModeloDeAutomovel modelo) {
		this.modelo = modelo;
	}
	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
}
