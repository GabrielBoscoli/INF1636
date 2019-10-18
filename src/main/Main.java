package main;

import javax.swing.JFrame;

import gui.FrameJogadores;
import gui.FramePosicionamento;
import gui.PanelTabuleiro;
import gui.ControleJogo;

@SuppressWarnings("serial")
public class Main extends JFrame {
	public static void main(String[] args) {
		//FramePosicionamento frame = new FramePosicionamento();
		ControleJogo novo = ControleJogo.getMainGamePresenter();
		//frame.setSize(450, 450);
		//frame.setVisible(true);
		novo.initiateGame();
	}
}
