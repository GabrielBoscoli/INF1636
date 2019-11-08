package dominio;

import java.util.ArrayList;

import gui.PainelArma;
import outros.Coordenada;

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
}
