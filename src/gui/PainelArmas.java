package gui;

import java.awt.Color;

import javax.swing.*;

import armas.*;

/**
 * Classe respons�vel pela apresenta��o das armas
 * @author Gabriel Boscoli
 *
 */
@SuppressWarnings("serial")
public class PainelArmas extends JPanel {
	
	//defini��o das cores das armas
	private Color COURACADO_COR = Color.green;
	private Color CRUZADOR_COR = Color.pink;
	private Color DESTROYER_COR = Color.blue;
	private Color HIDROAVIAO_COR = Color.gray;
	private Color SUBMARINO_COR = Color.magenta;
	
	//definicao da quantidade de cada tipo de arma
	private int COURACADO_QNTD = 1;
	private int CRUZADOR_QNTD = 2;
	private int DESTROYER_QNTD = 3;
	private int HIDROAVIAO_QNTD = 5;
	private int SUBMARINO_QNTD = 4;
	
	//vetores com as armas
	private Arma[] couracados = new Arma[COURACADO_QNTD];
	private Arma[] cruzadores = new Arma[CRUZADOR_QNTD];
	private Arma[] destroyers = new Arma[DESTROYER_QNTD];
	private Arma[] hidroavioes = new Arma[HIDROAVIAO_QNTD];
	private Arma[] submarinos = new Arma[SUBMARINO_QNTD];
	
	//espaco entre os desenhos das armas
	int espacoEntreArmas = 25;

	public PainelArmas() {
		
		final DimensaoTela tela = DimensaoTela.getScreenDimensions();
		int larguraTela = tela.screenIntWidth;
		int alturaTela = tela.screenIntHeight;
		setSize(larguraTela, alturaTela);
		setLayout(null);
		setFocusable(true);
		
		//auxiliares no posicionamento
		int xPosicionamento = 0;
		int yPosicionamento = 0;
		int tamanhoQuadrado = 0;
		
		//Adicionando hidroavioes no painel
		for(int i = 0; i < HIDROAVIAO_QNTD; i++) {
			hidroavioes[i] = new Hidroaviao();
		}
		tamanhoQuadrado = desenhaArmas(hidroavioes, HIDROAVIAO_COR, xPosicionamento, yPosicionamento);
		
		xPosicionamento = 0;
		yPosicionamento += hidroavioes[0].getAltura() * tamanhoQuadrado + espacoEntreArmas;
		
		//Adicionando submarinos no painel
		for(int i = 0; i < SUBMARINO_QNTD; i++) {
			submarinos[i] = new Submarino();
		}
		tamanhoQuadrado = desenhaArmas(submarinos, SUBMARINO_COR, xPosicionamento, yPosicionamento);
		
		xPosicionamento = 0;
		yPosicionamento += submarinos[0].getAltura() * tamanhoQuadrado + espacoEntreArmas;

		//Adicionando destroyers no painel
		for(int i = 0; i < DESTROYER_QNTD; i++) {
			destroyers[i] = new Destroyer();
		}
		tamanhoQuadrado = desenhaArmas(destroyers, DESTROYER_COR, xPosicionamento, yPosicionamento);
		
		xPosicionamento = 0;
		yPosicionamento += destroyers[0].getAltura() * tamanhoQuadrado + espacoEntreArmas;

		//Adicionando cruzadores no painel
		for(int i = 0; i < CRUZADOR_QNTD; i++) {
			cruzadores[i] = new Cruzador();
		}
		tamanhoQuadrado = desenhaArmas(cruzadores, CRUZADOR_COR, xPosicionamento, yPosicionamento);
		
		xPosicionamento = 0;
		yPosicionamento += cruzadores[0].getAltura() * tamanhoQuadrado + espacoEntreArmas;

		//Adicionando couracados no painel
		for(int i = 0; i < COURACADO_QNTD; i++) {
			couracados[i] = new Couracado();
		}
		tamanhoQuadrado = desenhaArmas(couracados, COURACADO_COR, xPosicionamento, yPosicionamento);

		xPosicionamento = 0;
		yPosicionamento += couracados[0].getAltura() * tamanhoQuadrado + espacoEntreArmas; 
	}
	
	/**
	 * Desenha as armas, todas na mesma linha.
	 * Adiciona as armas no painel.
	 * @param armas vetor com as armas
	 * @param cor cor das armas
	 * @param qntdArmas quantidade de armas a serem desenhadas
	 * @param xInicial coordenada x de onde o desenho deve come�ar
	 * @param yInicial coordenada y de onde o desenho deve come�ar
	 * @reurn o tamanho dos quadrados desenhados para formar a arma
	 */
	private int desenhaArmas(Arma[] armas, Color cor, int xInicial, int yInicial) {
		int tamanhoQuadrado = 0;
		int xPosicionamento = xInicial;
		int yPosicionamento = yInicial;
		
		for(int i = 0; i < armas.length; i++) {
			PainelArma painelArma = new PainelArma(armas[i], cor);
			tamanhoQuadrado = painelArma.getTamanhoQuadrado();
			painelArma.setSize(armas[i].getLargura() * tamanhoQuadrado + 1, armas[i].getAltura() * tamanhoQuadrado + 1);
			painelArma.setLocation(xPosicionamento, yPosicionamento);//rever localiza��o
			
			this.add(painelArma);
			//PainelCouracado.repaint();
			
			xPosicionamento += armas[i].getLargura() * tamanhoQuadrado + espacoEntreArmas;
		}
		
		return tamanhoQuadrado;
	}
	
//	@Override
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		
//		Graphics2D g2d = (Graphics2D) g;
//		
//		for (int i = 0; i < 4; i++){
//			Rectangle2D retangulo = new Rectangle2D.Double(i * 25, 25, 25, 25);
//			g2d.setPaint(Color.green);
//			g2d.fill(retangulo);
//			g2d.setPaint(Color.black);
//			g2d.draw(retangulo);
//		}
//		
//	}
	
}
