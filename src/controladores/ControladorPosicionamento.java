package controladores;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import gui.*;
import observer.IObservado;
import observer.IObservador;
import tabuleiro.Tabuleiro;


public class ControladorPosicionamento implements ActionListener , IObservado {
	List<IObservador> listaObservadores = new ArrayList<IObservador>();
	static ControladorPosicionamento controlador = null;
	
	PainelArma armaSelecionada = null;
	PainelArma ultimaArmaPosicionada = null;
			
	int numArmas = 15;
	int armasTotal = 0;
	private JButton confirmar = FramePosicionamento.getBotaoConfirmacao();
	
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
	
	public void TabuleiroClicado(int coluna, int linha) {
		if(armaSelecionada == null) {
			return;
		}
		
		if(PosicionaArmaSePossivel(coluna, linha)) {
			armaSelecionada = null;//Isso aqui vai mudar
			
			for(IObservador observador : listaObservadores) {
				if(observador instanceof PainelTabuleiro) {
					observador.notify(this);				
				}
			}			
		}

	}
	
	private boolean PosicionaArmaSePossivel(int coluna, int linha) {
		int linhaAjustada = 0;
		int colunaAjustada = 0;
		
		//verifica se a arma pode ser posicionada no tabuleiro
		for(int i = 0; i < armaSelecionada.getArma().getQntdQuadrados(); i++) {
			colunaAjustada = coluna + armaSelecionada.getArma().getFormato()[i].getX();
			linhaAjustada = linha + armaSelecionada.getArma().getFormato()[i].getY();
			
			if(!tabuleiro.CasaEstaVazia(colunaAjustada, linhaAjustada)) { //essa linha deve mudar se mudar a matrizCor da classe tabuleiro
				return false;		
			} else if(!CasasVizinhasVazias(colunaAjustada, linhaAjustada)) {
				return false;
			}
		}
		
		//posiciona a arma no tabuleiro
		for(int i = 0; i < armaSelecionada.getArma().getQntdQuadrados(); i++) {
			colunaAjustada = coluna + armaSelecionada.getArma().getFormato()[i].getX();
			linhaAjustada = linha + armaSelecionada.getArma().getFormato()[i].getY();
			tabuleiro.getMatrizCor()[colunaAjustada][linhaAjustada] = armaSelecionada.getCor();
		}
		
		armasTotal++;
		if (armasTotal == 15) {
			confirmar.setEnabled(true);
			confirmar.addActionListener(this);
		}
		return true;
	}

	/**
	 * Verifica se as casas vizinhas à casa da linha e coluna recebidas estao vazias.
	 * @param linha - linha da casa central
	 * @param coluna - coluna da casa central
	 * @return true, se as casas vizinhas estiverem vazias ou se nao existirem.<br>
	 * 		   false, se alguma das casas não estiver vazia.
	 */
	private boolean CasasVizinhasVazias(int coluna, int linha) {
		int colunaVizinha;
		int linhaVizinha;
		int sinal = 1;
		
		for(int j = 0; j < 2; j++) {
			colunaVizinha = coluna - (1 * sinal);
			linhaVizinha = linha + (1 * sinal);
			//tabuleiro.getMatrizCor()[colunaVizinha][linhaVizinha] = Color.red;
			
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		ControleJogo novo = ControleJogo.getMainGamePresenter();
		
		
		novo.closePositioning();
		//novo.showPositioning();
	}
	
	
}
