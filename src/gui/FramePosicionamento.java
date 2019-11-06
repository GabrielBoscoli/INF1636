package gui;

import java.awt.event.*;

import javax.swing.*;

import controladores.ControladorPosicionamento;
import observer.IObservado;
import observer.IObservador;


/**
 * Classe responsável pela tela de posicionamento
 * @author Bruno Franco
 *
 */

@SuppressWarnings("serial")

public class FramePosicionamento extends JFrame implements KeyListener, IObservador {

	
	//Painel do tabuleiro matricial
	private PainelTabuleiro boardPanel;
	
	//Painel das armas a serem posicionadas
	private PainelArmas painelArmas;
	
	//Painel das instrucoes de jogo
	private JPanel painelInstrucoes = new JPanel();
	
	//Botao de confirmacao do posicionamento
	private static JButton botaoConfirmacao = new JButton("Tabuleiro Pronto!");
	
	boolean tabuleiroPronto = false;
	
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
		boardPanel.setSize((boardPanel.getTabuleiro().getNumLinhas()+1)*boardPanel.getTamanhoQuadrado() + 1, 
						(boardPanel.getTabuleiro().getNumColunas()+1)*boardPanel.getTamanhoQuadrado() + 1);
		int x = (int)(tela.screenIntWidth*3/4 - boardPanel.getSize().getWidth()/2);
		int y = (int)(tela.screenIntHeight*1/2 - boardPanel.getSize().getHeight()/2);
		boardPanel.setLocation(x, y);	
		getContentPane().add(boardPanel);
		
		//configurando e adicionando armas ao frame
		painelArmas = new PainelArmas();
		painelArmas.setLocation((int) (tela.screenIntWidth*1/4 - painelArmas.getSize().getWidth()/2), y);
		getContentPane().add(painelArmas);
		
		//adicionando instruções de jogo
		JLabel instrucoes = new JLabel("Jogador1, posicione suas peças no tabuleiro");
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
		ControladorPosicionamento.getControladorPosicionamento().add(this);
		
		addKeyListener(this);
		
	}
	
	static public JButton getBotaoConfirmacao() {
		return botaoConfirmacao;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			ControladorPosicionamento.getControladorPosicionamento().TeclaEscapePressionada();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void notify(IObservado observado) {
		tabuleiroPronto = (boolean) ControladorPosicionamento.getControladorPosicionamento().get(3);
		if(tabuleiroPronto == true) {
			botaoConfirmacao.setEnabled(true);
		} else {
			botaoConfirmacao.setEnabled(false);
		}
		
	}
	
}