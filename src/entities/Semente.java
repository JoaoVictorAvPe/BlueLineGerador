package entities;

import java.util.Objects;

public class Semente {
	private String nome;
	private Byte qtd;
	private boolean iterado;

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

	public boolean isIterado() {
		return iterado;
	}

	public void setIterado(boolean iterado) {
		this.iterado = iterado;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + ", Qtd: " + qtd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Semente other = (Semente) obj;
		return Objects.equals(nome, other.nome);
	}

}
