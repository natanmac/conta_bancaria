package conta_bancaria;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import conta_bancaria.controller.ContaController;
import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;

public class Menu {

	public static void main(String[] args) {
		
		Scanner leia = new Scanner(System.in);
		
		ContaController contas = new ContaController();

		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		//Dados para teste
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, 1000.00f, "João da Silva", 100.00f);
		contas.cadastrar(cc1);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 123, 2, 1000.00f, "Maria da Silva", 12);
		contas.cadastrar(cp1);
		
		while (true) {

			System.out.println(Cores.ANSI_BLUE_BACKGROUND_BRIGHT);
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO TAXA AMIGA                     ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Listar por titular                   ");
			System.out.println("            0 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     ");

			opcao = leia.nextInt();

			if (opcao == 0) {
				System.out.println("\nBanco Taxa Amiga - Mais que um banco, seu amigo!");
				sobre();
                 leia.close();
				System.exit(0);
			}

			switch (opcao) {
				case 1:
					System.out.println("Criar Conta\n\n");
					
					System.out.println("Digite o número da agência: ");
					agencia = leia.nextInt();
					
					System.out.println("Digite o nome do titular: ");
					leia.skip("\\R");
					titular = leia.nextLine();
					
					System.out.println("Digite o tipo da conta (1 - CC | 2 - CP): ");
					tipo = leia.nextInt();
					
					System.out.println("Digite o saldo inicial da conta: ");
					saldo = leia.nextFloat();
					
					switch(tipo) {
						case 1 ->{
							System.out.println("Digite o limite da conta: ");
							limite = leia.nextFloat();
							contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, saldo, titular, limite));
						}
						case 2 ->{
							System.out.println("Digite o aniversário da conta: ");
							aniversario = leia.nextInt();
							contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, saldo, titular, aniversario));
						}
					}
					
					keyPress();
					break;
				case 2:
					System.out.println("Listar todas as Contas\n\n");
					
					contas.listarTodas();
					
					keyPress();
					break;
				case 3:
					System.out.println("Consultar dados da Conta - por número\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					contas.procurarPorNumero(numero);

					keyPress();
					break;
				case 4:
					System.out.println("Atualizar dados da Conta\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					Optional<Conta> conta = contas.buscarNaCollection(numero);
					
					if(conta.isPresent()) {
						System.out.println("Digite o número da agência: ");
						agencia = leia.nextInt();
						
						System.out.println("Digite o nome do titular: ");
						leia.skip("\\R");
						titular = leia.nextLine();
						
						tipo = conta.get().getTipo();
						
						System.out.println("Digite o saldo inicial da conta: ");
						saldo = leia.nextFloat();
						
						switch(tipo) {
							case 1 ->{
									System.out.println("Digite o limite da conta: ");
									limite = leia.nextFloat();
									contas.atualizar(new ContaCorrente(numero, agencia, tipo, saldo, titular, limite));
								}
							case 2 ->{
									System.out.println("Digite o aniversário da conta: ");
									aniversario = leia.nextInt();
									contas.atualizar(new ContaPoupanca(numero, agencia, tipo, saldo, titular, aniversario));
								}
						}
					} else {
						System.out.printf("\nA conta número %d não existe!", numero);
					}

					keyPress();
					break;
				case 5:
					System.out.println("Apagar a Conta\n\n");
					
					System.out.println("Consultar dados da Conta - por número\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					contas.deletar(numero);

					keyPress();
					break;
				case 6:
					System.out.println("Saque\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					System.out.println("Digite o valor do saque: ");
					valor = leia.nextFloat();
					
					contas.sacar(numero, valor);

					keyPress();
					break;
				case 7:
					System.out.println("Depósito\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					System.out.println("Digite o valor do saque: ");
					valor = leia.nextFloat();
					
					contas.depositar(numero, valor);

					keyPress();
					break;
				case 8:
					System.out.println("Transferência entre Contas\n\n");
					
					System.out.println("Digite o número da conta de origem: ");
					numero = leia.nextInt();
					
					System.out.println("Digite o número da conta de destino: ");
					numeroDestino = leia.nextInt();
					
					System.out.println("Digite o valor da transferência: ");
					valor = leia.nextFloat();
					
					contas.transferir(numero, numeroDestino, valor);

					keyPress();
					break;
				case 9:
					System.out.println("Consultar contas por titular\n\n");
					
					System.out.println("Digite o nome do titular: ");
					leia.skip("\\R");
					titular = leia.nextLine();
					
					contas.listarPorTitular(titular);
					
					keyPress();
					break;
				default:
					System.out.println("\nOpção Inválida!\n");
					
					keyPress();
					break;
			}
		}
	}
    
	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Natan Macedo                     ");
		System.out.println("Generation Brasil - generation@generation.org              ");
		System.out.println("github.com/conteudoGeneration                              ");
		System.out.println("https://github.com/natanmac/conta_bancaria                 ");
		System.out.println("***********************************************************");
	}
	
	public static void keyPress() {
		 
		try {
 
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();
 
		} catch (IOException e) {
 
			System.err.println("Ocorreu um erro ao tentar ler o teclado");
 
		}
	}

}
