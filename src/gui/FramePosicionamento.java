package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

import controladores.ControladorJogo;
import controladores.ControladorPosicionamento;
import dominio.Tabuleiro;
import observer.IObservado;
import observer.IObservador;


/**
 * Classe responsável pela tela de posicionamento
 * @author Bruno Franco
 *
 */

@SuppressWarnings("serial")

public class FramePosicionamento extends JFrame implements ActionListener, KeyListener, IObservador, MouseListener {

	
	//Painel do tabuleiro matricial
	private PainelTabuleiro painelTabuleiro;
	
	//Painel das armas a serem posicionadas
	private PainelArmas painelArmas;
	
	//Painel das instrucoes de jogo
	private JLabel instrucoes;
	
	//Botao de confirmacao do posicionamento
	private static JButton botaoConfirmacao = new JButton("Tabuleiro Pronto!");
	
	boolean tabuleiroPronto = false;
	
	Menu menu;
	
	String nomeJogadorDaVez;
	
	public FramePosicionamento() {
		
		//propriedades do frame
		final Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(tela.width, tela.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		//configurando e adicionando tabuleiro ao frame
		painelTabuleiro = new PainelTabuleiro();
		painelTabuleiro.setTabuleiro((Tabuleiro) ControladorPosicionamento.getControladorPosicionamento().get(2));
		painelTabuleiro.setSize((painelTabuleiro.getTabuleiro().getNumLinhas()+1)*painelTabuleiro.getTamanhoQuadrado() + 1, 
						(painelTabuleiro.getTabuleiro().getNumColunas()+1)*painelTabuleiro.getTamanhoQuadrado() + 1);
		int x = (int)(tela.width*3/4 - painelTabuleiro.getSize().getWidth()/2);
		int y = (int)(tela.height*1/2 - painelTabuleiro.getSize().getHeight()/2);
		painelTabuleiro.setLocation(x, y);	
		getContentPane().add(painelTabuleiro);
		painelTabuleiro.addMouseListener(this);
		
		//configurando e adicionando armas ao frame
		painelArmas = new PainelArmas();
		painelArmas.setLocation((int) (tela.width*1/4 - painelArmas.getSize().getWidth()/2), y);
		getContentPane().add(painelArmas);
		
		//adicionando instruções de jogo
		JPanel painelInstrucoes = new JPanel();
		nomeJogadorDaVez = (String) ControladorPosicionamento.getControladorPosicionamento().get(5);
		instrucoes = new JLabel(nomeJogadorDaVez + ", posicione suas peças no tabuleiro");
		int larguraInstrucoes = 500;
		int alturaInstrucoes = 25;
		painelInstrucoes.add(instrucoes);
		painelInstrucoes.setSize(larguraInstrucoes, alturaInstrucoes);
		painelInstrucoes.setLocation((int) (tela.width/2 - painelInstrucoes.getSize().getWidth()/2), tela.height * 4/5);
		getContentPane().add(painelInstrucoes);
		
		//adicionando botao para continuar
		int larguraBotao = 200;
		int alturaBotao = 50;
		botaoConfirmacao.setSize(larguraBotao, alturaBotao);
		botaoConfirmacao.setLocation((int) (tela.width/2 - botaoConfirmacao.getSize().getWidth()/2), tela.height * 5/6);
		botaoConfirmacao.setEnabled(false);
		getContentPane().add(botaoConfirmacao);
		botaoConfirmacao.addActionListener(this);
		botaoConfirmacao.setFocusable(false);
		
		menu = ControladorJogo.getMainGamePresenter().getMenu();
		menu.setLocation(0, 0);
		menu.setSize(tela.width, 20);
		getContentPane().add(menu);
		
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
		nomeJogadorDaVez = (String) ControladorPosicionamento.getControladorPosicionamento().get(5);
		
		if(tabuleiroPronto == true) {
			botaoConfirmacao.setEnabled(true);
		} else {
			botaoConfirmacao.setEnabled(false);
		}
		
		instrucoes.setText(nomeJogadorDaVez + ", posicione suas peças no tabuleiro");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ControladorPosicionamento.getControladorPosicionamento().BotaoTabuleiroProntoClicado();
	}
	
	//verificar se essa funcao esta seguindo boas praticas de design pattern
	public void mousePressed(MouseEvent e) {
		int coluna = e.getX()/painelTabuleiro.getTamanhoQuadrado();
		int linha = e.getY()/painelTabuleiro.getTamanhoQuadrado();
		
		//correção por conta das coordenadas do tabuleiro
		if(coluna > 0 && linha > 0) {
			coluna -= 1;
			linha -= 1;
		} else {
			return;
		}
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			ControladorPosicionamento.getControladorPosicionamento().TabuleiroClicadoComBotaoEsquerdo(coluna, linha);
		} else if(e.getButton() == MouseEvent.BUTTON3) {
			ControladorPosicionamento.getControladorPosicionamento().TabuleiroClicadoComBotaoDireito();
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}