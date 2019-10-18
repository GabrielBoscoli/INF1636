package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

public class DimensaoTela {

	public final double screenCenterX;
	public final double screenCenterY;
	public final double screenWidth;
	public final double screenHeight;
	public final int screenIntWidth;
	public final int screenIntHeight;
	public final Dimension screenSize;
	
	private static DimensaoTela dimensaoTela = null;
	
	private DimensaoTela() {
		this.screenSize = getScreenSize();
		this.screenWidth = screenSize.getWidth();
		this.screenHeight = screenSize.getHeight();
		this.screenIntWidth = screenSize.width;
		this.screenIntHeight = screenSize.height;
		this.screenCenterX = screenWidth / 2;
		this.screenCenterY = screenHeight / 2;
	}
	
	public static DimensaoTela getScreenDimensions() {
		if(dimensaoTela == null) {
			dimensaoTela = new DimensaoTela();
		}
		return dimensaoTela;
	}
	
	private Dimension getScreenSize() {
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension screenSize = tk.getScreenSize(); 
		return screenSize;
	}
	
}