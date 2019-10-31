package controladores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import armas.Arma;
import gui.PainelArma;
import gui.PainelTabuleiro;
import observer.IObservado;
import observer.IObservador;
import tabuleiro.Tabuleiro;

public class ControladorPosicionamento implements IObservado {
	List<IObservador> listaObservadores = new ArrayList<IObservador>();
	static ControladorPosicionamento controlador = null;
	
	PainelArma armaSelecionada = null;
	PainelArma ultimaArmaPosicionada = null;
	
	int numArmas = 15;
	PainelArma[] armasPosicionadas = new PainelArma[numArmas];
	
	Tabuleiro tabuleiro = new Tabuleiro();
	
	private ControladorPosicionamento() {
		for(int i = 0; i < numArmas; i++) {
			armasPosicionadas[i] = null;
		}
	}
	
	public static ControladorPosicionamento getControladorPosicionamento() {
		if(controlador == null)
			controlador = new ControladorPosicionamento();
		
		return controlador;	
	}
	
	public void ArmaClicada(PainelArma arma) {
		if(armaSelecionada == null) {
			armaSelecionada = arma;
			for(IObservador observador  : listaObservadores)
				observador.notify(this);
		}
	}
	
	public void TabuleiroClicado(int linha, int coluna) {
		if(armaSelecionada == null) {
			return;
		}
		
		if(PosicionaArmaSePossivel(linha, coluna)) {
			armaSelecionada = null;//Isso aqui vai mudar
			
			for(IObservador observador : listaObservadores) {
				if(observador instanceof PainelTabuleiro) {
					observador.notify(this);				
				}
			}			
		}

	}
	
	private boolean PosicionaArmaSePossivel(int linha, int coluna) {
		int linhaAjustada = 0;
		int colunaAjustada = 0;
		
		//verifica se a arma pode ser posicionada no tabuleiro
		for(int i = 0; i < armaSelecionada.getArma().getQntdQuadrados(); i++) {
			linhaAjustada = linha + armaSelecionada.getArma().getFormato()[i].getX();
			colunaAjustada = coluna + armaSelecionada.getArma().getFormato()[i].getY();
			
			if(!tabuleiro.CasaEstaVazia(linhaAjustada, colunaAjustada)) { //essa linha deve mudar se mudar a matrizCor da classe tabuleiro
				return false;		
			} else if(!CasasVizinhasVazias(linhaAjustada, colunaAjustada)) {
				return false;
			}
		}
		
		//posiciona a arma no tabuleiro
		for(int i = 0; i < armaSelecionada.getArma().getQntdQuadrados(); i++) {
			linhaAjustada = linha + armaSelecionada.getArma().getFormato()[i].getX();
			colunaAjustada = coluna + armaSelecionada.getArma().getFormato()[i].getY();
			tabuleiro.getMatrizCor()[linhaAjustada][colunaAjustada] = armaSelecionada.getCor();			
		}
		
		return true;
	}

	/**
	 * Verifica se as casas vizinhas à casa da linha e coluna recebidas estao vazias.
	 * @param linha - linha da casa central
	 * @param coluna - coluna da casa central
	 * @return true, se as casas vizinhas estiverem vazias ou se nao existirem.
	 * 		   false, se alguma das casas não estiver vazia.
	 */
	private boolean CasasVizinhasVazias(int linha, int coluna) {
		int linhaVizinha = linha + 1;
		int colunaVizinha = coluna - 1;
		
		for(int i = 0; i < 3; i++) {
			if(true) {}//implementar!
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
			return armaSelecionada;
		else if (i == 2)
			return tabuleiro;
		else
			return null;
	}

}
