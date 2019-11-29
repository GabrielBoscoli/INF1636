package controladores;

import java.io.IOException;
import java.util.ArrayList;

import dominio.Jogador;
import dominio.Tabuleiro;
import gui.Menu;
import gui.PainelArma;
import observer.IObservador;

public class Fachada {
	private static Fachada fachada = null;
	
	private Fachada() {
	}

	public static Fachada getFachada() {
		if(fachada == null) {
			fachada = new Fachada();
		}
		return fachada;
	}
	
	public void iniciarJogo() {
		ControladorJogo.getControladorJogo().iniciarJogo();
	}
	
	public void novoJogo() {
		ControladorJogo.getControladorJogo().reiniciarJogo();
	}
	
	public boolean getVisaoBloqueada() {
		return (boolean) ControladorAtaque.getControladorAtaque().get(3);
	}
	
	public boolean getRodadaEncerrada() {
		return (boolean) ControladorAtaque.getControladorAtaque().get(4);
	}
	
	public Jogador getJogador(int vez) {
		if(vez == 1) {
			return (Jogador) ControladorAtaque.getControladorAtaque().get(8);
		} else {
			return (Jogador) ControladorAtaque.getControladorAtaque().get(9);
		}
	}
	
	public String getResultado() {
		return (String) ControladorAtaque.getControladorAtaque().get(11);
	}
	
	public Tabuleiro getTabuleiroFaseAtaque(int vez) {
		if(vez == 1) {
			return (Tabuleiro) ControladorAtaque.getControladorAtaque().get(1);
		} else {
			return (Tabuleiro) ControladorAtaque.getControladorAtaque().get(2);
		}
	}
	
	public String getNomeJogadorDaVezFaseDeAtaque() {
		return (String) ControladorAtaque.getControladorAtaque().get(10);
	}
	
	public Menu getMenu() {
		return ControladorJogo.getControladorJogo().getMenu();
	}
	
	public String getNomeVencedor() {
		return (String) ControladorAtaque.getControladorAtaque().get(5);
	}
	
	public void botaoFaseAtaqueClicado() {
		ControladorAtaque.getControladorAtaque().botaoClicado();
	}
	
	public void fecharFaseAtaque() {
		ControladorJogo.getControladorJogo().fecharFrameAtaque();
	}
	
	public void tabuleiroFaseAtaqueClicado(Tabuleiro tabuleiro, int coluna, int linha) {
		ControladorAtaque.getControladorAtaque().tabuleiroClicado(tabuleiro, coluna, linha);
	}
	
	public void fecharFrameJogadores(String nomeJogadorUm, String nomeJogadorDois) {
		ControladorJogo.getControladorJogo().fecharFrameJogadores(nomeJogadorUm, nomeJogadorDois);
	}
	
	public Tabuleiro getTabuleiroFasePosicionamento() {
		return (Tabuleiro) ControladorPosicionamento.getControladorPosicionamento().get(2);
	}
	
	public String getNomeJogadorDaVezFasePosicionamento() {
		return (String) ControladorPosicionamento.getControladorPosicionamento().get(5);
	}
	
	public void teclaEscapePressionada() {
		ControladorPosicionamento.getControladorPosicionamento().TeclaEscapePressionada();
	}
	
	public boolean getTabuleiroPosicionamentoPronto() {
		return (boolean) ControladorPosicionamento.getControladorPosicionamento().get(3);
	}
	
	public void botaoTabuleiroProntoClicado() {
		ControladorPosicionamento.getControladorPosicionamento().BotaoTabuleiroProntoClicado();
	}
	
	public void tabuleiroPosicionamentoClicadoComBotaoEsquerdo(int coluna, int linha) {
		ControladorPosicionamento.getControladorPosicionamento().TabuleiroClicadoComBotaoEsquerdo(coluna, linha);
	}
	
	public void tabuleiroPosicionamentoClicadoComBotaoDireito() {
		ControladorPosicionamento.getControladorPosicionamento().TabuleiroClicadoComBotaoDireito();
	}
	
	public void salvarJogo() throws IOException {
		ControladorJogo.getControladorJogo().salvarJogo();
	}
	
	public void recarregarJogo() {
		ControladorJogo.getControladorJogo().recarregarJogo();
	}
	
	public PainelArma getArmaSelecionada() {
		return (PainelArma) ControladorPosicionamento.getControladorPosicionamento().get(1);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<PainelArma> getArmasPosicionadas() {
		return (ArrayList<PainelArma>) ControladorPosicionamento.getControladorPosicionamento().get(4);
	}
	
	public void armaClicada(PainelArma arma) {
		ControladorPosicionamento.getControladorPosicionamento().ArmaClicada(arma);
	}
	
	public void register(IObservador o, String fase) {
		if("ataque".equals(fase)) {
			ControladorAtaque.getControladorAtaque().add(o);
		} else if("posicionamento".equals(fase)) {
			ControladorPosicionamento.getControladorPosicionamento().add(o);
		}
	}
}
