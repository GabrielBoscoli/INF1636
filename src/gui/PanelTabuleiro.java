package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;

/**
 * Classe responsável pelo desenho do tabuleiro
 * @author Gabriel Boscoli
 *
 */
@SuppressWarnings("serial")
public class PanelTabuleiro extends JPanel {

	private int tamanhoQuadrado = 25;
	private int numColunas = 15;
	private int numLinhas = 15;
	private Color[][] MatrizCor = new Color[numLinhas][numColunas];
	
	public PanelTabuleiro() {
		this.setLayout(null);
		
		//define a cor dos quadrados da matriz
		for (int i = 0; i < numLinhas; i++){
			for (int j = 0 ; j < numColunas; j++ ){
				MatrizCor[i][j] = Color.cyan;
			}
		}
		
		this.setDoubleBuffered(true);
		
		//define coordenadas das linhas
		for (int i = 1 ; i <= numLinhas; i++){
			JPanel coordLinha = new JPanel();
			Label labelLinha = new Label("" + (char)(i+'A'-1));
			coordLinha.setSize(tamanhoQuadrado, tamanhoQuadrado);
			coordLinha.setLocation(0, i * tamanhoQuadrado);
			coordLinha.add(labelLinha);
			this.add(coordLinha);
		}
		
		//define coordenadas das colunas
		for (int i = 1; i <= numColunas; i++) {
			JPanel coordColuna = new JPanel();
			Label labelColuna = new Label(String.valueOf(i));
			coordColuna.setSize(tamanhoQuadrado, tamanhoQuadrado);
			coordColuna.setLocation(i * tamanhoQuadrado, 0);
			coordColuna.add(labelColuna);
			this.add(coordColuna);
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		int linha, coluna;
		
		//desenhando o tabuleiro
		for (linha=1; linha <= numLinhas; linha++){
			for (coluna=1; coluna <= numColunas; coluna++){
				Rectangle2D retangulo = new Rectangle2D.Double(tamanhoQuadrado * coluna, tamanhoQuadrado * linha, tamanhoQuadrado, tamanhoQuadrado);
				g2d.setPaint(MatrizCor[coluna-1][linha-1]);
				g2d.fill(retangulo);
				g2d.setPaint(Color.black);
				g2d.draw(retangulo);
			}
		}
	}
	
	public int getTamanhoQuadrado(){
		return tamanhoQuadrado;
	}
	
	public int getNumLinhas(){
		return numLinhas;
	}
	
	public int getNumColunas(){
		return numColunas;
	}
	
	public Color[][] getMatrizCor() {
		return MatrizCor;
	}
	
}