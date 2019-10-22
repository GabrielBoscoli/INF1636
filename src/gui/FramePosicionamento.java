package gui;

import java.awt.*;
import javax.swing.*;

/**
 * Classe responsável pela tela de posicionamento
 * @author Bruno Franco
 *
 */

@SuppressWarnings("serial")
public class FramePosicionamento extends JFrame  {
	
	//Painel do tabuleiro matricial
	private PanelTabuleiro boardPanel;

	private Point currentMousePosition = new Point(0,0);
	
	//coordenadas do ponto de origem do tabuleiro
	public int basePointX;
	public int basePointY;
	
	public FramePosicionamento() {
		
		//propriedades do frame
		final DimensaoTela tela = DimensaoTela.getScreenDimensions();
		setSize(tela.screenIntWidth, tela.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setFocusable(true);
		requestFocusInWindow();
		
		//configurando e adicionando tabuleiro ao frame
		boardPanel = new PanelTabuleiro();
		boardPanel.setSize((boardPanel.getNumLinhas()+1)*boardPanel.getTamanhoQuadrado(), 
						(boardPanel.getNumColunas()+1)*boardPanel.getTamanhoQuadrado());
		basePointX = (int)(tela.screenIntWidth*3/4 - boardPanel.getSize().getWidth()/2);
		basePointY = (int)(tela.screenIntHeight*1/2 - boardPanel.getSize().getHeight()/2);
		boardPanel.setLocation(basePointX, basePointY);	
		getContentPane().add(boardPanel);
		
		//
		
	}

	public PanelTabuleiro getPanel() {
		return boardPanel;
	}

	public Point getCurrentMousePosition() {
		return currentMousePosition;
	}
	
	public void setCurrentMousePosition(Point p) {
		this.currentMousePosition = p;
	}
	
	public Point getPanelPoint(){
		return boardPanel.getLocation();
	}
	
	
}