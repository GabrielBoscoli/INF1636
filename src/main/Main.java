package main;

import javax.swing.JFrame;

import controladores.Fachada;
import gui.FrameAtaque;
import gui.FrameJogadores;
import gui.FramePosicionamento;
import gui.PainelTabuleiro;

@SuppressWarnings("serial")
public class Main extends JFrame {
	public static void main(String[] args) {
		///FramePosicionamento frame = new FramePosicionamento();
		Fachada.getFachada().iniciarJogo();
		//frame.setSize(450, 450);
		//frame.setVisible(true);
			//FrameAtaque frame = new FrameAtaque();
		//frame.setSize(1800, 1300);
			//frame.setVisible(true);
	}
}
