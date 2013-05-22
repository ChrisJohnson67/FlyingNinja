package ninja.component.skill;

import ninja.component.ImageRenderComponent;
import ninja.entity.Entity;
import ninja.game.Game;
import ninja.game.IConstants;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class SkillW extends Skill implements IConstants {

	private double invincibleTime;
	private boolean invincible;

	public SkillW(String id, Polygon p) {
		super(id, p);
		invincibleTime = 0.0;
		invincible=false;
	}

	public Entity init() {

		setPosition(new Vector2f(SCREEN_WIDTH / 2 - SKILL_HEIGHT, SCREEN_HEIGHT
				- SKILL_HEIGHT));

		try {
			AddComponent(new ImageRenderComponent("skillw", new Image(
					"assets/skillW.png")));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, float delta) {		
		if (cooldown <= 0) {
			cooldown = 0;
			onCooldown = false;
			

		} else if (onCooldown) {
			if(invincibleTime >=3000){
				invincible=false;
			}
			cooldown -= delta;
			invincibleTime+=delta;
		}

	}

	@Override
	public void execute(Game game) {
		if (cooldown <= 0) {
			invincibleTime=0.0;
			invincible=true;
			onCooldown = true;
			cooldown = W_COOLDOWN;
		}

	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

}
