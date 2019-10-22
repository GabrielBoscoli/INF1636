package armas;

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
		
	}
	
}
