package p1;

import java.awt.geom.Rectangle2D;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Action;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import de.gurkenlabs.litiengine.physics.Collision;
import de.gurkenlabs.litiengine.physics.IMovementController;

//attributes of the box
@EntityInfo(width = 35, height = 35)
@MovementInfo(velocity = 100, acceleration = 100)
@CollisionInfo(collisionBoxWidth = 35, collisionBoxHeight = 35, collision = true)
public class Player extends Creature implements IUpdateable {

	private static Player instance;
	public static final int MAX_ADDITIONAL_JUMPS = 1;
	private final Jump jump;
	private int consecutiveJumps;

	public static Player instance() {
		if (instance == null) {
			instance = new Player();
		}

		return instance;
	}

	private Player() {
		super("sprites/Box.png");

		// setup the player's abilities
		this.jump = new Jump(this);
	}

	@Override
	public void update() {
		// resets the ability to jump when touching the ground
		if (this.isTouchingGround()) {
			this.consecutiveJumps = 0;
		}
	}

	@Override
	protected IMovementController createMovementController() {
		// setup movement (left, right) controller
		return new PlatformingMovementController<>(this);
	}

	@Action(description = "This performs the jump ability for the player's entity.")
	public void jump() {
		if (this.consecutiveJumps >= MAX_ADDITIONAL_JUMPS || !this.jump.canCast()) {
			return;
		}

		this.jump.cast();
		this.consecutiveJumps++;
	}

	private boolean isTouchingGround() {
		// extends the collision by 1 pixel to see if it is touching the ground
		Rectangle2D groundCheck = new Rectangle2D.Double(this.getCollisionBox().getX(), this.getCollisionBox().getY(),
				this.getCollisionBoxWidth(), this.getCollisionBoxHeight() + 1);

		// if it collides with the map's boundaries
		if (groundCheck.getMaxY() > Game.physics().getBounds().getMaxY()) {
			return true;
		}

		return Game.physics().collides(groundCheck, Collision.STATIC);
	}
}