package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controladores.ControladorJogo;

/**
 * Classe responsável pela tela de definição de jogadores
 * @author Gabriel Boscoli
 *
 */
@SuppressWarnings("serial")
public class FrameJogadores extends JFrame implements ActionListener {
	
	//dimensão do frame
	private final int FRAME_LARGURA = 300; 
	private final int FRAME_ALTURA = 200;
	
	//nome dos jogadores
	private JTextField nomeJogadorUm = new JTextField("Jogador 1", 10);
	private JTextField nomeJogadorDois = new JTextField("Jogador 2", 10);
	
	private Menu menu;
	
	public FrameJogadores() {
		setLayout(null);

		menu = ControladorJogo.getMainGamePresenter().getMenu();
		menu.setLocation(0, 0);
		menu.setSize(FRAME_LARGURA, 20);

		JPanel painelJogadorUm = new JPanel();
		JPanel painelJogadorDois = new JPanel();
		Label labelJogadorUm = new Label("Jogador 1:");
		Label labelJogadorDois = new Label("Jogador 2:");
		JButton botaoInicio = new JButton("Iniciar");
		
		nomeJogadorUm.setPreferredSize(new Dimension(20,30));
		nomeJogadorDois.setPreferredSize(new Dimension(20,30));

		painelJogadorUm.add(labelJogadorUm);
		painelJogadorUm.add(nomeJogadorUm);
		painelJogadorUm.setSize(FRAME_LARGURA, 40);
		painelJogadorUm.setLocation(0, 35);
		painelJogadorDois.add(labelJogadorDois);
		painelJogadorDois.add(nomeJogadorDois);
		painelJogadorDois.setSize(FRAME_LARGURA, 40);
		painelJogadorDois.setLocation(0, 75);
		
		botaoInicio.setSize(100, 30);
		botaoInicio.setLocation(FRAME_LARGURA/2 - botaoInicio.getSize().width/2, 125);

		getContentPane().add(menu);
		getContentPane().add(painelJogadorUm);
		getContentPane().add(painelJogadorDois);
		getContentPane().add(botaoInicio);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - FRAME_LARGURA) / 2);
	    int y = (int) ((dimension.getHeight() - FRAME_ALTURA) / 2);
	    setLocation(x, y);
		
		setSize(FRAME_LARGURA, FRAME_ALTURA);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Seleção de Jogadores");
		
		botaoInicio.addActionListener(this);
	}
	
	public String getNomeJogadorUm() {
		return nomeJogadorUm.getText();
	}
	
	public String getNomeJogadorDois() {
		return nomeJogadorDois.getText();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ControladorJogo.getMainGamePresenter().fecharFrameJogadores(nomeJogadorUm.getText(), nomeJogadorDois.getText());
	}
	
}
