package armas;

/**
 * Classe que define a arma Couraçado
 * @author Gabriel Boscoli
 *
 */
public class Couracado extends Arma {

	/**
	 * Construtor da classe Couraçado
	 * Cria a arma com as propriedades de um couraçado
	 * @param qntdQuadrados qntdQuadrados quantidade de quadrados que a arma ocupa na matriz de posicionamento
	 * @param qntdRotacoes quantidade de rotações possiveis para a arma
	 */
	public Couracado() {
		
		this.setQntdQuadrados(5);
		this.setQntdRotacoes(2);
		
	}

}
