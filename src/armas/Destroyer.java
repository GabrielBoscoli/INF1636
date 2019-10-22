package armas;

/**
 * Classe que define a arma Destroyer
 * @author Gabriel Boscoli
 *
 */
public class Destroyer extends Arma {

	/**
	 * Construtor da classe Destroyer
	 * Cria a arma com as propriedades de um coura�ado
	 * @param qntdQuadrados qntdQuadrados quantidade de quadrados que a arma ocupa na matriz de posicionamento
	 * @param qntdRotacoes quantidade de rota��es possiveis para a arma
	 */
	Destroyer() {
		
		this.setQntdQuadrados(2);
		this.setQntdRotacoes(2);
		
	}
	
}
