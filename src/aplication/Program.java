package aplication;

import java.util.Scanner;

import entities.Pedido;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Pedido ped = new Pedido();
		byte resp;

		do {
			System.out.println("==============");
			System.out.println("1-Novo Pedido\n2-Gerar Arquivo\n3-Imprimir Pedido\n4-Alterar\n5-Sair");
			System.out.println("==============");
			resp = Byte.parseByte(sc.nextLine());

			if (resp == 1) {
				System.out.println();
				ped.escreverPedido(sc);
			}

			if (resp == 2) {
				ped.gerarArquivoDoPedido();
			}

			if (resp == 3) {
				System.out.println();
				ped.imprimirPedido();
			}
			
			if (resp == 4) {
				ped.alterarItem(sc);
			}

		} while (resp != 5);

		sc.close();
	}

}
