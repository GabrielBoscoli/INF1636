package armas;

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
	Submarino() {
		
		this.setQntdQuadrados(1);
		this.setQntdRotacoes(1);
		
	}
	
}
