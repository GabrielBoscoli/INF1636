package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

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
	
	//determina se a arma está posicionada ou nao
	private boolean posicionada;//deve ir para a classe arma?
	
	public PainelArma (Arma arma, Color cor) {
		this.arma = arma;
		this.cor = cor;
		selecionada = false;
		posicionada = false;
		
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
	
	public void ResetaArma() {
		getArma().rotacionaArmaParaPosicaoOriginal();
		selecionada = false;
		posicionada = false;
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

	@SuppressWarnings("unchecked")
	@Override
	public void notify(IObservado observado) {
		//PainelArma painelArma = (PainelArma) ControladorPosicionamento.getControladorPosicionamento().get(1);
		selecionada = ((PainelArma) ControladorPosicionamento.getControladorPosicionamento().get(1)) == this;
		posicionada = ((ArrayList<PainelArma>) ControladorPosicionamento.getControladorPosicionamento().get(4)).contains(this);
		
		if(selecionada == true) {
			setVisible(false);
		} else if (selecionada == false && posicionada == true) {
			setVisible(false);
		} else {
			getArma().rotacionaArmaParaPosicaoOriginal();
			setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON1) {
			ControladorPosicionamento.getControladorPosicionamento().ArmaClicada(this);			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
}
