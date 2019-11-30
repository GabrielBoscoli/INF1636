package armas;

import dominio.Arma;
import dominio.Coordenada;

/**
 * Classe que define a arma Cruzador
 * @author Gabriel Boscoli
 *
 */
public class Cruzador extends Arma{

	/**
	 * Construtor da classe Cruzador
	 * Cria a arma com as propriedades de um couraçado
	 * @param qntdQuadrados qntdQuadrados quantidade de quadrados que a arma ocupa na matriz de posicionamento
	 * @param qntdRotacoes quantidade de rotações possiveis para a arma
	 */
	public Cruzador() {
		
		this.setQntdQuadrados(4);
		this.setQntdRotacoes(2);
		this.setTipoArma("cruzador");
		
		Coordenada[] formato = new Coordenada[this.getQntdQuadrados()];
		
		//define o formato inicial do cruzador
		for(int i = 0; i < formato.length; i++) {
			
			formato[i] = new Coordenada(i, 0);
			
		}
		this.setFormato(formato);
		
	}
	
	private Cruzador(Coordenada[] formato, boolean destruida, int numQuadradosIntactos) {
		qntdQuadrados = 4;
		qntdRotacoes = 2;
		this.formato = formato;
		tipoArma = "cruzador";
		this.destruida = destruida;
		quadradosIntactos = numQuadradosIntactos;
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
	
	@Override
	public void rotacionaArmaParaPosicaoOriginal() {
		Coordenada[] formato = new Coordenada[this.getQntdQuadrados()];
		
		for(int i = 0; i < formato.length; i++) {
			
			formato[i] = new Coordenada(i, 0);
			
		}
		this.setFormato(formato);
		
	}

	@Override
	public Arma clonaArma() {
		return new Cruzador(this.formato, destruida, quadradosIntactos);
	}
	
}
