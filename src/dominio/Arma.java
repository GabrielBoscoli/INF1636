package dominio;

/**
 * Classe abstrata que define propriedades comuns a todas as armas do jogo
 * @author Gabriel Boscoli
 *
 */
public abstract class Arma {

	//quantidade de quadrados que a arma ocupa na matriz de posicionamento
	protected int qntdQuadrados;
	
	//quantidade de rotações possiveis para a arma
	protected int qntdRotacoes;
	
	//formato da arma
	protected Coordenada[] formato;
	
	protected String tipoArma;
	
	protected boolean destruida = false;
	
	protected int quadradosIntactos;
	
	/**
	 * Construtor da classe arma
	 * @param qntdQuadrados quantidade de quadrados que a arma ocupa na matriz de posicionamento
	 * @param qntdRotacoes quantidade de rotações possiveis para a arma
	 */
	public Arma() {
	}

	public int getQntdQuadrados() {
		return qntdQuadrados;
	}

	protected void setQntdQuadrados(int qntdQuadrados) {
		this.qntdQuadrados = qntdQuadrados;
		quadradosIntactos = qntdQuadrados;
	}

	public int getQntdRotacoes() {
		return qntdRotacoes;
	}

	protected void setQntdRotacoes(int qntdRotacoes) {
		this.qntdRotacoes = qntdRotacoes;
	}

	public Coordenada[] getFormato() {
		return formato;
	}

	protected void setFormato(Coordenada[] formato) {
		this.formato = formato;
	}
	
	/**
	 * Retorna a altura da arma em quadrados
	 * @return altura da arma em quadrados
	 */
	public int getAltura() {
		if(formato == null) {
			return 0;
		}
		
		int maior = 0;
		int menor = 0;
		
		for(int i=0; i<formato.length; i++) {
			if(formato[i].getY() > maior) {
				maior = formato[i].getY();
			}
			if(formato[i].getY() < menor) {
				menor = formato[i].getY();
			}
		}
		
		return maior - menor + 1;
	}
	
	/**
	 * Retorna a largura da arma em quadrados
	 * @return largura da arma em quadrados
	 */
	public int getLargura() {
		if(formato == null) {
			return 0;
		}
		
		int maior = 0;
		int menor = 0;
		
		for(int i=0; i<formato.length; i++) {
			if(formato[i].getX() > maior) {
				maior = formato[i].getX();
			}
			if(formato[i].getY() < menor) {
				menor = formato[i].getY();
			}
		}
		
		return maior - menor + 1;
	}
	
	public abstract void rotacionaArma();
	
	public abstract void rotacionaArmaParaPosicaoOriginal();
	
	public abstract Arma clonaArma();

	public String getTipoArma() {
		return tipoArma;
	}

	protected void setTipoArma(String tipoArma) {
		this.tipoArma = tipoArma;
	}

	public boolean isDestruida() {
		return destruida;
	}
	
	public int getQuadradosIntactos() {
		return quadradosIntactos;
	}
	
	public void setQuadradosIntactos(int numQuadradosIntactos) {
		if(numQuadradosIntactos <= 0) {
			quadradosIntactos = 0;
			destruida = true;
		} else {
			quadradosIntactos = numQuadradosIntactos;
			destruida = false;
		}
	}
	
	public void sofreuTiro() {
		quadradosIntactos--;
		if(quadradosIntactos <= 0) {
			destruida = true;
			quadradosIntactos = 0;
		}
	}
	
}
