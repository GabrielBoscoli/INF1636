package dominio;

import java.util.ArrayList;

import gui.PainelArma;

public class Jogador {
	String nome;
	ArrayList<PainelArma> armas;
	ArrayList<Coordenada[]> coordenadaArmas;
	
	public Jogador() {
		nome = null;
		armas = new ArrayList<PainelArma>();
		coordenadaArmas = new ArrayList<Coordenada[]>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<PainelArma> getArmas() {
		return armas;
	}
	
	public void setArmas(ArrayList<PainelArma> armas) {
		this.armas = new ArrayList<>(armas);
	}
	
	public ArrayList<Coordenada[]> getCoordenadaArmas() {
		return coordenadaArmas;
	}
	
	public void setCoordenadaArmas(ArrayList<Coordenada[]> armas) {
		this.coordenadaArmas = new ArrayList<>(armas);
	}
	
	public String toString() {
		String stringJogador = nome + "\n\n";
		stringJogador += coordenadaArmasToString();
		stringJogador += "\n" + armasJogadorToString();
		
		return stringJogador;
	}
	
	private String armasJogadorToString() {
		String retorno = "";
		for(int i = 0; i < armas.size(); i++) {
			retorno += armas.get(i).getArma().getTipoArma() + " ";
			retorno += armas.get(i).getArma().getQuadradosIntactos();
			retorno += "\n";
		}
		return retorno;
	}

	private String coordenadaArmasToString() {
		String retorno = "";
		for(int i = 0; i < coordenadaArmas.size(); i++) {
			Coordenada[] coordenada = coordenadaArmas.get(i);
			for(int j = 0; j < coordenada.length; j++) {
				retorno += coordenadaArmas.get(i)[j].getX();
				retorno += " " + coordenadaArmas.get(i)[j].getY();
				retorno += ", ";
			}
			retorno = retorno.substring(0, retorno.length() - 2);
			retorno += "\n";
		}
		return retorno;
	}
}
