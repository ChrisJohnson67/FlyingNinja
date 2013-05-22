package ninja.component.skill;

import ninja.component.ImageRenderComponent;
import ninja.entity.Entity;
import ninja.entity.weapon.Star;
import ninja.game.Game;
import ninja.game.IConstants;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

public class SkillQ extends Skill implements IConstants {

	public SkillQ(String id, Polygon p) {
		super(id, p);
		// TODO Auto-generated constructor stub
	}

	public Entity init() {

		setPosition(new Vector2f(SCREEN_WIDTH / 2 - SKILL_HEIGHT * 2 - 32,
				SCREEN_HEIGHT - SKILL_HEIGHT));

		try {
			AddComponent(new ImageRenderComponent("skillq", new Image(
					"assets/skillQ.png")));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	@Override
	public void execute(Game game) {
		if (cooldown <= 0) {
			game.getWeaponList().add(
					(Star) new Star("star", new Polygon(new float[] { 0, 0,
							STAR_WIDTH, 0, STAR_WIDTH, STAR_HEIGHT, 0,
							STAR_HEIGHT })).init(
							game.getPlayer().getPosition().x, game.getPlayer()
									.getPosition().y));
			cooldown = Q_COOLDOWN;
			onCooldown = true;
		}

	}

}
