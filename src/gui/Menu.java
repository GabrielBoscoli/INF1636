package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controladores.ControladorJogo;

@SuppressWarnings("serial")
public class Menu extends JMenuBar implements ActionListener {
	
	JMenu menu;
	JMenuItem salvar;
	JMenuItem recarregar;
	
	public Menu() {
		JMenu menu = new JMenu("Arquivo");
		
		this.add(menu);
		
		salvar = new JMenuItem("Salvar");
        salvar.setEnabled(true);
        menu.add(salvar);
 
        recarregar = new JMenuItem("Abrir");
        recarregar.setEnabled(true);
        menu.add(recarregar);
        
        salvar.addActionListener(this);
        recarregar.addActionListener(this);
	}
	
	public void desativarSalvamento() {
		salvar.setEnabled(false);
	}
	
	public void ativarSalvamento() {
		salvar.setEnabled(true);
	}
	
	public void desativarRecarregamento() {
		recarregar.setEnabled(false);
	}
	
	public void ativarRecarregamento() {
		recarregar.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == salvar) {
			try {
				ControladorJogo.getMainGamePresenter().salvarJogo();				
			} catch (Exception ex) {
	            ex.printStackTrace();
			}
		} else {
			ControladorJogo.getMainGamePresenter().recarregarJogo();
		}
		
	}
}
