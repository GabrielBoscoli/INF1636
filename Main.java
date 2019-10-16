package main;

import javax.swing.JFrame;

import gui.FrameJogadores;
import gui.FramePosicionamento;
import gui.PanelTabuleiro;

@SuppressWarnings("serial")
public class Main extends JFrame {
	public static void main(String[] args) {
		FramePosicionamento frame = new FramePosicionamento();
		frame.setSize(450, 450);
		frame.setVisible(true);
	}
}
