package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
	
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
}
