package tabuleiro;

import java.awt.Color;

public class Tabuleiro {
	
	private int numColunas = 15;
	private int numLinhas = 15;
	private Color[][] MatrizCor = new Color[numLinhas][numColunas];
	
	public Tabuleiro() {
		for (int i = 0; i < numLinhas; i++){
			for (int j = 0 ; j < numColunas; j++ ){
				MatrizCor[i][j] = Color.cyan;
			}
		}
	}

	public int getNumColunas() {
		return numColunas;
	}

	public void setNumColunas(int numColunas) {
		this.numColunas = numColunas;
	}

	public int getNumLinhas() {
		return numLinhas;
	}

	public void setNumLinhas(int numLinhas) {
		this.numLinhas = numLinhas;
	}

	public Color[][] getMatrizCor() {
		return MatrizCor;
	}

	public void setMatrizCor(Color[][] matrizCor) {
		MatrizCor = matrizCor;
	}

}
