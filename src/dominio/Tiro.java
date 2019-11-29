package dominio;

public class Tiro {

	//um tiro pode ser certeiro ou errado
	String tipo;
	
	//a coordenada do tiro
	Coordenada coordenada;
	
	public Tiro(String tipo, Coordenada coordenada) {
		if(!"certeiro".equals(tipo) && !"errado".equals(tipo)) {
			this.tipo = "indefinido";
		} else {
			this.tipo = tipo;
		}
		this.coordenada = coordenada;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public Coordenada getCoordenada() {
		return coordenada;
	}
}
