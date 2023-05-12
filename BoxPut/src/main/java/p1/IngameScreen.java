package p1;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;

public class IngameScreen extends GameScreen {
	public static final String NAME = "INGAME-SCREEN";
	public long lastPlayed;

	public IngameScreen() {
		super(NAME);
		update();
	}
	
	public void update() { 
	  if (this.lastPlayed == 0) { 
	    Game.audio().playMusic("audio/a-small-miracle.mp3"); 
	    this.lastPlayed = Game.loop().getTicks(); 
	  }
	}
}