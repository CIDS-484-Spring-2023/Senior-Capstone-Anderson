package p1;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;

public class Program {

  public static void main(String[] args) {
	  
	  //sets information about the game 
	  Game.info().setName("BoxPut"); 
	  Game.info().setSubTitle("");
	  Game.info().setVersion("v0.1"); 
	  Game.info().setWebsite(""); 
	  Game.info().setDescription("golf but box");
	  
	  
	  // initializes the Litiengine infrastructure
	  Game.init(args);

	  // loads data from the utiLITI game file 
	  Resources.load("game.litidata"); 

	  // loads the first level
	  Game.world().loadEnvironment("level1"); 
	  //starts the game
	  Game.start();
  }
}