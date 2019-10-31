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
	
	/**
	 * Verifica se a casa da linha e coluna especificada está vazia
	 * @param linha - linha da casa
	 * @param coluna - coluna da casa
	 * @return true, se a casa estiver vazia. false, se estiver ocupada
	 * ou se o tabuleiro nao possui casa na coordenada recebida.
	 */
	public boolean CasaEstaVazia(int linha, int coluna) {
		if(linha >= numLinhas || linha < 0 || coluna >= numColunas || coluna < 0) {
			return false;
		}
		
		if(MatrizCor[linha][coluna] == Color.cyan) { //essa linha deve mudar depois
			return true;
		} else {
			return false;
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
