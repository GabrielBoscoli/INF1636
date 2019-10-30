package gui;

import javax.swing.*;

import controladores.ControladorTabuleiro;
import observer.IObservado;
import observer.IObservador;
import tabuleiro.Tabuleiro;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;

/**
 * Classe responsável pelo desenho do tabuleiro
 * @author Gabriel Boscoli
 *
 */
@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel implements MouseListener, IObservador {

	private int tamanhoQuadrado = 25;
	Tabuleiro tabuleiro;
	ControladorTabuleiro controlador = new ControladorTabuleiro();
	
	public PainelTabuleiro() {
		tabuleiro = controlador.getTabuleiro();
		controlador.add(this);
		this.setLayout(null);
		this.setDoubleBuffered(true);
		
		//define coordenadas das linhas
		for (int i = 1 ; i <= tabuleiro.getNumLinhas(); i++){
			JPanel coordLinha = new JPanel();
			Label labelLinha = new Label("" + (char)(i+'A'-1));
			coordLinha.setSize(tamanhoQuadrado, tamanhoQuadrado);
			coordLinha.setLocation(0, i * tamanhoQuadrado);
			coordLinha.add(labelLinha);
			this.add(coordLinha);
		}
		
		//define coordenadas das colunas
		for (int i = 1; i <= tabuleiro.getNumColunas(); i++) {
			JPanel coordColuna = new JPanel();
			Label labelColuna = new Label(String.valueOf(i));
			coordColuna.setSize(tamanhoQuadrado, tamanhoQuadrado);
			coordColuna.setLocation(i * tamanhoQuadrado, 0);
			coordColuna.add(labelColuna);
			this.add(coordColuna);
		}
		
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		int linha, coluna;
		
		//desenhando o tabuleiro
		for (linha=1; linha <= tabuleiro.getNumLinhas(); linha++){
			for (coluna=1; coluna <= tabuleiro.getNumColunas(); coluna++){
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

	//verificar se essa funcao esta seguindo boas praticas de design pattern
	public void mouseClicked(MouseEvent e) {
		int linha = e.getX()/tamanhoQuadrado;
		int coluna = e.getY()/tamanhoQuadrado;
		
		//correção por conta das coordenadas do tabuleiro
		if(linha > 0 && coluna > 0) {
			linha -= 1;
			coluna -= 1;
		} else {
			return;
		}
		
		controlador.TabuleiroClicado(linha, coluna);
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void notify(IObservado observado) {
		repaint();
	}
}