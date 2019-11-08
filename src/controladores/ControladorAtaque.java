package controladores;

import java.awt.Color;
import java.util.ArrayList;

import dominio.Jogador;
import gui.PainelArma;
import gui.PainelTabuleiro;
import observer.IObservado;
import observer.IObservador;
import outros.Coordenada;

public class ControladorAtaque implements IObservado {

	static ControladorAtaque controlador = null;
	
	Jogador jogador1;
	Jogador jogador2;
	
	PainelTabuleiro tabuleiroJogador1;
	PainelTabuleiro tabuleiroJogador2;
	
	int vez;
	
	Color tiroCerteiro = Color.red;
	Color tiroErrado = Color.blue;
	int numTiros = 3;
	
	Color[] tirosJogador1;
	Color[] tirosJogador2;
	
	private ControladorAtaque() {}
	
	public static ControladorAtaque getControladorAtaque() {
		if(controlador == null)
			controlador = new ControladorAtaque();
		
		return controlador;	
	}
	
	public void botaoClicado() {
		if(vez == 1) {
			arrumaTabuleiroComArmas(vez);
		}
	}

	private void arrumaTabuleiroComArmas(int jogador) {
		ArrayList<Coordenada[]> coordenadaArmas = null;
		ArrayList<PainelArma> armas = null;
		PainelTabuleiro tabuleiro = null;
		
		if(jogador == 1) {
			armas = jogador1.getArmas();
			tabuleiro = tabuleiroJogador1;
			coordenadaArmas = jogador1.getCoordenadaArmas();
		} else {
			armas = jogador2.getArmas();
			tabuleiro = tabuleiroJogador2;
			coordenadaArmas = jogador2.getCoordenadaArmas();
		}
		
		int x;
		int y;
		for(int i = 0; i < armas.size(); i++) {
			Coordenada[] coordenada = coordenadaArmas.get(i);
			for(int j = 0; j < coordenada.length; j++) {
				x = coordenada[j].getX();
				y = coordenada[j].getY();
				tabuleiro.getTabuleiro().getMatrizCor()[x][y] = armas.get(i).getCor();
			}
		}
	}

	@Override
	public void add(IObservador observador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(IObservador observador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
