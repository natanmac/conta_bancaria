package conta_bancaria.model;

import java.text.NumberFormat;

public class Conta {
	
	// Atributos da classe
	private int numero, agencia, tipo;
	private float saldo;
	private String titular;
	
	// Método construtor 
	public Conta(int numero, int agencia, int tipo, float saldo, String titular) {
		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
		this.saldo = saldo;
		this.titular = titular;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	// Métodos bancários
	public boolean sacar(float valor) {
		if(this.saldo < valor) {
			System.out.println("\nSaldo insuficiente");
			return false;
		}
		
		this.setSaldo(this.saldo - valor);
		return true;
	}
	
	public void depositar(float valor) {
		this.saldo += valor;
	}
	
	// Método para visualizar os dados da conta
	public void visualizar() {
		
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		
		String tipo = "";
		
		switch(this.tipo) {
			case 1 -> tipo = "Conta Corrente";
			case 2 -> tipo = "Conta Poupança";
			default -> tipo = "Inválido";
		}
		
		System.out.println("*****************************************************");
		System.out.println("                  DADOS DA CONTA                     ");
		System.out.println("*****************************************************");
		System.out.println("Número da conta: " + this.numero);
		System.out.println("Número da agência: " + this.agencia);
		System.out.println("Tipo da conta: " + tipo);
		System.out.println("Titular da conta: " + this.titular);
		System.out.println("Saldo da conta: " + nfMoeda.format(this.saldo));
	}
	

}
