package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import service.MetUteis;

public class Pedido {
	private LocalDateTime data;
	private String solicitante;
	private Integer iteracao;

	List<Semente> listaBlueLine;

	public Pedido() {
		this.data = LocalDateTime.now();
	}

	public List<Semente> getListaBlueLine() {
		return listaBlueLine;
	}

	public void setListaBlueLine(List<Semente> listaBlueLine) {
		this.listaBlueLine = listaBlueLine;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public void novoPedido(Scanner sc) throws GeradorException {
		setListaBlueLine(new ArrayList<>(MetUteis.lerArquivo()));
		this.iteracao = 0;
		System.out.print("Nome do solicitante: ");
		this.solicitante = sc.nextLine();
		for (Semente s : listaBlueLine) {
			System.out.print(s.getNome() + ": ");
			byte qtd = Byte.parseByte(sc.nextLine());
			if (qtd != 99) {
				s.setQtd(qtd);
				s.setIterado(true);
			}
			else break;
			iteracao++;
		}
	}

	public void continuarPedido(Scanner sc) throws GeradorException {
		this.iteracao = 0;
		if (this.solicitante == null) {
			throw new GeradorException("Sem pedido na memoria, inicie um novo");
		}
		for (Semente s : listaBlueLine) {
			if (!s.isIterado()) {
				System.out.print(s.getNome() + ": ");
				byte qtd = Byte.parseByte(sc.nextLine());
				if (qtd == 99)
					break;
				else
					s.setQtd(qtd);
			}
			iteracao++;
		}
	}

	public void alterarItem(Scanner sc) {
		if (solicitante == null) {
			throw new GeradorException("Sem pedido na memoria, inicie um novo");
		}
		imprimirIndices();
		System.out.print("\nIndice a ser alterado: ");
		byte index = Byte.parseByte(sc.nextLine());
		System.out.println(listaBlueLine.get(index));
		System.out.print("Nova quantidade: ");
		listaBlueLine.get(index).setQtd(Byte.parseByte(sc.nextLine()));
		System.out.println("\nAlteração feita com sucesso!");
	}

	private void imprimirIndices() {
		System.out.println("\nIndices:");
		for (byte i = 0; i < iteracao; i++) {
			System.out.print("Indice: " + i);
			System.out.println(" " + listaBlueLine.get(i));
		}
	}

	public void imprimirPedido() throws GeradorException {
		if (solicitante == null) {
			throw new GeradorException("Nao ha pedido feito para poder ser impresso");
		}
		System.out.println("\nPedido Blue Line " + data.format(MetUteis.fmt));
		System.out.println("Solicitante: " + solicitante + "\n");
		listaBlueLine.stream().filter(x -> x.getQtd() != 0).forEach(System.out::println);
	}

	public void gerarArquivoDoPedido() throws GeradorException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(MetUteis.pathDestino))) {
			if (solicitante == null) {
				throw new GeradorException("Nao ha pedido feito para poder ser gerado");
			}
			bw.write("Pedido Blue Line " + data.format(MetUteis.fmt));
			bw.newLine();
			bw.write("Solicitante: " + solicitante);
			bw.newLine();
			bw.newLine();
			for (Semente i : listaBlueLine.stream().filter(x -> x.getQtd() != 0).collect(Collectors.toList())) {
				bw.write(i.getQtd() + (i.getQtd() == 1 ? " caixinha" : " caixinhas") + " de " + i.getNome());
				bw.newLine();
			}
			System.out.println("\nArquivo gerado com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void importarArquivo() throws GeradorException {
		setListaBlueLine(new ArrayList<>(MetUteis.lerArquivo()));
		try (BufferedReader br = new BufferedReader(new FileReader(MetUteis.pathDestino))) {
			String line = br.readLine();
			if (line.isEmpty()) {
				throw new GeradorException("Arquivo vazio, inicie um novo pedido");
			}
			byte linhasLidas = 1;
			this.iteracao = 0;
			while (line != null) {
				if (linhasLidas == 2) {
					this.solicitante = line.substring(13);
				}
				if (linhasLidas >= 4) {
					String[] info = (line.charAt(0) == '1') ? line.split(" caixinha de ") : line.split(" caixinhas de ");
					Semente itemEncontrado = listaBlueLine.stream().filter(x -> x.getNome().equals(info[1])).findFirst().orElse(null);
					itemEncontrado.setQtd(Byte.parseByte(info[0]));
					itemEncontrado.setIterado(true);
					if (listaBlueLine.indexOf(itemEncontrado) > this.iteracao) {
						this.iteracao = listaBlueLine.indexOf(itemEncontrado);
					}
				}
				line = br.readLine();
				linhasLidas++;
			}
			this.iteracao++;
			System.out.println("Pedido importado com sucesso");
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
