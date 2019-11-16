package dominio;

import outros.Coordenada;

public class Tiro {

	//um tiro pode ser certeiro ou errado
	String tipo;
	
	//a coordenada do tiro
	Coordenada coordenada;
	
	public Tiro(String tipo, Coordenada coordenada) {
		if(tipo != "certeiro" && tipo != "errado") {
			this.tipo = "errado";
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
