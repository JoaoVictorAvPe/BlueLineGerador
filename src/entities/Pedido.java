package entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import service.MetUteis;

public class Pedido {
	private LocalDateTime data;
	private String solicitante;
	
	private List<Semente> pedidoBlueLine = MetUteis.lerArquivo();
	
	public Pedido() {
	}

	public Pedido(String solicitante) {
		this.data = LocalDateTime.now();
		this.solicitante = solicitante;
	}
	
	
	public List<Semente> getPedidoBlueLine() {
		return pedidoBlueLine;
	}

	public void setPedidoBlueLine(List<Semente> pedidoBlueLine) {
		this.pedidoBlueLine = pedidoBlueLine;
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
	
	public void imprimir() {
		System.out.println("Pedido Blue Line " + data.format(MetUteis.fmt));
		System.out.println();
		pedidoBlueLine.forEach(System.out::println);
	}
	
	public void gerarPedido() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(MetUteis.pathDestino))) {
			bw.write("Solicitante: " + solicitante);
			bw.newLine();
			bw.write(data.format(MetUteis.fmt));
			bw.newLine();
			bw.newLine();
			for (Semente i : pedidoBlueLine) {
				bw.write(i.getQtd() + (i.getQtd()==1?" caixinha":" caixinhas") + " de " + i.getNome());
				bw.newLine();
				}
		}
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
}
