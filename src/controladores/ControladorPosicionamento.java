package controladores;

import java.util.ArrayList;
import java.util.List;

import gui.*;
import observer.IObservado;
import observer.IObservador;
import dominio.Coordenada;
import dominio.Tabuleiro;


class ControladorPosicionamento implements IObservado {
	List<IObservador> listaObservadores = new ArrayList<IObservador>();
	static ControladorPosicionamento controlador = null;
	
	PainelArma armaSelecionada = null;
	PainelArma ultimaArmaPosicionada = null;
	Coordenada coordenadaUltimaArmaPosicionada = new Coordenada(-1, -1);
	
	int numArmas = 15;
	List<PainelArma> armasPosicionadas = new ArrayList<PainelArma>();
	List<Coordenada[]> coordenadaArmasPosicionadas = new ArrayList<Coordenada[]>();
	
	Tabuleiro tabuleiro = new Tabuleiro();
	boolean tabuleiroPronto = false;
	
	int vez = 1;
	
	Menu menu;
	
	private ControladorPosicionamento() {
		menu = ControladorJogo.getControladorJogo().getMenu();
		menu.ativarRecarregamento();
		menu.desativarSalvamento();
	}
	
	void reiniciarControlador() {
		controlador = null;
	}
	
	static ControladorPosicionamento getControladorPosicionamento() {
		if(controlador == null)
			controlador = new ControladorPosicionamento();
		
		return controlador;	
	}
	
	void ArmaClicada(PainelArma arma) {
		System.out.println("ArmaClicada");
		if(armaSelecionada == null) {
			armaSelecionada = arma;
			for(IObservador observador : listaObservadores) {
				observador.notify(this);					
			}
		}
	}
	
	void BotaoTabuleiroProntoClicado() {
		System.out.println("BotaoTabuleiroProntoClicado");
		clonaArmas();
		ControladorJogo.getControladorJogo().setArmasJogador((ArrayList<PainelArma>) armasPosicionadas, vez);
		ControladorJogo.getControladorJogo().setCoordenadaArmasJogador((ArrayList<Coordenada[]>) coordenadaArmasPosicionadas, vez);
		if(vez == 1) {
			ResetaDados();
			vez++;
			for(IObservador observador : listaObservadores) {
				observador.notify(this);					
			}
		} else if(vez == 2) {
			ControladorJogo.getControladorJogo().fecharFramePosicionamento();
		}
	}
	
	private void ResetaDados() {
		tabuleiro.ResetaTabuleiro();
		for(int i = 0; i < armasPosicionadas.size(); i++) {
			armasPosicionadas.get(i).ResetaArma();
		}
		armasPosicionadas.clear();
		coordenadaArmasPosicionadas.clear();
		armaSelecionada = null;
		ultimaArmaPosicionada = null;
		tabuleiroPronto = false;
	}
	
	void TeclaEscapePressionada() {
		System.out.println("TeclaEscapePressionada");
		PainelArma exArmaSelecionada = armaSelecionada;
		armaSelecionada = null;
		if(exArmaSelecionada != null && exArmaSelecionada != ultimaArmaPosicionada) {
			for(IObservador observador : listaObservadores) {
				observador.notify(this);
			}
		}
		if(VerificaTabuleiroPronto() && tabuleiroPronto == false) {
			tabuleiroPronto = true;
			for(IObservador observador : listaObservadores) {
				observador.notify(this);
			}
		}
	}
	
	private boolean VerificaTabuleiroPronto() {
		if(armasPosicionadas.size() == numArmas) {
			return true;
		}
		return false;
	}

	void TabuleiroClicadoComBotaoDireito() {
		System.out.println("TabuleiroClicadoComBotaoDireito");
		if(armaSelecionada == null || armaSelecionada != ultimaArmaPosicionada) {
			return;
		}

		int numeroRotacoes = armaSelecionada.getArma().getQntdRotacoes();
		int coluna = coordenadaUltimaArmaPosicionada.getX();
		int linha = coordenadaUltimaArmaPosicionada.getY();

		RetiraArma(coluna, linha);
		for(int i = 0; i < numeroRotacoes; i++) {
			armaSelecionada.getArma().rotacionaArma();
			if(PosicionaArmaSePossivel(coluna, linha)) {
				for(IObservador observador : listaObservadores) {
					observador.notify(this);				
				}
				return;
			}
		}
	}
	
	private boolean RetiraArma(int coluna, int linha) {
		int linhaAjustada = 0;
		int colunaAjustada = 0;
		
		for(int i = 0; i < armaSelecionada.getArma().getQntdQuadrados(); i++) {
			colunaAjustada = coluna + armaSelecionada.getArma().getFormato()[i].getX();
			linhaAjustada = linha + armaSelecionada.getArma().getFormato()[i].getY();
			tabuleiro.EsvaziaCasa(colunaAjustada, linhaAjustada);
		}
		
		if(armasPosicionadas.contains(armaSelecionada)) {
			int i = armasPosicionadas.lastIndexOf(armaSelecionada);
			armasPosicionadas.remove(i);
			coordenadaArmasPosicionadas.remove(i);
		}
		return true;
	}
	
	private boolean RetiraArma(int i) {
		if(i >= coordenadaArmasPosicionadas.size() || i < 0) {
			return false;
		}
		Coordenada[] coordenada = coordenadaArmasPosicionadas.get(i);
		for(int j = 0; j < coordenada.length; j++) {
			tabuleiro.EsvaziaCasa(coordenada[j].getX(), coordenada[j].getY());
		}
		armasPosicionadas.remove(i);
		coordenadaArmasPosicionadas.remove(i);
		return true;
	}
	
	void TabuleiroClicadoComBotaoEsquerdo(int coluna, int linha) {
		System.out.println("TabuleiroClicadoComBotaoEsquerdo");
		boolean reposicionamento = false;
		if(armaSelecionada == null) {
			if(tabuleiro.CasaEstaVazia(coluna, linha)) {
				return;				
			} else {
				int i = AchaArma(coluna, linha);
				if(armasPosicionadas.get(i) == ultimaArmaPosicionada) {
					ultimaArmaPosicionada = null;
				}
				RetiraArma(i);
				if(VerificaTabuleiroPronto() == false && tabuleiroPronto == true) {
					tabuleiroPronto = false;
				}
				for(IObservador observador : listaObservadores) {
					observador.notify(this);
				}
				desAtivarRecarregamento();
				return;
			}
		}
		
		if(armaSelecionada == ultimaArmaPosicionada) {
			RetiraArma(coordenadaUltimaArmaPosicionada.getX(), coordenadaUltimaArmaPosicionada.getY());
			reposicionamento = true;
		}
		if(PosicionaArmaSePossivel(coluna, linha)) {
			for(IObservador observador : listaObservadores) {
				observador.notify(this);				
			}
		} else if (reposicionamento) {
			PosicionaArmaSePossivel(coordenadaUltimaArmaPosicionada.getX(), coordenadaUltimaArmaPosicionada.getY());
		}

		desAtivarRecarregamento();
	}
	
	/**
	 * Retorna o indice da arma no vetor armasPosicionadas que está na coluna
	 * e linha especificada.
	 * @param coluna - coluna da casa que algum quadrado da arma ocupa
	 * @param linha - linha da casa que algum quadrado da arma ocupa
	 * @return o indice de onde se encontra a arma no vetor armasPosicionadas
	 */
	private int AchaArma(int coluna, int linha) {
		for(int i = 0; i < coordenadaArmasPosicionadas.size(); i++) {
			for(int j = 0; j < coordenadaArmasPosicionadas.get(i).length; j++ ) {
				if(coluna == coordenadaArmasPosicionadas.get(i)[j].getX()) {
					if(linha == coordenadaArmasPosicionadas.get(i)[j].getY()) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	private boolean PosicionaArmaSePossivel(int coluna, int linha) {
		int linhaAjustada = 0;
		int colunaAjustada = 0;
		
		Coordenada[] coordenada = new Coordenada[armaSelecionada.getArma().getQntdQuadrados()];
		if(VerificaSePodePosicionarArma(coluna, linha)) {
			for(int i = 0; i < armaSelecionada.getArma().getQntdQuadrados(); i++) {
				colunaAjustada = coluna + armaSelecionada.getArma().getFormato()[i].getX();
				linhaAjustada = linha + armaSelecionada.getArma().getFormato()[i].getY();
				tabuleiro.getMatrizCor()[colunaAjustada][linhaAjustada] = armaSelecionada.getCor();
				coordenada[i] = new Coordenada(colunaAjustada, linhaAjustada);
			}
		} else {
			return false;
		}
		
		coordenadaUltimaArmaPosicionada.setX(coluna);
		coordenadaUltimaArmaPosicionada.setY(linha);
		ultimaArmaPosicionada = armaSelecionada;
		armasPosicionadas.add(ultimaArmaPosicionada);
		coordenadaArmasPosicionadas.add(coordenada);
		return true;
	}
	
	private boolean VerificaSePodePosicionarArma(int coluna, int linha) {
		int linhaAjustada = 0;
		int colunaAjustada = 0;
		
		//verifica se a arma pode ser posicionada no tabuleiro
		for(int i = 0; i < armaSelecionada.getArma().getQntdQuadrados(); i++) {
			colunaAjustada = coluna + armaSelecionada.getArma().getFormato()[i].getX();
			linhaAjustada = linha + armaSelecionada.getArma().getFormato()[i].getY();
			
			if(!tabuleiro.CasaEstaVazia(colunaAjustada, linhaAjustada)) {
				return false;		
			} else if(!CasasVizinhasVazias(colunaAjustada, linhaAjustada)) {
				return false;
			}
		}

		return true;
	}
	
	private void desAtivarRecarregamento() {
		if(vez == 1 && armasPosicionadas.isEmpty()) {
			menu.ativarRecarregamento();
		} else {
			menu.desativarRecarregamento();
		}
	}

	/**
	 * Verifica se as casas vizinhas � casa da linha e coluna recebidas estao vazias.
	 * @param linha - linha da casa central
	 * @param coluna - coluna da casa central
	 * @return true, se as casas vizinhas estiverem vazias ou se nao existirem.<br>
	 * 		   false, se alguma das casas n�o estiver vazia.
	 */
	private boolean CasasVizinhasVazias(int coluna, int linha) {
		int colunaVizinha;
		int linhaVizinha;
		int sinal = 1;
		
		for(int j = 0; j < 2; j++) {
			colunaVizinha = coluna - (1 * sinal);
			linhaVizinha = linha + (1 * sinal);
			
			for(int i = 0; i < 3; i++) {
				if(linhaVizinha < tabuleiro.getNumLinhas() && linhaVizinha >= 0 && colunaVizinha < tabuleiro.getNumColunas() && colunaVizinha >= 0) {
					if(!tabuleiro.CasaEstaVazia(colunaVizinha, linhaVizinha)) {
						return false;
					}
				}
				linhaVizinha -= 1 * sinal;
			}
			
			linhaVizinha = linha - (1 * sinal);
	
			for(int i = 0; i < 3; i++) {
				if(linhaVizinha < tabuleiro.getNumLinhas() && linhaVizinha >= 0 && colunaVizinha < tabuleiro.getNumColunas() && colunaVizinha >= 0) {
					if(!tabuleiro.CasaEstaVazia(colunaVizinha, linhaVizinha)) {
						return false;
					}
				}
				colunaVizinha += 1 * sinal;
			}
	
			sinal = - sinal;
		}
		return true;
	}
	
	private String getNomeJogadorDaVez() {
		return ControladorJogo.getControladorJogo().getJogador(vez).getNome();
	}
	
	private void clonaArmas() {
		for(int i = 0; i < armasPosicionadas.size(); i++) {
			armasPosicionadas.set(i, new PainelArma(armasPosicionadas.get(i).getArma().clonaArma(), armasPosicionadas.get(i).getCor()));
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
			return armaSelecionada;
		else if(i == 2)
			return tabuleiro;
		else if(i == 3)
			return tabuleiroPronto;
		else if(i == 4)
			return armasPosicionadas;
		else if(i == 5)
			return getNomeJogadorDaVez();
		else if(i == 6)
			return vez;
		else
			return null;
	}
	
}
