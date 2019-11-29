package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controladores.Fachada;
import dominio.Jogador;
import dominio.Tabuleiro;
import observer.IObservado;
import observer.IObservador;

@SuppressWarnings("serial")
public class FrameAtaque extends JFrame implements MouseListener, ActionListener, IObservador {
	Jogador jogador1;
	Jogador jogador2;
	
	PainelTabuleiro tabuleiroJogador1 = new PainelTabuleiro();
	JLabel labelTabuleiro1 = new JLabel();
	
	PainelTabuleiro tabuleiroJogador2 = new PainelTabuleiro();
	JLabel labelTabuleiro2 = new JLabel();
	
	JLabel instrucao = new JLabel();
	JLabel resultado = new JLabel();
	
	JButton botao = new JButton("Desbloquear Visão");
	
	Menu menu;
	
	boolean visaoBloqueada;
	boolean rodadaEncerrada;
	
	String vencedor = null;
	String nomeJogadorDaVez;

	public FrameAtaque() {
		final Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(tela.width, tela.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		visaoBloqueada = Fachada.getFachada().getVisaoBloqueada();
		rodadaEncerrada = Fachada.getFachada().getRodadaEncerrada();
		jogador1 = Fachada.getFachada().getJogador(1);
		jogador2 = Fachada.getFachada().getJogador(2);
		
		labelTabuleiro1.setText("Tabuleiro de " + jogador1.getNome());
		labelTabuleiro2.setText("Tabuleiro de " + jogador2.getNome());
		
		int x;
		int y;
		
		JPanel painelResultadoAtaque = new JPanel();
		resultado.setText(Fachada.getFachada().getResultado());
		painelResultadoAtaque.add(resultado);
		painelResultadoAtaque.setSize(500, 25);
		x = (int)(tela.width * 1/2 - painelResultadoAtaque.getSize().getWidth()/2);
		y = (int)(tela.height * 1/4);
		painelResultadoAtaque.setLocation(x, y);
		this.add(painelResultadoAtaque);
		
		tabuleiroJogador1 = new PainelTabuleiro();
		tabuleiroJogador1.setTabuleiro(Fachada.getFachada().getTabuleiroFaseAtaque(1));
		tabuleiroJogador1.setSize((tabuleiroJogador1.getTabuleiro().getNumLinhas() + 1) * tabuleiroJogador1.getTamanhoQuadrado() + 1, 
						(tabuleiroJogador1.getTabuleiro().getNumColunas() + 1) * tabuleiroJogador1.getTamanhoQuadrado() + 1);
		x = (int)(tela.width * 1/4 - tabuleiroJogador1.getSize().getWidth()/2);
		y = (int)(tela.height * 1/2 - tabuleiroJogador1.getSize().getHeight()/2);
		tabuleiroJogador1.setLocation(x, y);	
		getContentPane().add(tabuleiroJogador1);
		tabuleiroJogador1.addMouseListener(this);
		
		labelTabuleiro1.setSize(500, 25);
		labelTabuleiro1.setLocation(x, y - 25);
		this.add(labelTabuleiro1);
		
		tabuleiroJogador2 = new PainelTabuleiro();
		tabuleiroJogador2.setTabuleiro(Fachada.getFachada().getTabuleiroFaseAtaque(2));
		tabuleiroJogador2.setSize((tabuleiroJogador2.getTabuleiro().getNumLinhas() + 1) * tabuleiroJogador2.getTamanhoQuadrado() + 1, 
				(tabuleiroJogador2.getTabuleiro().getNumColunas() + 1) * tabuleiroJogador2.getTamanhoQuadrado() + 1);
		x = (int)(tela.width * 3/4 - tabuleiroJogador2.getSize().getWidth()/2);
		tabuleiroJogador2.setLocation(x, y);
		getContentPane().add(tabuleiroJogador2);
		tabuleiroJogador2.addMouseListener(this);
		
		labelTabuleiro2.setSize(500, 25);
		labelTabuleiro2.setLocation(x, y - 25);
		this.add(labelTabuleiro2);
		
		JPanel painelInstrucao = new JPanel();
		instrucao.setText("Visao bloqueada. " + Fachada.getFachada().getNomeJogadorDaVezFaseDeAtaque() + ", clique no botao para desbloquear sua visao.");
		painelInstrucao.add(instrucao);
		painelInstrucao.setSize(500, 25);
		x = (int)(tela.width * 1/2 - painelInstrucao.getSize().getWidth()/2);
		y = y + tabuleiroJogador2.getHeight() + 50;
		painelInstrucao.setLocation(x, y);
		this.add(painelInstrucao);
		
		botao.setSize(200, 50);
		botao.setLocation((int) (tela.width/2 - botao.getSize().getWidth()/2), y + 50);
		botao.setEnabled(true);
		this.add(botao);
		botao.addActionListener(this);
		
		menu = Fachada.getFachada().getMenu();
		menu.setLocation(0, 0);
		menu.setSize(tela.width, 20);
		getContentPane().add(menu);
		
		Fachada.getFachada().register(this, "ataque");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Fachada.getFachada().botaoFaseAtaqueClicado();
	}

	@Override
	public void notify(IObservado observado) {
		visaoBloqueada = Fachada.getFachada().getVisaoBloqueada();
		rodadaEncerrada = Fachada.getFachada().getRodadaEncerrada();
		vencedor = (String) Fachada.getFachada().getNomeVencedor();
		resultado.setText(Fachada.getFachada().getResultado());
		
		tabuleiroJogador1.repaint();
		tabuleiroJogador2.repaint();
		
		if(visaoBloqueada) {
			botao.setText("Desbloquear Visão");
			instrucao.setText("Visao bloqueada. " + Fachada.getFachada().getNomeJogadorDaVezFaseDeAtaque() + ", clique no botao para desbloquear sua visao.");
		} else if(!rodadaEncerrada) {
			botao.setEnabled(false);
			instrucao.setText(Fachada.getFachada().getNomeJogadorDaVezFaseDeAtaque() + ", quando acabar seus tiros, clique no botao para encerrar sua rodada.");
			botao.setText("Encerrar Rodada");
		} else if(rodadaEncerrada ) {
			botao.setEnabled(true);
		} 

		if(vencedor != null) {
			JOptionPane.showMessageDialog(this,vencedor + " é o vencedor");
			Fachada.getFachada().novoJogo();
			Fachada.getFachada().iniciarJogo();
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		PainelTabuleiro painelTabuleiro = (PainelTabuleiro)arg0.getSource();
		Tabuleiro tabuleiro = painelTabuleiro.getTabuleiro();
		
		int coluna = arg0.getX()/painelTabuleiro.getTamanhoQuadrado();
		int linha = arg0.getY()/painelTabuleiro.getTamanhoQuadrado();
		
		//correção por conta das coordenadas do tabuleiro
		if(coluna > 0 && linha > 0) {
			coluna -= 1;
			linha -= 1;
		} else {
			return;
		}
		
		Fachada.getFachada().tabuleiroFaseAtaqueClicado(tabuleiro, coluna, linha);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
