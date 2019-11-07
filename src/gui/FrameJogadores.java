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
	private JTextField nomeJogadorUm = new JTextField("Jogador 1");
	private JTextField nomeJogadorDois = new JTextField("Jogador 2");
	
	public FrameJogadores() {
		
		//criando componentes
		JPanel painelJogadorUm = new JPanel();
		painelJogadorUm.setLayout(new BoxLayout(painelJogadorUm, BoxLayout.X_AXIS));
		JPanel painelJogadorDois = new JPanel();
		painelJogadorDois.setLayout(new BoxLayout(painelJogadorDois, BoxLayout.X_AXIS));
		Label labelJogadorUm = new Label("Jogador 1: ");
		Label labelJogadorDois = new Label("Jogador 2: ");
		JButton botaoInicio = new JButton("Iniciar");
		
		//adicionando componentes em seus paineis
		Dimension dimensaoComponente = new Dimension(200, 50);
		painelJogadorUm.add(labelJogadorUm);
		painelJogadorUm.add(nomeJogadorUm);
		painelJogadorUm.setMaximumSize(dimensaoComponente);
		painelJogadorDois.add(labelJogadorDois);
		painelJogadorDois.add(nomeJogadorDois);
		painelJogadorDois.setMaximumSize(dimensaoComponente);
		
		//arrumando componentes no frame
		Dimension espacoEntreComponentes = new Dimension(0, 20);
		getContentPane().add(Box.createRigidArea(espacoEntreComponentes));
		getContentPane().add(painelJogadorUm);
		getContentPane().add(Box.createRigidArea(espacoEntreComponentes));
		getContentPane().add(painelJogadorDois);
		getContentPane().add(Box.createRigidArea(espacoEntreComponentes));
		getContentPane().add(botaoInicio);
		getContentPane().add(Box.createRigidArea(espacoEntreComponentes));
		
		botaoInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		//centralizando o frame
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - FRAME_LARGURA) / 2);
	    int y = (int) ((dimension.getHeight() - FRAME_ALTURA) / 2);
	    setLocation(x, y);
		
	    //propriedades do frame
		setSize(FRAME_LARGURA, FRAME_ALTURA);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Seleção de Jogadores");
		
		botaoInicio.addActionListener(this);
		//MenuController.getMenuController().createAndShowGUI(this);
	}
	
	public String getNomeJogadorUm() {
		return nomeJogadorUm.getText();
	}
	
	public String getNomeJogadorDois() {
		return nomeJogadorDois.getText();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ControladorJogo.getMainGamePresenter().fecharFrameJogadores();
	}
	
}
