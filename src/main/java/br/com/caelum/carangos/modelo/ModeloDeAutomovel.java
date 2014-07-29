package br.com.caelum.carangos.modelo;

public class ModeloDeAutomovel {
	private String nome;
	private Marca fabricante;
	private Cilindros cilindrada;
	private double litrosMotor;
	private Intervalo intervaloDeVenda;
	private Combustivel combustivel;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String modelo) {
		this.nome = modelo;
	}
	public Marca getFabricante() {
		return fabricante;
	}
	public void setFabricante(Marca fabricante) {
		this.fabricante = fabricante;
	}
	public Cilindros getCilindrada() {
		return cilindrada;
	}
	public void setCilindrada(Cilindros cilindrada) {
		this.cilindrada = cilindrada;
	}
	public double getLitrosMotor() {
		return litrosMotor;
	}
	public void setLitrosMotor(double litrosMotor) {
		this.litrosMotor = litrosMotor;
	}
	public Intervalo getIntervaloDeVenda() {
		return intervaloDeVenda;
	}
	public void setIntervaloDeVenda(Intervalo intervaloDeVenda) {
		this.intervaloDeVenda = intervaloDeVenda;
	}
	public Combustivel getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}
	
	
}
