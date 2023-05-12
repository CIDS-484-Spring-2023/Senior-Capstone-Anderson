package p1;

import java.util.Optional;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityInfo;
import de.gurkenlabs.litiengine.abilities.effects.EffectApplication;
import de.gurkenlabs.litiengine.abilities.effects.EffectTarget;
import de.gurkenlabs.litiengine.abilities.effects.ForceEffect;
import de.gurkenlabs.litiengine.entities.CollisionBox;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityPivotType;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.physics.Force;
import de.gurkenlabs.litiengine.physics.GravityForce;

@AbilityInfo(cooldown = 500, origin = EntityPivotType.COLLISIONBOX_CENTER, duration = 300, value = 240)
public class Jump extends Ability {

	public Jump(Creature executor) {
		super(executor);
		this.addEffect(new JumpEffect(this));
	}

	private class JumpEffect extends ForceEffect {

		protected JumpEffect(Ability ability) {
			super(ability, ability.getAttributes().value().get().intValue(), EffectTarget.EXECUTINGENTITY);
		}

		@Override
		protected Force applyForce(IMobileEntity affectedEntity) {
			// creates a new force and apply it to the player
			GravityForce force = new GravityForce(affectedEntity, this.getStrength(), Direction.UP);
			affectedEntity.movement().apply(force);
			return force;
		}

		@Override
		protected boolean hasEnded(final EffectApplication appliance) {
			return super.hasEnded(appliance) || this.isTouchingCeiling();
		}

		// handles the case where a jump hits a ceiling
		private boolean isTouchingCeiling() {
			Optional opt = Game.world().environment().getCollisionBoxes().stream()
					.filter(x -> x.getBoundingBox().intersects(this.getAbility().getExecutor().getBoundingBox()))
					.findFirst();
			if (!opt.isPresent()) {
				return false;
			}

			CollisionBox box = (CollisionBox) opt.get();
			return box.getCollisionBox().getMaxY() <= this.getAbility().getExecutor().getCollisionBox().getMinY();
		}
	}
}