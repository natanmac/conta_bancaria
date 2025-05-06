package conta_bancaria.model;

public class ContaPoupanca extends Conta {
	
	int aniversario;

	public ContaPoupanca(int numero, int agencia, int tipo, float saldo, String titular, int aniversario) {
		super(numero, agencia, tipo, saldo, titular);
		this.aniversario = aniversario;
	}

	public int getAniversario() {
		return aniversario;
	}

	public void setAniversario(int aniversario) {
		this.aniversario = aniversario;
	}
	
	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("Data de anivesário da poupança: " + this.aniversario);
	}

}
