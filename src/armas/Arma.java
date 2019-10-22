package armas;


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
	
}
