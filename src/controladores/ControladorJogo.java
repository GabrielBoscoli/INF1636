package controladores;

import java.awt.event.WindowEvent;
import java.util.ArrayList;

import dominio.Jogador;
import gui.FrameAtaque;
import gui.FrameJogadores;
import gui.FramePosicionamento;
import gui.PainelArma;
import outros.Coordenada;

public class ControladorJogo {

	private static ControladorJogo gamePresenter = null;
	private FramePosicionamento framePosicionamento;
	private FrameJogadores frameJogadores;
	private FrameAtaque frameAtaque;
	private Jogador jogador1 = new Jogador();
	private Jogador jogador2 = new Jogador();
	
	private ControladorJogo() {
	}
	
	public static ControladorJogo getMainGamePresenter() {
		if(gamePresenter == null) {
			gamePresenter = new ControladorJogo();
		}
		return gamePresenter;
	}
	
	public void iniciarJogo() { 
		frameJogadores = new FrameJogadores();
		frameJogadores.setTitle("Batalha Naval"); 
		frameJogadores.setVisible(true);
	}
	
	public void fecharFrameJogadores(String nomeJogador1, String nomeJogador2) {
		setNomeJogador(nomeJogador1, 1);
		setNomeJogador(nomeJogador2, 2);
		frameJogadores.setVisible(false);
		framePosicionamento = new FramePosicionamento();
		framePosicionamento.setTitle("Batalha Naval");
		framePosicionamento.setVisible(true);
	}
	
	public void fecharFramePosicionamento() {
		framePosicionamento.setVisible(false);
		frameAtaque = new FrameAtaque();
		frameAtaque.setTitle("Batalha Naval");
		frameAtaque.setVisible(true);
	}
	
	public void fecharFrameAtaque() {
		frameAtaque.dispatchEvent(new WindowEvent(frameAtaque, WindowEvent.WINDOW_CLOSING));
	}
	
	public FramePosicionamento getFramePosicionamento() {
		return framePosicionamento;
	}
	
	public void setNomeJogador(String nome, int numJogador) {
		if(numJogador == 1) {
			jogador1.setNome(nome);
		} else if(numJogador == 2) {
			jogador2.setNome(nome);
		}
	}
	
	public void setArmasJogador(ArrayList<PainelArma> armas, int numJogador) {
		if(numJogador == 1) {
			jogador1.setArmas(armas);
		} else if(numJogador == 2) {
			jogador2.setArmas(armas);
		}
	}
	
	public void setCoordenadaArmasJogador(ArrayList<Coordenada[]> coordenadaArmas, int numJogador) {
		if(numJogador == 1) {
			jogador1.setCoordenadaArmas(coordenadaArmas);
		} else if(numJogador == 2) {
			jogador2.setCoordenadaArmas(coordenadaArmas);
		}
	}
	
	public Jogador getJogador(int numJogador) {
		if(numJogador == 1) {
			return jogador1;
		} else if(numJogador == 2) {
			return jogador2;
		}
		return null;
	}
	
}