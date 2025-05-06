package conta_bancaria.repository;

import conta_bancaria.model.Conta;

public interface ContaRepository {
	
	// Metódo do CRUD (Create, read, update e delete)
	public void procurarPorNumero(int numero);
	public void listarTodas();
	public void cadastrar(Conta conta);
	public void atualizar(Conta conta);
	public void deletar(int numero);
	public void listarPorTitular(String titular);
	
	// Metódos Bancários
	public void sacar(int numero, float valor);
	public void depositar(int numero, float valor);
	public void transferir(int numeroOrigem, int numeroDestino, float valor);

}
