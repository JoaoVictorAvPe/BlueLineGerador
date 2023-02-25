package aplication;

import java.util.Scanner;

import entities.GeradorException;
import entities.Pedido;
import service.MetUteis;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Pedido ped = new Pedido();
		byte resp = 0;
		
		System.out.println("Pedido Blue Line " + ped.getData().format(MetUteis.fmt));
		System.out.println("Bem Vindo\n");
		
		do {
			try {
				System.out.println("==============");
				System.out.println("1-Novo Pedido\n2-Continuar\n3-Imprimir\n4-Gerar Arquivo\n5-Alterar\n6-Importar Pedido\n7-Sair");
				System.out.println("==============");
				resp = Byte.parseByte(sc.nextLine());
				if (resp < 1 || resp > 7) {
					throw new GeradorException("Opcao invalida");
				}
				
				if (resp == 1) {
					System.out.println("Instrucoes:\nDigite as quantidades a vontade\nAtribua a quantia 99 para encerrar");
					sc.nextLine();
					ped.novoPedido(sc);
					System.out.println("Pedido salvo");
				}
				
				if (resp == 2) {
					System.out.println();
					ped.continuarPedido(sc);
				}

				if (resp == 3) {
					System.out.println();
					ped.imprimirPedido();
				}
				
				if (resp == 4) {
					ped.gerarArquivoDoPedido();
				}

				if (resp == 5) {
					ped.alterarItem(sc);
				}
				
				if (resp == 6) {
					ped.importarArquivo();
				}
				
				if (resp != 7) {
					System.out.println("\npressione ENTER para continuar\n");
					sc.nextLine();
				}
			} 
			catch (NumberFormatException e) {
				System.out.println("Erro, caractere errado\n");
				sc.nextLine();
			}
			catch (NullPointerException e) {
				System.out.println("Erro: Pedido nao gerado ou indice invalido\n");
				System.out.println(e.getMessage());
				e.getStackTrace();
				sc.nextLine();
			}
			catch (GeradorException e) {
				System.out.println("Erro: " + e.getMessage());
				sc.nextLine();
			}
		} while (resp != 7);
		
		System.out.println("\nTenha um bom dia");
		sc.close();
	}

}
