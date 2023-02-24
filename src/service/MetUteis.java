package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MetUteis {
	
	private static String path = "C:\\Users\\joaoa\\OneDrive\\Área de Trabalho\\Java\\ws-eclipse\\BlueLineGerador\\BlueLine.txt";

	public static List<String> lerArquivo() {
		List<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				list.add(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return list;
	}
	
	public static void gravarArquivo(List<String> list) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (String i : list) {
				bw.write(i);
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