package armas;

/**
 * Classe que define a arma Cruzador
 * @author Gabriel Boscoli
 *
 */
public class Cruzador extends Arma{

	/**
	 * Construtor da classe Cruzador
	 * Cria a arma com as propriedades de um coura�ado
	 * @param qntdQuadrados qntdQuadrados quantidade de quadrados que a arma ocupa na matriz de posicionamento
	 * @param qntdRotacoes quantidade de rota��es possiveis para a arma
	 */
	public Cruzador() {
		
		this.setQntdQuadrados(4);
		this.setQntdRotacoes(2);
		
	}
	
}
