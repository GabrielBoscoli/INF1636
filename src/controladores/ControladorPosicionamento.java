package controladores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import armas.Arma;
import gui.PainelArma;
import observer.IObservado;
import observer.IObservador;
import tabuleiro.Tabuleiro;

public class ControladorPosicionamento implements IObservado {
	List<IObservador> listaObservadores = new ArrayList<IObservador>();
	static ControladorPosicionamento controlador = null;
	
	PainelArma armaSelecionada = null;
	
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
		for(int i = 0; i < armaSelecionada.getArma().getQntdQuadrados(); i++) {
			tabuleiro.getMatrizCor()[linha + armaSelecionada.getArma().getFormato()[i].getX()][coluna - armaSelecionada.getArma().getFormato()[i].getY()] = armaSelecionada.getCor();			
		}
		for(IObservador observador  : listaObservadores) {
			if(observador instanceof Tabuleiro) {
				observador.notify(this);				
			}
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
		else
			return null;
	}

}
