package armas;

import outros.Coordenada;

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
		
		Coordenada[] formato = new Coordenada[this.getQntdQuadrados()];
		
		//define o formato inicial do couracado
		for(int i = 0; i < formato.length; i++) {
			
			formato[i] = new Coordenada(i, 0);
			
		}
		this.setFormato(formato);
	}

	@Override
	public void rotacionaArma() {
		Coordenada[] novoFormato = new Coordenada[this.getQntdQuadrados()];
		Coordenada[] formatoAntigo = getFormato();
		
		for(int i = 0; i < formatoAntigo.length; i++) {
			novoFormato[i] = new Coordenada(formatoAntigo[i].getY(), formatoAntigo[i].getX());
		}
		
		setFormato(novoFormato);
	}

}
