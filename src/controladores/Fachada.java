package controladores;

import observer.IObservador;

public class Fachada {
	ControladorPosicionamento ctrl;
	static Fachada f=null;
	
	private Fachada() {
		//ctrl = new ControladorPosicionamento();
	}
	
	public static Fachada getFachada() {
		if(f==null)
			f=new Fachada();
		
		return f;	
	}
	
	public void register(IObservador o) {
		ctrl.add(o);
	}
}
