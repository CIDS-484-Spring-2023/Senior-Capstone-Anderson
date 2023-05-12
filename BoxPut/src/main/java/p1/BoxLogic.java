package p1;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.FreeFlightCamera;;

public final class BoxLogic {

	private BoxLogic() {
	}

	// initializes the game logic
	public static void init() {

		// creating the camera and confining it to the map
		Camera camera = new Camera();
		camera.setClampToMap(true);
		camera.setZoom((float) .29, 0);
		camera.setFocus(700, 420);
		Game.world().setCamera(camera);

		// sets the gravity
		Game.world().setGravity(120);

		// add default game logic for when a level was loaded
		Game.world().onLoaded(e -> {

			// spawns the player instance on the spawn point
			Spawnpoint spawnpoint = e.getSpawnpoint("spawnpoint");
			if (spawnpoint != null) {
				spawnpoint.spawn(Player.instance());
			}
		});

	}
}