package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import armas.*;
import outros.*;

@SuppressWarnings("serial")
public class PainelArma extends JPanel {

	//arma
	private Arma arma;
	
	//tamanho de um quadrado da arma
	private int tamanhoQuadrado = 25;
	
	//cor da arma
	private Color cor;
	
	PainelArma (Arma arma, Color cor) {
		this.arma = arma;
		this.cor = cor;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D retangulo;
		
		for (int i = 0; i < arma.getQntdQuadrados(); i++) {
			retangulo = new Rectangle2D.Double(arma.getFormato()[i].getX() * tamanhoQuadrado, arma.getFormato()[i].getY() * tamanhoQuadrado, tamanhoQuadrado, tamanhoQuadrado);
			g2d.setPaint(this.cor);
			g2d.fill(retangulo);
			g2d.setPaint(Color.black);
			g2d.draw(retangulo);
		}
	}

	public int getTamanhoQuadrado() {
		return tamanhoQuadrado;
	}
	
}
