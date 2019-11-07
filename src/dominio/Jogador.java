package dominio;

import java.util.ArrayList;
import java.util.List;

import gui.PainelArma;
import outros.Coordenada;

public class Jogador {
	String nome;
	List<PainelArma> armas;
	List<Coordenada[]> coordenadaArmas;
	
	public Jogador() {
		nome = null;
		armas = new ArrayList<PainelArma>();
		coordenadaArmas = new ArrayList<Coordenada[]>();
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setArmas(ArrayList<PainelArma> armas) {
		this.armas = new ArrayList<>(armas);
	}
	
	public void setCoordenadaArmas(ArrayList<Coordenada[]> armas) {
		this.coordenadaArmas = new ArrayList<>(armas);
	}
}
