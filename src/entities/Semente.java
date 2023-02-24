package entities;

public class Semente {
	private String nome;
	private Byte qtd;
	
	public Semente(String nome) {
		this.nome = nome;
		this.qtd = 0;
	}

	public Semente(String nome, Byte qtd) {
		this.nome = nome;
		this.qtd = qtd;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Byte getQtd() {
		return qtd;
	}

	public void setQtd(Byte qtd) {
		this.qtd = qtd;
	}
	

	@Override
	public String toString() {
		if (qtd != null) {
			return "Nome: " + nome + ", Qtd: " + qtd;
		} else {
			return "Nome: " + nome;
		}
	}
	
	
}
