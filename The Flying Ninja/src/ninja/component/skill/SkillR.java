package ninja.component.skill;

import ninja.component.ImageRenderComponent;
import ninja.entity.Entity;
import ninja.entity.weapon.Beam;
import ninja.entity.weapon.Star;
import ninja.game.Game;
import ninja.game.IConstants;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class SkillR extends Skill implements IConstants {

	private double beamTimer;
	private boolean beamOn;
	
	public SkillR(String id, Polygon p) {
		super(id, p);
		beamTimer=0.0;
		beamOn=false;
	}

	public Entity init() {

		setPosition(new Vector2f(SCREEN_WIDTH / 2 + SKILL_HEIGHT + 64, SCREEN_HEIGHT - SKILL_HEIGHT));

		try {
			AddComponent(new ImageRenderComponent("skillr", new Image("assets/skillR.png")));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	public void update(GameContainer gc, StateBasedGame sb, float delta) {
		if (cooldown <= 0) {
			cooldown = 0;
			onCooldown = false;

		} else if (onCooldown) {
			if(beamTimer >=5000){
				beamOn=false;
			}
			beamTimer+=delta;
			cooldown -= delta;
		}

	}
	
	@Override
	public void execute(Game game) {
		if (cooldown <= 0) {
			beamOn=true;
			beamTimer=0.0;
			game.getWeaponList().add((Beam) new Beam("beam", new Polygon(new float[] { 0, 0,
							BEAM_WIDTH, 0, BEAM_WIDTH, BEAM_HEIGHT, 0,
							BEAM_HEIGHT })).init(
							game.getPlayer().getPosition().x+5, game.getPlayer()
									.getPosition().y));
			cooldown = R_COOLDOWN;
			onCooldown = true;
		}

	}

	public double getBeamTimer() {
		return beamTimer;
	}

	public void setBeamTimer(double beamTimer) {
		this.beamTimer = beamTimer;
	}

	public boolean isBeamOn() {
		return beamOn;
	}

	public void setBeamOn(boolean beamOn) {
		this.beamOn = beamOn;
	}

}
