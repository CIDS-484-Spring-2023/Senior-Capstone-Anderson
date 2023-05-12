package p1;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.resources.Resources;

public class Program {

	public static void main(String[] args) {

		// sets information about the game
		Game.info().setName("BoxPut");
		Game.info().setSubTitle("");
		Game.info().setVersion("v0.2");
		Game.info().setWebsite("https://github.com/CIDS-484-Spring-2023/Senior-Capstone-Anderson/tree/main");
		Game.info().setDescription("golf but box");

		// initializes the Litiengine infrastructure
		Game.init(args);

		// sets the icon for the game
		Game.window().setIcon(Resources.images().get("sprites/Box.png"));
		Game.graphics().setBaseRenderScale(4f);

		// loads data from the utiLITI game file
		Resources.load("game.litidata");
		
		// initializes the game logic
		//PlayerInput.init();
	    BoxLogic.init();

		// adds the screen (allows us to see what is happening)
		Game.screens().add(new IngameScreen());

		// loads the first level
		Game.world().loadEnvironment("level1");

		// starts the game
		Game.start();
	}
}