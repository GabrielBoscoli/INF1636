package gui;

import javax.swing.*;

import controladores.Fachada;
import observer.IObservado;
import observer.IObservador;
import dominio.Tabuleiro;

import java.awt.*;
import java.awt.geom.*;

/**
 * Classe responsável pelo desenho do tabuleiro
 * @author Gabriel Boscoli
 *
 */
@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel implements IObservador {

	private int tamanhoQuadrado = 25;
	Tabuleiro tabuleiro = new Tabuleiro();
	
	public PainelTabuleiro() {
		Fachada.getFachada().register(this, "posicionamento");
		this.setLayout(null);
		this.setDoubleBuffered(true);
		
		//define coordenadas das colunas
		for (int i = 1 ; i <= tabuleiro.getNumColunas(); i++){
			JPanel coordColuna = new JPanel();
			Label labelColuna = new Label("" + (char)(i+'A'-1));
			coordColuna.setSize(tamanhoQuadrado, tamanhoQuadrado);
			coordColuna.setLocation(0, i * tamanhoQuadrado);
			coordColuna.add(labelColuna);
			this.add(coordColuna);
		}
		
		//define coordenadas das linhas
		for (int i = 1; i <= tabuleiro.getNumLinhas(); i++) {
			JPanel coordLinha = new JPanel();
			Label labelLinha = new Label(String.valueOf(i));
			coordLinha.setSize(tamanhoQuadrado, tamanhoQuadrado);
			coordLinha.setLocation(i * tamanhoQuadrado, 0);
			coordLinha.add(labelLinha);
			this.add(coordLinha);
		}
		
		//addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		int linha, coluna;
		
		//desenhando o tabuleiro
		for (coluna=1; coluna <= tabuleiro.getNumColunas(); coluna++){
			for (linha=1; linha <= tabuleiro.getNumLinhas(); linha++){
				Rectangle2D retangulo = new Rectangle2D.Double(tamanhoQuadrado * coluna, tamanhoQuadrado * linha, tamanhoQuadrado, tamanhoQuadrado);
				g2d.setPaint(tabuleiro.getMatrizCor()[coluna-1][linha-1]);
				g2d.fill(retangulo);
				g2d.setPaint(Color.black);
				g2d.draw(retangulo);
			}
		}
	}
	
	public int getTamanhoQuadrado() {
		return tamanhoQuadrado;
	}
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	@Override
	public void notify(IObservado observado) {
		repaint();
	}
}