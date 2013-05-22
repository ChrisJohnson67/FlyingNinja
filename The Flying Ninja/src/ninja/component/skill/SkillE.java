package ninja.component.skill;

import ninja.component.ImageRenderComponent;
import ninja.entity.Entity;
import ninja.game.Game;
import ninja.game.IConstants;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

public class SkillE extends Skill implements IConstants {

	public SkillE(String id, Polygon p) {
		super(id, p);
		// TODO Auto-generated constructor stub
	}

	public Entity init() {

		setPosition(new Vector2f(SCREEN_WIDTH / 2 + 32, SCREEN_HEIGHT
				- SKILL_HEIGHT));

		try {
			AddComponent(new ImageRenderComponent("skille", new Image(
					"assets/skillE.png")));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	@Override
	public void execute(Game game) {
		if (cooldown <= 0) {
			if (game.getHealth() <= 100) {
				game.setHealth(Math.min(100, game.getHealth() + 30));
			}
			cooldown = E_COOLDOWN;
			onCooldown = true;
		}

	}

}
