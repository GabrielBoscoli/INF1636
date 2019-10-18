package gui;

import java.awt.*;
import javax.swing.*;

/**
 * Classe responsável pela tela de posicao
 * @author Bruno Franco
 *
 */

@SuppressWarnings("serial")
public class FramePosicionamento extends JFrame  {
	
	private PanelTabuleiro boardPanel;
	private JPanel panelInstrucao = new JPanel();
	private FrameJogadores jogadores;
	
	private Point currentMousePosition = new Point(0,0);
	
	public int basePointX;
	public int basePointY;
	
	public FramePosicionamento() {
		//configuracao da janela
		final DimensaoTela tela = DimensaoTela.getScreenDimensions();
		setSize(tela.screenIntWidth, tela.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setLayout(null);
		setFocusable(true);
		requestFocusInWindow();
		
		//Panel de Intruções ao jogador
		panelInstrucao.setSize(500, 100);
		//instructionPanel.lines
		panelInstrucao.setLocation((int)(tela.screenIntWidth*1/2 - panelInstrucao.getSize().getWidth()/2), (int) 50);
		//this.setInstruction("<html>Vez de " + jogadores.getNomeJogadorUm() + ": <br> Botão direito do mouse gira a peça, esquerdo seleciona. <br> Clique no tabuleiro para colocar no local desejado. </html>");
		
		//tabuleiro
		boardPanel = new PanelTabuleiro();
		boardPanel.setSize((boardPanel.getNumLinhas()+1)*boardPanel.getTamanhoQuadrado(), 
						(boardPanel.getNumColunas()+1)*boardPanel.getTamanhoQuadrado());
		basePointX = (int)(tela.screenIntWidth*3/4 - boardPanel.getSize().getWidth()/2);
		basePointY = (int)(tela.screenIntHeight*1/2 - boardPanel.getSize().getHeight()/2);
		
		boardPanel.setLocation(basePointX, basePointY);	
		
		getContentPane().add(boardPanel);
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
	
	public void setInstruction (String text){
		panelInstrucao.removeAll();
		panelInstrucao.revalidate();
		Label instructionLabel = new Label(text);
		panelInstrucao.add(instructionLabel);
	}
	
	public Point getPanelPoint(){
		return boardPanel.getLocation();
	}
}