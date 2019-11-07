package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FrameAtaque extends JFrame {
	
	PainelTabuleiro tabuleiroJogador1 = new PainelTabuleiro();
	PainelTabuleiro tabuleiroJogador2 = new PainelTabuleiro();

	public FrameAtaque() {
		final DimensaoTela tela = DimensaoTela.getScreenDimensions();
		setSize(tela.screenIntWidth, tela.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		int x;
		int y;
		
		tabuleiroJogador1.setSize((tabuleiroJogador1.getTabuleiro().getNumLinhas() + 1) * tabuleiroJogador1.getTamanhoQuadrado() + 1, 
						(tabuleiroJogador1.getTabuleiro().getNumColunas() + 1) * tabuleiroJogador1.getTamanhoQuadrado() + 1);
		x = (int)(tela.screenIntWidth * 1/4 - tabuleiroJogador1.getSize().getWidth()/2);
		y = (int)(tela.screenIntHeight * 1/2 - tabuleiroJogador1.getSize().getHeight()/2);
		tabuleiroJogador1.setLocation(x, y);	
		getContentPane().add(tabuleiroJogador1);
		
		JLabel labelTabuleiro1 = new JLabel("Tabuleiro de j1");
		labelTabuleiro1.setSize(500, 25);
		labelTabuleiro1.setLocation(x, y - 25);
		this.add(labelTabuleiro1);
		
		tabuleiroJogador2.setSize((tabuleiroJogador2.getTabuleiro().getNumLinhas() + 1) * tabuleiroJogador2.getTamanhoQuadrado() + 1, 
				(tabuleiroJogador2.getTabuleiro().getNumColunas() + 1) * tabuleiroJogador2.getTamanhoQuadrado() + 1);
		x = (int)(tela.screenIntWidth * 3/4 - tabuleiroJogador2.getSize().getWidth()/2);
		tabuleiroJogador2.setLocation(x, y);
		getContentPane().add(tabuleiroJogador2);
		
		JLabel labelTabuleiro2 = new JLabel("Tabuleiro de j2");
		labelTabuleiro2.setSize(500, 25);
		labelTabuleiro2.setLocation(x, y - 25);
		this.add(labelTabuleiro2);
		
		JLabel instrucao = new JLabel("Visao bloqueada, j1 deve clicar no botao para desbloquear sua visao");
		instrucao.setSize(500, 25);
		x = (int)(tela.screenIntWidth * 1/2 - instrucao.getSize().getWidth()/2);
		y = y + tabuleiroJogador2.getHeight() + 50;
		instrucao.setLocation(x, y);
		this.add(instrucao);
		
		JButton botao = new JButton("Botao");
		int larguraBotao = 200;
		int alturaBotao = 50;
		botao.setSize(larguraBotao, alturaBotao);
		botao.setLocation((int) (tela.screenIntWidth/2 - botao.getSize().getWidth()/2), y + 50);
		botao.setEnabled(false);
		this.add(botao);
	}
}
