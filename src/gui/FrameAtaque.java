package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controladores.ControladorAtaque;
import controladores.ControladorJogo;
import dominio.Tabuleiro;
import observer.IObservado;
import observer.IObservador;

@SuppressWarnings("serial")
public class FrameAtaque extends JFrame implements MouseListener, ActionListener, IObservador {
	
	PainelTabuleiro tabuleiroJogador1 = new PainelTabuleiro();
	JLabel labelTabuleiro1 = new JLabel("Tabuleiro de j1");
	
	PainelTabuleiro tabuleiroJogador2 = new PainelTabuleiro();
	JLabel labelTabuleiro2 = new JLabel("Tabuleiro de j2");
	
	JLabel instrucao = new JLabel();
	
	JButton botao = new JButton("Desbloquear Visão");
	
	boolean visaoBloqueada;
	boolean rodadaEncerrada;
	
	String vencedor = null;

	public FrameAtaque() {
		final DimensaoTela tela = DimensaoTela.getScreenDimensions();
		setSize(tela.screenIntWidth, tela.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		visaoBloqueada = (boolean) ControladorAtaque.getControladorAtaque().get(3);
		rodadaEncerrada = (boolean) ControladorAtaque.getControladorAtaque().get(4);
		
		int x;
		int y;
		
		tabuleiroJogador1 = new PainelTabuleiro();
		tabuleiroJogador1.setTabuleiro((Tabuleiro) ControladorAtaque.getControladorAtaque().get(1));
		tabuleiroJogador1.setSize((tabuleiroJogador1.getTabuleiro().getNumLinhas() + 1) * tabuleiroJogador1.getTamanhoQuadrado() + 1, 
						(tabuleiroJogador1.getTabuleiro().getNumColunas() + 1) * tabuleiroJogador1.getTamanhoQuadrado() + 1);
		x = (int)(tela.screenIntWidth * 1/4 - tabuleiroJogador1.getSize().getWidth()/2);
		y = (int)(tela.screenIntHeight * 1/2 - tabuleiroJogador1.getSize().getHeight()/2);
		tabuleiroJogador1.setLocation(x, y);	
		getContentPane().add(tabuleiroJogador1);
		tabuleiroJogador1.addMouseListener(this);
		
		labelTabuleiro1.setSize(500, 25);
		labelTabuleiro1.setLocation(x, y - 25);
		this.add(labelTabuleiro1);
		
		tabuleiroJogador2 = new PainelTabuleiro();
		tabuleiroJogador2.setTabuleiro((Tabuleiro) ControladorAtaque.getControladorAtaque().get(2));
		tabuleiroJogador2.setSize((tabuleiroJogador2.getTabuleiro().getNumLinhas() + 1) * tabuleiroJogador2.getTamanhoQuadrado() + 1, 
				(tabuleiroJogador2.getTabuleiro().getNumColunas() + 1) * tabuleiroJogador2.getTamanhoQuadrado() + 1);
		x = (int)(tela.screenIntWidth * 3/4 - tabuleiroJogador2.getSize().getWidth()/2);
		tabuleiroJogador2.setLocation(x, y);
		getContentPane().add(tabuleiroJogador2);
		tabuleiroJogador2.addMouseListener(this);
		
		labelTabuleiro2.setSize(500, 25);
		labelTabuleiro2.setLocation(x, y - 25);
		this.add(labelTabuleiro2);
		
		JPanel painelInstrucao = new JPanel();
		instrucao.setText("Visao bloqueada, j1 deve clicar no botao para desbloquear sua visao");
		painelInstrucao.add(instrucao);
		painelInstrucao.setSize(500, 25);
		x = (int)(tela.screenIntWidth * 1/2 - painelInstrucao.getSize().getWidth()/2);
		y = y + tabuleiroJogador2.getHeight() + 50;
		painelInstrucao.setLocation(x, y);
		this.add(painelInstrucao);
		
		botao.setSize(200, 50);
		botao.setLocation((int) (tela.screenIntWidth/2 - botao.getSize().getWidth()/2), y + 50);
		botao.setEnabled(true);
		this.add(botao);
		botao.addActionListener(this);
		
		ControladorAtaque.getControladorAtaque().add(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ControladorAtaque.getControladorAtaque().botaoClicado();
	}

	@Override
	public void notify(IObservado observado) {
		visaoBloqueada = (boolean) ControladorAtaque.getControladorAtaque().get(3);
		rodadaEncerrada = (boolean) ControladorAtaque.getControladorAtaque().get(4);
		vencedor = (String) ControladorAtaque.getControladorAtaque().get(5);
		
		tabuleiroJogador1.repaint();
		tabuleiroJogador2.repaint();
		
		if(visaoBloqueada) {
			botao.setText("Desbloquear Visão");
		} else if(!rodadaEncerrada) {
			//instrucao.setText("Encerrar Rodada");
			botao.setEnabled(false);
			botao.setText("Encerrar Rodada");
		} else if(rodadaEncerrada ) {
			botao.setEnabled(true);
		} 

		if(vencedor != null) {
			JOptionPane.showMessageDialog(this,vencedor + " é o vencedor");
			ControladorJogo.getMainGamePresenter().fecharFrameAtaque();
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
		
		ControladorAtaque.getControladorAtaque().tabuleiroClicado(tabuleiro, coluna, linha);
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
