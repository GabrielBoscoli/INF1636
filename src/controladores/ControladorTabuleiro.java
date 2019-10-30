package controladores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import observer.IObservado;
import observer.IObservador;
import tabuleiro.Tabuleiro;

public class ControladorTabuleiro implements IObservado {
	List<IObservador> listaObservadores = new ArrayList<IObservador>();
	Tabuleiro tabuleiro = new Tabuleiro();
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public void TabuleiroClicado(int linha, int coluna) {
		tabuleiro.getMatrizCor()[linha][coluna] = Color.red;
		for(IObservador observador  : listaObservadores)
			observador.notify(this);
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
			return tabuleiro;
		else
			return null;
	}
}
