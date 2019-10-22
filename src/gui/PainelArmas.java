package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import armas.*;

/**
 * Classe responsável pela apresentação das armas
 * @author Gabriel Boscoli
 *
 */
@SuppressWarnings("serial")
public class PainelArmas extends JPanel {
	
	Arma arma = new Cruzador();
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 1; i <= arma.getQntdQuadrados(); i++){
			Rectangle2D retangulo = new Rectangle2D.Double(i * 25, 25, 25, 25);
			g2d.setPaint(Color.green);
			g2d.fill(retangulo);
			g2d.setPaint(Color.black);
			g2d.draw(retangulo);
		}
		
	}
	
}
