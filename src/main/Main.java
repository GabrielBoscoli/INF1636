package main;

import javax.swing.JFrame;

import controladores.Fachada;

@SuppressWarnings("serial")
public class Main extends JFrame {
	public static void main(String[] args) {
		Fachada.getFachada().iniciarJogo();
	}
}
