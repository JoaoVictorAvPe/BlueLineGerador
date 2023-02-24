package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import entities.Semente;

public class MetUteis {
	
	public static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	public static String pathOrigem = "C:\\Users\\joaoa\\OneDrive\\Área de Trabalho\\Java\\ws-eclipse\\BlueLineGerador\\BlueLine.txt";
	public static String pathDestino = pathOrigem.replace("BlueLine.txt", "Pedido.txt");
	
	public static List<Semente> lerArquivo() {
		List<Semente> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(pathOrigem))) {
			String line = br.readLine();
			while (line != null) {
				list.add(new Semente(line));
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return list;
	}
	
	public static void gravarArquivo(List<Semente> list) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathOrigem))) {
			for (Semente i : list) {
				bw.write(i.getNome());
				bw.newLine();
				}
		}
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	

	public static List<String> limpaAspasEAcentos(List<String> list) {
		List<String> newList = new ArrayList<>();
		for (String i : list) {
			newList.add(removerAcentos(i).replace("'", " ").replace(",", " ").trim());
		}
		return newList;
	}

	private static String removerAcentos(String value) {
		String normalizer = Normalizer.normalize(value, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(normalizer).replaceAll("");
	}

}