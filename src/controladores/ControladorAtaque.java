package controladores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import dominio.Jogador;
import dominio.Tabuleiro;
import dominio.Tiro;
import gui.PainelArma;
import observer.IObservado;
import observer.IObservador;
import outros.Coordenada;

public class ControladorAtaque implements IObservado {
	List<IObservador> listaObservadores = new ArrayList<IObservador>();
	static ControladorAtaque controlador = null;
	
	Jogador jogador1;
	Jogador jogador2;
	String nomeVencedor = null;
	
	Tabuleiro tabuleiroJogador1;
	Tabuleiro tabuleiroJogador2;
	
	boolean visaoBloqueada = true;
	boolean rodadaEncerrada = false;
	
	int vez = 1;
	
	Color tiroCerteiro = Color.red;
	Color tiroErrado = Color.blue;
	int numTiros = 3;
	int tiroAtual = 1;
	
	ArrayList<Tiro> tirosTabuleiro1;
	ArrayList<Tiro> tirosTabuleiro2;
	
	private ControladorAtaque() {
		tabuleiroJogador1 = new Tabuleiro();
		tabuleiroJogador2 = new Tabuleiro();
		
		tirosTabuleiro1 = new ArrayList<>();
		tirosTabuleiro2 = new ArrayList<>();
		
		jogador1 = ControladorJogo.getMainGamePresenter().getJogador(1);
		jogador2 = ControladorJogo.getMainGamePresenter().getJogador(2);
	}
	
	public static ControladorAtaque getControladorAtaque() {
		if(controlador == null)
			controlador = new ControladorAtaque();
		
		return controlador;	
	}
	
	public void tabuleiroClicado(Tabuleiro tabuleiro, int coluna, int linha) {
		System.out.println("tabuleiroClicado");
		if(visaoBloqueada) {
			return;
		}
		if(tiroAtual > numTiros) {
			return;
		}
		if((vez == 1 && tabuleiro == tabuleiroJogador2) || (vez == 2 && tabuleiro == tabuleiroJogador1)) {
			Atira(coluna, linha);
			VerificaVencedor();
			if(tiroAtual > numTiros) {
				rodadaEncerrada = true;
			}
			for(IObservador observador : listaObservadores) {
				observador.notify(this);
			}
		}
		return;
	}
	
	public void botaoClicado() {
		System.out.println("botaoClicado");
		if(visaoBloqueada) {
			visaoBloqueada = false;
			tiroAtual = 1;
			rodadaEncerrada = false;
			botaArmasNoTabuleiro();
			botaTirosNosTabuleiros();
			for(IObservador observador : listaObservadores) {
				observador.notify(this);
			}
		} else if(rodadaEncerrada) {
			rodadaEncerrada = false;
			visaoBloqueada = true;
			tiroAtual = 1;
			if(vez == 1) {
				vez = 2;
			} else {
				vez = 1;
			}
			tabuleiroJogador1.ResetaTabuleiro();
			tabuleiroJogador2.ResetaTabuleiro();
			for(IObservador observador : listaObservadores) {
				observador.notify(this);
			}
		}
	}
	

	private void Atira(int coluna, int linha) {
		Tabuleiro tabuleiro;
		ArrayList<Tiro> tiros;
		if(vez == 1) {
			tabuleiro = tabuleiroJogador2;
			tiros = tirosTabuleiro2;
		} else {
			tabuleiro = tabuleiroJogador1;
			tiros = tirosTabuleiro1;
		}
		
		if(VerificaSeTemArmaNaPosicao(coluna, linha)) {
			tabuleiro.getMatrizCor()[coluna][linha] = tiroCerteiro;
			tiros.add(new Tiro("certeiro", new Coordenada(coluna, linha)));
		} else {
			tabuleiro.getMatrizCor()[coluna][linha] = tiroErrado;
			tiros.add(new Tiro("errado", new Coordenada(coluna, linha)));
		}
		
		tiroAtual++;
	}

	private boolean VerificaSeTemArmaNaPosicao(int coluna, int linha) {
		Jogador jogador;
		if(vez == 1) {
			jogador = jogador2;
		} else {
			jogador = jogador1;
		}
		
		ArrayList<Coordenada[]> coordenadas = jogador.getCoordenadaArmas();
		for(int i = 0; i < coordenadas.size(); i++) {
			Coordenada[] coordenada = coordenadas.get(i);
			for(int j = 0; j < coordenada.length; j++) {
				if(coordenada[j].getX() == coluna && coordenada[j].getY() == linha) {
					return true;
				}
			}
		}
		return false;
	}

	private void botaArmasNoTabuleiro() {
		ArrayList<Coordenada[]> coordenadaArmas;
		ArrayList<PainelArma> armas;
		Tabuleiro tabuleiro;
		
		if(vez == 1) {
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

	private void botaTirosNosTabuleiros() {
		Tiro tiro;
		Coordenada coordenada;
		for(int i = 0; i < tirosTabuleiro1.size(); i++) {
			tiro = tirosTabuleiro1.get(i);
			coordenada = tiro.getCoordenada();
			if(tiro.getTipo() == "errado") {
				tabuleiroJogador1.getMatrizCor()[coordenada.getX()][coordenada.getY()] = tiroErrado;
			} else {
				tabuleiroJogador1.getMatrizCor()[coordenada.getX()][coordenada.getY()] = tiroCerteiro;
			}
		}
		
		for(int i = 0; i < tirosTabuleiro2.size(); i++) {
			tiro = tirosTabuleiro2.get(i);
			coordenada = tiro.getCoordenada();
			if(tiro.getTipo() == "errado") {
				tabuleiroJogador2.getMatrizCor()[coordenada.getX()][coordenada.getY()] = tiroErrado;
			} else {
				tabuleiroJogador2.getMatrizCor()[coordenada.getX()][coordenada.getY()] = tiroCerteiro;
			}
		}
	}
	
	private boolean VerificaVencedor() {
		Jogador jogador;
		ArrayList<Tiro> tiros;
		if(vez == 1) {
			jogador = jogador2;
			tiros = tirosTabuleiro2;
		} else {
			jogador = jogador1;
			tiros = tirosTabuleiro1;
		}
		ArrayList<Coordenada[]> armas = jogador.getCoordenadaArmas();
		boolean armaAtirada = false;
		
		for(int i = 0; i < armas.size(); i++) {
			for(int j = 0; j < armas.get(i).length; j++) {
				Coordenada coordenada = armas.get(i)[j];
				for(int k = 0; k < tiros.size(); k++) {
					if(coordenada.getX() == tiros.get(k).getCoordenada().getX()) {
						if(coordenada.getY() == tiros.get(k).getCoordenada().getY()) {
							armaAtirada = true;
							break;
						}
					}
				}
				if(armaAtirada == false) {
					return false;
				} else {
					armaAtirada = false;
				}
			}
		}

		if(jogador == jogador1) {
			nomeVencedor = jogador2.getNome();
		} else {
			nomeVencedor = jogador1.getNome();
		}
		return true;
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
		else if(i == 3)
			return visaoBloqueada;
		else if(i == 4)
			return rodadaEncerrada;
		else if(i == 5)
			return nomeVencedor;
		else
			return null;
	}
}
