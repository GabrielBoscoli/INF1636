package armas;

import outros.Coordenada;

/**
 * Classe que define a arma Hidroaviao
 * @author Gabriel Boscoli
 *
 */
public class Hidroaviao extends Arma {

	/**
	 * Construtor da classe Hidroaviao
	 * Cria a arma com as propriedades de um couraçado
	 * @param qntdQuadrados qntdQuadrados quantidade de quadrados que a arma ocupa na matriz de posicionamento
	 * @param qntdRotacoes quantidade de rotações possiveis para a arma
	 */
	public Hidroaviao() {
		
		this.setQntdQuadrados(3);
		this.setQntdRotacoes(4);
		
		Coordenada[] formato = new Coordenada[this.getQntdQuadrados()];
		
		//define o formato inicial do couracado
		formato[0] = new Coordenada(0, 1);
		formato[1] = new Coordenada(1, 0);
		formato[2] = new Coordenada(2, 1);

		this.setFormato(formato);
		
	}
	
}
