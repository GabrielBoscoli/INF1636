package armas;

import outros.Coordenada;

/**
 * Classe que define a arma Submarino
 * @author Gabriel Boscoli
 *
 */
public class Submarino extends Arma {
	
	/**
	 * Construtor da classe Submarino
	 * Cria a arma com as propriedades de um couraçado
	 * @param qntdQuadrados qntdQuadrados quantidade de quadrados que a arma ocupa na matriz de posicionamento
	 * @param qntdRotacoes quantidade de rotações possiveis para a arma
	 */
	public Submarino() {
		
		this.setQntdQuadrados(1);
		this.setQntdRotacoes(1);
		
		Coordenada[] formato = new Coordenada[this.getQntdQuadrados()];
		
		//define o formato inicial do destroyer
		formato[0] = new Coordenada(0, 0);
		this.setFormato(formato);
		
	}
	
}
