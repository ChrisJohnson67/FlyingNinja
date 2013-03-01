package ninja.entity;

import java.util.ArrayList;
import java.util.Random;

import ninja.entity.enemy.Bird;
import ninja.entity.enemy.Enemy;
import ninja.game.IConstants;

import org.newdawn.slick.geom.Polygon;

public class BirdSpawner implements IConstants {

    ArrayList<Enemy> enemies;
    float timer = 0;
    protected int minSpawnTime;
    protected int maxSpawnTime;

    public BirdSpawner() {
        enemies = new ArrayList<Enemy>();
        minSpawnTime = 5;
        maxSpawnTime = 10;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void spawn() {
        enemies.add((Bird) new Bird("bird", new Polygon(new float[] { 0, 0,
                BIRD_WIDTH, 0, BIRD_WIDTH, BIRD_HEIGHT, 0, BIRD_HEIGHT }))
                .init());
    }

    public void update(float delta) {
        timer += delta / 1000;
        if (timer > minSpawnTime) {
            Random rng = new Random(System.currentTimeMillis());
            int r1 = rng.nextInt();
            int spawnTime = maxSpawnTime - minSpawnTime;
            if ((int) r1 % spawnTime == 0 || timer > (spawnTime)) {
                timer = 0;
                spawn();
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getPosition().x < 0) {
                enemies.remove(enemies.get(i));
            }
        }
    }

}
