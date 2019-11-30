package armas;

import dominio.Arma;
import dominio.Coordenada;

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
		this.setTipoArma("submarino");
		
		Coordenada[] formato = new Coordenada[this.getQntdQuadrados()];
		
		//define o formato inicial do destroyer
		formato[0] = new Coordenada(0, 0);
		this.setFormato(formato);
		
	}
	
	private Submarino(Coordenada[] formato, boolean destruida, int numQuadradosIntactos) {
		qntdQuadrados = 1;
		qntdRotacoes = 1;
		this.formato = formato;
		tipoArma = "submarino";
		this.destruida = destruida;
		quadradosIntactos = numQuadradosIntactos;
	}
	
	@Override
	public void rotacionaArma() {}
	
	@Override
	public void rotacionaArmaParaPosicaoOriginal() {}

	@Override
	public Arma clonaArma() {
		return new Submarino(this.formato, destruida, quadradosIntactos);
	}
	
}
