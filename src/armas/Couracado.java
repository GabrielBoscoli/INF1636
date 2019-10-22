package armas;

/**
 * Classe que define a arma Coura�ado
 * @author Gabriel Boscoli
 *
 */
public class Couracado extends Arma {

	/**
	 * Construtor da classe Coura�ado
	 * Cria a arma com as propriedades de um coura�ado
	 * @param qntdQuadrados qntdQuadrados quantidade de quadrados que a arma ocupa na matriz de posicionamento
	 * @param qntdRotacoes quantidade de rota��es possiveis para a arma
	 */
	public Couracado() {
		
		this.setQntdQuadrados(5);
		this.setQntdRotacoes(2);
		
	}

}
