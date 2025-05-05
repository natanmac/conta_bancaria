package conta_bancaria.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Optional;

import conta_bancaria.model.Conta;
import conta_bancaria.repository.ContaRepository;

public class ContaController implements ContaRepository {

	//Criar a collection ArrayList 
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	
	//Variável para controlar os números das contas
	int numero = 0;
	
	
	@Override
	public void procurarPorNumero(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);
		
		if(conta.isPresent()) {
			conta.get().visualizar();
		} else {
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		}
		
	}

	@Override
	public void listarTodas() {
		for(var conta : listaContas) {
			conta.visualizar();
		}
		
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("A conta foi criada com Sucesso");
	}

	@Override
	public void atualizar(Conta conta) {
		Optional<Conta> buscaConta = buscarNaCollection(conta.getNumero());
		
		if(buscaConta.isPresent()) {
			listaContas.set(listaContas.indexOf(buscaConta.get()), conta);
			System.out.printf("\nA conta número %d foi atualizada com sucesso!", conta.getNumero());
		} else {
			System.out.printf("\nA conta número %d não foi encontrada!", conta.getNumero());
		}
		
	}

	@Override
	public void deletar(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);
		
		if(conta.isPresent()) {
			if(listaContas.remove(conta.get()) == true) 
				System.out.printf("\nA conta número %d foi excluída com sucesso!", numero);
		} else 
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		
	}

	@Override
	public void sacar(int numero, float valor) {
		//NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		
		Optional<Conta> conta = buscarNaCollection(numero);
		
		if(conta.isPresent()) {
			if(conta.get().sacar(valor) == true) {
				System.out.printf("\nO saque de R$%.2f, foi efetuado com sucesso na conta de número %d", valor, numero);
			}
		} else {
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		}
		
	}

	@Override
	public void depositar(int numero, float valor) {
		//NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		
		Optional<Conta> conta = buscarNaCollection(numero);
		
		if(conta.isPresent()) {
			conta.get().depositar(valor);
			System.out.printf("\nDeposito no valor de R$%.2f, foi efetuado com sucesso na conta de número %d", valor, numero);
		} else {
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		}
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		Optional<Conta> contaOrigem = buscarNaCollection(numeroOrigem);
		Optional<Conta> contaDestino = buscarNaCollection(numeroDestino);
		
		if(contaOrigem.isPresent() && contaDestino.isPresent()) {
			if(contaOrigem.get().sacar(valor) == true) {
				contaDestino.get().depositar(valor);
				System.out.printf("\nTransferência no valor de R$%.2f, da conta %d foi efetuado com sucesso na conta de número %d", valor, numeroOrigem, numeroDestino);
			}
		} else {
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		}
		
	}
	
	//Metódos auxiliares
	
	public int gerarNumero() {
		return ++ numero;
	}
	
	public Optional<Conta> buscarNaCollection(int numero) {
		for(var conta : listaContas) {
			if(conta.getNumero() == numero) {
				return Optional.of(conta);
			}
		}
		return Optional.empty();
	}

}
