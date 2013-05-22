package ninja.component.skill;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import ninja.entity.Entity;
import ninja.game.Game;

public abstract class Skill extends Entity {

	protected double cooldown;
	protected boolean onCooldown;

	public double getCooldown() {
		return cooldown;
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

	public Skill(String id, Polygon p) {
		super(id, p);
		cooldown = 0;
		onCooldown = false;
		// TODO Auto-generated constructor stub
	}

	public boolean isOnCooldown() {
		return onCooldown;
	}

	public void setOnCooldown(boolean onCooldown) {
		this.onCooldown = onCooldown;
	}

	@Override
	public void updatePosition(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPosition(Vector2f position) {
		this.position = position;
		this.shape.setLocation(position);

	}

	public void update(GameContainer gc, StateBasedGame sb, float delta) {
		if (cooldown <= 0) {
			cooldown = 0;
			onCooldown = false;

		} else if (onCooldown) {
			cooldown -= delta;
		}

	}

	public abstract void execute(Game game);
}
