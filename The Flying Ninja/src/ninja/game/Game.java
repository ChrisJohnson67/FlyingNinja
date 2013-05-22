package ninja.game;

import java.awt.Font;
import java.util.ArrayList;

import ninja.component.skill.Skill;
import ninja.component.skill.SkillE;
import ninja.component.skill.SkillQ;
import ninja.component.skill.SkillR;
import ninja.component.skill.SkillW;
import ninja.entity.Background;
import ninja.entity.BackgroundCopy;
import ninja.entity.BirdSpawner;
import ninja.entity.BoardSpawner;
import ninja.entity.Entity;
import ninja.entity.GUI;
import ninja.entity.Player;
import ninja.entity.weapon.Beam;
import ninja.entity.weapon.Star;
import ninja.entity.weapon.Weapon;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Game extends BasicGame implements IConstants {

	static AppGameContainer app;
	ArrayList<Entity> entityList;
	ArrayList<Weapon> weaponList;
	Player player;
	Background background;
	BackgroundCopy background_copy;
	BoardSpawner boardSpawn;
	BirdSpawner birdSpawn;
	Entity gui;
	SkillQ skillQ;
	SkillW skillW;
	SkillE skillE;
	SkillR skillR;
	float score;
	int health;
	UnicodeFont ttf;
	UnicodeFont ttfTitle;

	public Game() {
		super("The Flying Ninja");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		entityList = new ArrayList<Entity>();
		boardSpawn = new BoardSpawner();
		weaponList = new ArrayList<Weapon>();
		birdSpawn = new BirdSpawner();

		health = 100;
		score = 0;
		Font font = new Font("Verdana", Font.BOLD, 20);
		Font fontTitle = new Font("Verdana", Font.BOLD, 44);
		ttf = new UnicodeFont(font);
		ttfTitle = new UnicodeFont(fontTitle);
		ttf.addAsciiGlyphs();
		ttfTitle.addAsciiGlyphs();
		ttf.getEffects().add(new ColorEffect());
		ttfTitle.getEffects().add(new ColorEffect());
		ttf.loadGlyphs();
		ttfTitle.loadGlyphs();
		ttf.getEffects();

		player = new Player("player", PLAYER_SHAPE);
		background = new Background("background", BACKGROUND_SHAPE);
		background_copy = new BackgroundCopy("background_copy", BACKGROUND_SHAPE);
		gui = new GUI("gui", BACKGROUND_SHAPE);

		skillQ = new SkillQ("q", SKILL_SHAPE);
		skillW = new SkillW("w", SKILL_SHAPE);
		skillE = new SkillE("e", SKILL_SHAPE);
		skillR = new SkillR("r", SKILL_SHAPE);

		entityList.add(background_copy.init());
		entityList.add(background.init());
		entityList.add(player.init());
		entityList.add(gui.init());
		entityList.add(skillQ.init());
		entityList.add(skillW.init());
		entityList.add(skillE.init());
		entityList.add(skillR.init());
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		for (int i = 0; i < entityList.size(); i++) {
			entityList.get(i).update(gc, null, delta);
		}

		boardSpawn.update(delta);
		for (int i = 0; i < boardSpawn.getEnemies().size(); i++) {
			boardSpawn.getEnemies().get(i).update(gc, null, (float) delta);
		}
		birdSpawn.update(delta);
		for (int i = 0; i < birdSpawn.getEnemies().size(); i++) {
			birdSpawn.getEnemies().get(i).update(gc, null, (float) delta);
		}
		ArrayList<Weapon> tempList=new ArrayList<Weapon>();
		for (int i = 0; i < weaponList.size(); i++) {
			
			if (!(weaponList.get(i) instanceof Beam)) {
				weaponList.get(i).update(gc, null, (float) delta);
			} else {
				((Beam) weaponList.get(i)).updateBeam(gc, null, (float) delta, player);
				if (!skillR.isBeamOn()) {
					tempList.add(weaponList.get(i));
				}
			}
			if (weaponList.get(i).getPosition().x > SCREEN_WIDTH) {
				tempList.add(weaponList.get(i));
			}
		}
		weaponList.removeAll(tempList);
		score += (float) delta / 100;
		checkSkillInput(gc);
		checkCollisions();

		checkIfLost();
	}

	public void render(GameContainer gc, Graphics gr) throws SlickException {
		for (int i = 0; i < entityList.size(); i++) {
			if (entityList.get(i) instanceof Skill) {
				entityList.get(i).renderSkill(gc, null, gr, ((Skill) entityList.get(i)).isOnCooldown());
				if (((Skill) entityList.get(i)).isOnCooldown()) {
					ttf.drawString(entityList.get(i).getPosition().x, entityList.get(i).getPosition().y + 14,
							String.valueOf(((Skill) entityList.get(i)).getCooldown() / 1000), Color.red);
				}
			} else if (entityList.get(i) instanceof Player) {
				entityList.get(i).renderSkill(gc, null, gr, skillW.isInvincible());
			} else {
				entityList.get(i).render(gc, null, gr);
			}
		}
		for (int i = 0; i < boardSpawn.getEnemies().size(); i++) {
			boardSpawn.getEnemies().get(i).render(gc, null, gr);
		}
		for (int i = 0; i < birdSpawn.getEnemies().size(); i++) {
			birdSpawn.getEnemies().get(i).render(gc, null, gr);
		}
		for (int i = 0; i < weaponList.size(); i++) {
			weaponList.get(i).render(gc, null, gr);
		}
		ttf.drawString(SCREEN_WIDTH - 200, 30, "Score:   " + (int) score, Color.black);
		ttf.drawString(100, 30, "Life:  " + (int) health, Color.black);
		ttfTitle.drawString(440, 15, "The Flying Ninja", Color.black);

	}

	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new Game());

		app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		app.setTargetFrameRate(10000);
		app.start();
	}

	public void checkCollisions() {
		checkCollisionWithPlayer();
		checkCollisionWithWeapons();
	}

	public void checkCollisionWithWeapons() {
		for (int i = 0; i < weaponList.size(); i++) {
			for (int j = 0; j < boardSpawn.getEnemies().size(); j++) {
				if (weaponList.get(i).getShape().intersects(boardSpawn.getEnemies().get(j).getShape())) {
					if (weaponList.get(i) instanceof Star) {
						weaponList.remove(i);
					}
					boardSpawn.getEnemies().remove(j);
				}
			}
		}

		for (int i = 0; i < weaponList.size(); i++) {
			for (int j = 0; j < birdSpawn.getEnemies().size(); j++) {
				if (weaponList.get(i).getShape().intersects(birdSpawn.getEnemies().get(j).getShape())) {
					if (weaponList.get(i) instanceof Star) {
						weaponList.remove(i);
					}
					birdSpawn.getEnemies().remove(j);
				}
			}
		}
	}

	public void checkCollisionWithPlayer() {
		// check for collisions if player is not invincible
		if (!skillW.isInvincible()) {
			for (int i = 0; i < boardSpawn.getEnemies().size(); i++) {
				if (player.getShape().intersects(boardSpawn.getEnemies().get(i).getShape())) {
					boardSpawn.getEnemies().remove(i);
					health -= 10;
				}
			}

			for (int i = 0; i < birdSpawn.getEnemies().size(); i++) {
				if (player.getShape().intersects(birdSpawn.getEnemies().get(i).getShape())) {
					birdSpawn.getEnemies().remove(i);
					health -= 10;
				}
			}
		}
	}

	public void checkSkillInput(GameContainer gc) {
		if (gc.getInput().isKeyPressed(Input.KEY_Q)) {
			skillQ.execute(this);
		}
		if (gc.getInput().isKeyPressed(Input.KEY_W)) {
			skillW.execute(this);
		}
		if (gc.getInput().isKeyPressed(Input.KEY_E)) {
			skillE.execute(this);
		}
		if (gc.getInput().isKeyPressed(Input.KEY_R)) {
			skillR.execute(this);
		}
	}

	public ArrayList<Weapon> getWeaponList() {
		return weaponList;
	}

	public void setWeaponList(ArrayList<Weapon> weaponList) {
		this.weaponList = weaponList;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void checkIfLost() {
		if (health < 0) {
			lose();
		}
	}

	public void lose() {
		System.out.println("You Lose!");
		app.exit();
	}
}