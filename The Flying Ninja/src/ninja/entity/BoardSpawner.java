package ninja.entity;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.geom.Polygon;

import ninja.entity.enemy.Board;
import ninja.entity.enemy.Enemy;
import ninja.game.IConstants;

public class BoardSpawner implements IConstants {

    ArrayList<Enemy> enemies;
    float boardTimer = 0;
    protected int minSpawnTime;
    protected int maxSpawnTime;
    Polygon BOARD_SHAPE;

    public BoardSpawner() {
        enemies = new ArrayList<Enemy>();
        minSpawnTime = 1;
        maxSpawnTime = 4;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void spawn() {
        enemies.add((Board) new Board("board", new Polygon(new float[] { 0, 0,
                BOARD_WIDTH, 0, BOARD_WIDTH, BOARD_HEIGHT, 0, BOARD_HEIGHT }))
                .init());
    }

    public void update(float delta) {
        boardTimer += delta / 1000;
        if (boardTimer > minSpawnTime) {
            Random rng = new Random(System.currentTimeMillis());
            int r1 = rng.nextInt();
            int spawnTime = maxSpawnTime - minSpawnTime;
            if ((int) r1 % spawnTime == 0 || boardTimer > (spawnTime)) {
                boardTimer = 0;
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
