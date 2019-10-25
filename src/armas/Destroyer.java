package armas;

import outros.Coordenada;

/**
 * Classe que define a arma Destroyer
 * @author Gabriel Boscoli
 *
 */
public class Destroyer extends Arma {

	/**
	 * Construtor da classe Destroyer
	 * Cria a arma com as propriedades de um couraçado
	 * @param qntdQuadrados qntdQuadrados quantidade de quadrados que a arma ocupa na matriz de posicionamento
	 * @param qntdRotacoes quantidade de rotações possiveis para a arma
	 */
	public Destroyer() {
		
		this.setQntdQuadrados(2);
		this.setQntdRotacoes(2);
		
		Coordenada[] formato = new Coordenada[this.getQntdQuadrados()];
		
		//define o formato inicial do destroyer
		for(int i = 0; i < formato.length; i++) {
			
			formato[i] = new Coordenada(i, 0);
			
		}
		this.setFormato(formato);
	}
	
}
