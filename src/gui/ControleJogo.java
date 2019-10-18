package gui;

public class ControleJogo {

	private static ControleJogo gamePresenter = null;
	private FramePosicionamento positioningFrame;
	private FrameJogadores playersNamingFrame;
	
	/**
	 * Initializes the facade
	 */
	private ControleJogo() {
	}
	
	/**
	 * If not already initialized: initializes the singleton
	 * as an single instance and returns it
	 * If initialized only returns the instance
	 * @return the instanced Game Controller
	 */
	public static ControleJogo getMainGamePresenter() {
		if(gamePresenter == null) {
			gamePresenter = new ControleJogo();
		}
		return gamePresenter;
	}
	
	/**
	 * Initializes the naming frame and sets it visible
	 */
	public void initiateGame() { 
		playersNamingFrame = new FrameJogadores();
		playersNamingFrame.setTitle("Batalha Naval"); 
		playersNamingFrame.setVisible(true);
	}
	
	/**
	 * Closes naming frame
	 */
	public void closeNaming() {
		playersNamingFrame.setVisible(false);
	}
	
	/**
	 * Set the turn for player1 and initilizes the Positioning frame
	 * to start the board building phase
	 */
	public void showPositioning() {
		positioningFrame = new FramePosicionamento();
		positioningFrame.setVisible(true);
	}
	
	
	
}