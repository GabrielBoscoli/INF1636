package observer;

public interface IObservador {
	/**
	 * Notifica o observador de atualiza��es no observado
	 * @param observador - o observador a ser notificado
	 */
	void notify(IObservador observador);
}
