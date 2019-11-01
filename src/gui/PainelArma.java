package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import armas.*;
import controladores.ControladorPosicionamento;
import observer.IObservado;
import observer.IObservador;

@SuppressWarnings("serial")
public class PainelArma extends JPanel implements MouseListener, IObservador{

	//arma
	private Arma arma;
	
	//tamanho de um quadrado da arma
	private int tamanhoQuadrado = 25;
	
	//cor da arma
	private Color cor;
	
	//determina se a arma está selecionada ou nao
	private boolean selecionada;//deve ir para a classe arma?
	
	PainelArma (Arma arma, Color cor) {
		this.arma = arma;
		this.cor = cor;
		selecionada = false;
		
		addMouseListener(this);
		ControladorPosicionamento.getControladorPosicionamento().add(this);
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
	
	public Arma getArma() {
		return arma;
	}
	
	public Color getCor() {
		return cor;
	}

	@Override
	public void notify(IObservado observado) {
		PainelArma painelArma = (PainelArma) ControladorPosicionamento.getControladorPosicionamento().get(1);
		
		if(painelArma == this) {
			setVisible(false);
			selecionada = true;
		} else if (painelArma == null && selecionada == true) {
			setVisible(true);
			selecionada = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		ControladorPosicionamento.getControladorPosicionamento().ArmaClicada(this);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
}
