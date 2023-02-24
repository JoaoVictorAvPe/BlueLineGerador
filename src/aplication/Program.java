package aplication;

import java.util.Scanner;

import entities.Pedido;
import entities.Semente;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o nome do solicitante: ");
		String nome = sc.nextLine();
		
		Pedido pedido = new Pedido(nome);
		
		for (Semente s : pedido.getPedidoBlueLine()) {
			System.out.print(s.getNome() + ": ");
			byte qtd = sc.nextByte();
			if (qtd == 99) {
				break;
			} else {
				s.setQtd(qtd);
			}
		}
		
		pedido.setPedidoBlueLine(pedido.getPedidoBlueLine().stream().filter(x -> x.getQtd() != 0).toList());
		
		pedido.gerarPedido();
		
		sc.close();
	}

}
