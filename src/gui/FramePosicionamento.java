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
	private PainelTabuleiro boardPanel;
	
	//Painel das armas a serem posicionadas
	private PainelArmas painelArmas;
	
	//Painel das instrucoes de jogo
	private JPanel painelInstrucoes = new JPanel();
	
	//Botao de confirmacao do posicionamento
	private JButton botaoConfirmacao = new JButton("Tabuleiro Pronto!");

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
		//setFocusable(true);
		//requestFocusInWindow();
		
		//configurando e adicionando tabuleiro ao frame
		boardPanel = new PainelTabuleiro();
		boardPanel.setSize((boardPanel.getNumLinhas()+1)*boardPanel.getTamanhoQuadrado(), 
						(boardPanel.getNumColunas()+1)*boardPanel.getTamanhoQuadrado());
		basePointX = (int)(tela.screenIntWidth*3/4 - boardPanel.getSize().getWidth()/2);
		basePointY = (int)(tela.screenIntHeight*1/2 - boardPanel.getSize().getHeight()/2);
		boardPanel.setLocation(basePointX, basePointY);	
		getContentPane().add(boardPanel);
		
		//configurando e adicionando armas ao frame
		painelArmas = new PainelArmas();
		painelArmas.setLocation(tela.screenIntWidth*1/7, basePointY);
		getContentPane().add(painelArmas);
		
		//adicionando instruções de jogo
		JLabel instrucoes = new JLabel("O magui eh muito liso meu deus nunca vi igual garay goroy quuentinhas");
		int larguraInstrucoes = 500;
		int alturaInstrucoes = 25;
		painelInstrucoes.add(instrucoes);
		painelInstrucoes.setSize(larguraInstrucoes, alturaInstrucoes);
		painelInstrucoes.setLocation((int) (tela.screenIntWidth/2 - painelInstrucoes.getSize().getWidth()/2), tela.screenIntHeight * 4/5);
		getContentPane().add(painelInstrucoes);
		
		//adicionando botao para continuar
		int larguraBotao = 200;
		int alturaBotao = 50;
		botaoConfirmacao.setSize(larguraBotao, alturaBotao);
		botaoConfirmacao.setLocation((int) (tela.screenIntWidth/2 - botaoConfirmacao.getSize().getWidth()/2), tela.screenIntHeight * 5/6);
		botaoConfirmacao.setEnabled(false);
		getContentPane().add(botaoConfirmacao);
		
	}

	public PainelTabuleiro getPanel() {
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