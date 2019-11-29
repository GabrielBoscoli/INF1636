package controladores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import dominio.Coordenada;
import dominio.Jogador;
import dominio.Tabuleiro;
import dominio.Tiro;
import gui.Menu;
import gui.PainelArma;
import observer.IObservado;
import observer.IObservador;

class ControladorAtaque implements IObservado {
	List<IObservador> listaObservadores = new ArrayList<IObservador>();
	static ControladorAtaque controlador = null;
	
	final Jogador jogador1;
	final Jogador jogador2;
	String nomeVencedor;
	
	Tabuleiro tabuleiroJogador1;
	Tabuleiro tabuleiroJogador2;
	
	boolean visaoBloqueada;
	boolean rodadaEncerrada;
	
	int vez;
	
	Color tiroCerteiro = Color.red;
	Color tiroErrado = Color.blue;
	int numTiros = 3;
	int tiroAtual;
	
	ArrayList<Tiro> tirosTabuleiro1;
	ArrayList<Tiro> tirosTabuleiro2;
	
	Menu menu;
	
	String resultado;
	
	private ControladorAtaque() {
		tabuleiroJogador1 = new Tabuleiro();
		tabuleiroJogador2 = new Tabuleiro();
		
		tirosTabuleiro1 = new ArrayList<>();
		tirosTabuleiro2 = new ArrayList<>();
		
		jogador1 = ControladorJogo.getControladorJogo().getJogador(1);
		jogador2 = ControladorJogo.getControladorJogo().getJogador(2);
		
		menu = ControladorJogo.getControladorJogo().getMenu();
		menu.ativarSalvamento();
		menu.desativarRecarregamento();
		
		visaoBloqueada = true;
		rodadaEncerrada = false;
		nomeVencedor = null;
		tiroAtual = 1;
		
		vez = 1;
	}
	
	void reiniciarControlador() {
		controlador = null;
	}
	
	static ControladorAtaque getControladorAtaque() {
		if(controlador == null)
			controlador = new ControladorAtaque();
		
		return controlador;	
	}
	
	void tabuleiroClicado(Tabuleiro tabuleiro, int coluna, int linha) {
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
		desAtivarSalvamento();
		return;
	}
	
	void botaoClicado() {
		System.out.println("botaoClicado");
		if(visaoBloqueada) {
			visaoBloqueada = false;
			tiroAtual = 1;
			rodadaEncerrada = false;
			botaArmasNoTabuleiro();
			botaTirosNosTabuleiros();
			botaArmasDestruidasNoTabuleiro();
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
		}
		montaStringResultado(null, false, false);
		desAtivarSalvamento();
		for(IObservador observador : listaObservadores) {
			observador.notify(this);
		}
	}
	

	private void botaArmasDestruidasNoTabuleiro() {
		ArrayList<Coordenada[]> coordenadaArmas;
		ArrayList<PainelArma> armas;
		Tabuleiro tabuleiro;
		int x;
		int y;
		int numJogadores = 2;
	
		armas = jogador1.getArmas();
		tabuleiro = tabuleiroJogador1;
		coordenadaArmas = jogador1.getCoordenadaArmas();
		for(int k = 0; k < numJogadores; k++) {
			for(int i = 0; i < armas.size(); i++) {
				if(armas.get(i).getArma().isDestruida()) {
					Coordenada[] coordenada = coordenadaArmas.get(i);
					for(int j = 0; j < coordenada.length; j++) {
						x = coordenada[j].getX();
						y = coordenada[j].getY();
						tabuleiro.getMatrizCor()[x][y] = Color.BLACK;
					}				
				}
			}
			armas = jogador2.getArmas();
			tabuleiro = tabuleiroJogador2;
			coordenadaArmas = jogador2.getCoordenadaArmas();
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
		
		if(jaFoiDadoTiroNaPosicao(tiros, coluna, linha)) {
			montaStringResultado(null, true, false);
			return;
		}
		
		PainelArma armaAtingida = VerificaSeTemArmaNaPosicao(coluna, linha);
		tiroAtual++;
		if(armaAtingida != null) {
			armaAtingida.getArma().sofreuTiro();
			if(armaAtingida.getArma().isDestruida()) {
				botaArmasDestruidasNoTabuleiro();
				montaStringResultado(armaAtingida.getArma().getTipoArma(), false, true);
			} else {
				tabuleiro.getMatrizCor()[coluna][linha] = tiroCerteiro;
				montaStringResultado(armaAtingida.getArma().getTipoArma(), false, false);
			}
			tiros.add(new Tiro("certeiro", new Coordenada(coluna, linha)));
		} else {
			tabuleiro.getMatrizCor()[coluna][linha] = tiroErrado;
			tiros.add(new Tiro("errado", new Coordenada(coluna, linha)));
			montaStringResultado("água", false, false);
		}
	}

	private boolean jaFoiDadoTiroNaPosicao(ArrayList<Tiro> tiros, int coluna, int linha) {
		for(Tiro tiro: tiros) {
			if(tiro.getCoordenada().getX() == coluna && tiro.getCoordenada().getY() == linha) {
				return true;
			}
		}
		return false;
	}

	private PainelArma VerificaSeTemArmaNaPosicao(int coluna, int linha) {
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
					return jogador.getArmas().get(i);
				}
			}
		}
		return null;
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
	}

	private void botaTirosNosTabuleiros() {
		Tiro tiro;
		Coordenada coordenada;
		for(int i = 0; i < tirosTabuleiro1.size(); i++) {
			tiro = tirosTabuleiro1.get(i);
			coordenada = tiro.getCoordenada();
			if("errado".equals(tiro.getTipo())) {
				tabuleiroJogador1.getMatrizCor()[coordenada.getX()][coordenada.getY()] = tiroErrado;
			} else {
				tabuleiroJogador1.getMatrizCor()[coordenada.getX()][coordenada.getY()] = tiroCerteiro;
			}
		}
		
		for(int i = 0; i < tirosTabuleiro2.size(); i++) {
			tiro = tirosTabuleiro2.get(i);
			coordenada = tiro.getCoordenada();
			if("errado".equals(tiro.getTipo())) {
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
	
	void setTiros(ArrayList<Tiro> tiros, int i) {
		if(i == 1) {
			tirosTabuleiro1 = tiros;
		} else {
			tirosTabuleiro2 = tiros;
		}
	}
	
	void setVez(int vez) {
		this.vez = vez;
	}
	
	private void desAtivarSalvamento() {
		if(visaoBloqueada || tiroAtual == 1) {
			menu.ativarSalvamento();			
		} else {
			menu.desativarSalvamento();
		}
	}

	private void montaStringResultado(String localAtingido, boolean tiroRepetido, boolean afundou) {
		if(visaoBloqueada) {
			resultado = "";			
		} else if(tiroAtual == 1) {
			resultado = numTiros + " tiros restantes.";
		} else if(tiroRepetido) {
			resultado = "Você já atirou nesse local. "  + (numTiros - (tiroAtual - 1)) + " tiro(s) restantes.";
		} else if(afundou) {
			resultado = "Você afundou " + localAtingido + ". " + (numTiros - (tiroAtual - 1)) + " tiro(s) restantes.";
		} else {
			resultado = "Você atingiu " + localAtingido + ". " + (numTiros - (tiroAtual - 1)) + " tiro(s) restantes.";
		}
	}
	
	private String getNomeJogadorDaVez() {
		return ControladorJogo.getControladorJogo().getJogador(vez).getNome();
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
		else if(i == 6)
			return tirosTabuleiro1;
		else if(i == 7)
			return tirosTabuleiro2;
		else if(i == 8)
			return jogador1;
		else if(i == 9)
			return jogador2;
		else if(i == 10)
			return getNomeJogadorDaVez();
		else if(i == 11)
			return resultado;
		else if(i== 12)
			return vez;
		else
			return null;
	}
}
