package controladores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import dominio.Jogador;
import dominio.Tabuleiro;
import gui.PainelArma;
import observer.IObservado;
import observer.IObservador;
import outros.Coordenada;

public class ControladorAtaque implements IObservado {
	List<IObservador> listaObservadores = new ArrayList<IObservador>();
	static ControladorAtaque controlador = null;
	
	Jogador jogador1;
	Jogador jogador2;
	
	Tabuleiro tabuleiroJogador1;
	Tabuleiro tabuleiroJogador2;
	
	int vez = 1;
	
	Color tiroCerteiro = Color.red;
	Color tiroErrado = Color.blue;
	int numTiros = 3;
	int tiroAtual = 1;
	
	Color[] tirosJogador1;
	Color[] tirosJogador2;
	
	private ControladorAtaque() {
		tabuleiroJogador1 = new Tabuleiro();
		tabuleiroJogador2 = new Tabuleiro();
		
		jogador1 = ControladorJogo.getMainGamePresenter().getJogador(1);
		jogador2 = ControladorJogo.getMainGamePresenter().getJogador(2);
	}
	
	public static ControladorAtaque getControladorAtaque() {
		if(controlador == null)
			controlador = new ControladorAtaque();
		
		return controlador;	
	}
	
	public void tabuleiroClicado(Tabuleiro tabuleiro, int coluna, int linha) {//falta adicionar os tiros na lista de tiros
		System.out.println("tabuleiroClicado");
		if(tiroAtual > numTiros) {
			return;
		}
		if((vez == 1 && tabuleiro == tabuleiroJogador2) || (vez == 2 && tabuleiro == tabuleiroJogador1)) {
			tabuleiro.getMatrizCor()[coluna][linha] = tiroErrado;
			tiroAtual++;
			for(IObservador observador : listaObservadores) {
				observador.notify(this);
			}
		}
		return;
	}
	
	public void botaoClicado() {
		System.out.println("botaoClicado");
		if(vez == 1) {
			botaArmasNoTabuleiro(vez);
		}
	}

	private void botaArmasNoTabuleiro(int jogador) {
		ArrayList<Coordenada[]> coordenadaArmas = null;
		ArrayList<PainelArma> armas = null;
		Tabuleiro tabuleiro = null;
		
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
				tabuleiro.getMatrizCor()[x][y] = armas.get(i).getCor();
			}
		}
		
		for(IObservador observador : listaObservadores) {
			observador.notify(this);
		}
	}

	@Override
	public void add(IObservador observador) {
		listaObservadores.add(observador);
	}

	@Override
	public void remove(IObservador observador) {
		listaObservadores.remove(observador);
	}

	@Override
	public Object get(int i) {
		if(i == 1)
			return tabuleiroJogador1;
		else if(i == 2)
			return tabuleiroJogador2;
		else
			return null;
	}
}
