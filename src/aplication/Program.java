package aplication;

import java.util.List;

import service.MetUteis;

public class Program {

	public static void main(String[] args) {
		
		
		List<String> blueLine = MetUteis.lerArquivo();
		blueLine = MetUteis.limpaAspasEAcentos(blueLine);
		blueLine.forEach(System.out::println);
		MetUteis.gravarArquivo(blueLine);
	}

}
