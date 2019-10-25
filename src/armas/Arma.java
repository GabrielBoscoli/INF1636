package armas;

import outros.Coordenada;

/**
 * Classe abstrata que define propriedades comuns a todas as armas do jogo
 * @author Gabriel Boscoli
 *
 */
public abstract class Arma {

	//quantidade de quadrados que a arma ocupa na matriz de posicionamento
	private int qntdQuadrados;
	
	//quantidade de rotações possiveis para a arma
	private int qntdRotacoes;
	
	//formato da arma
	private Coordenada[] formato;
	
	/**
	 * Construtor da classe arma
	 * @param qntdQuadrados quantidade de quadrados que a arma ocupa na matriz de posicionamento
	 * @param qntdRotacoes quantidade de rotações possiveis para a arma
	 */
	Arma() {
		
	}

	public int getQntdQuadrados() {
		return qntdQuadrados;
	}

	public void setQntdQuadrados(int qntdQuadrados) {
		this.qntdQuadrados = qntdQuadrados;
	}

	public int getQntdRotacoes() {
		return qntdRotacoes;
	}

	public void setQntdRotacoes(int qntdRotacoes) {
		this.qntdRotacoes = qntdRotacoes;
	}

	public Coordenada[] getFormato() {
		return formato;
	}

	public void setFormato(Coordenada[] formato) {
		this.formato = formato;
	}
	
	/**
	 * Retorna a altura da arma em quadrados
	 * @return altura da arma em quadrados
	 */
	public int getAltura() {
		if(formato == null) {
			return 0;
		}
		
		int maior = 0;
		
		for(int i=0; i<formato.length; i++) {
			if(formato[i].getY() > maior) {
				maior = formato[i].getY();
			}
		}
		
		return maior + 1;
	}
	
	/**
	 * Retorna a largura da arma em quadrados
	 * @return largura da arma em quadrados
	 */
	public int getLargura() {
		if(formato == null) {
			return 0;
		}
		
		int maior = 0;
		
		for(int i=0; i<formato.length; i++) {
			if(formato[i].getX() > maior) {
				maior = formato[i].getX();
			}
		}
		
		return maior + 1;
	}
	
}
