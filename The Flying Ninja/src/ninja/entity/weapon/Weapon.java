package ninja.entity.weapon;

import ninja.entity.Entity;
import ninja.game.IConstants;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Weapon extends Entity implements IConstants {

    public Weapon(String id, Polygon p) {
        super(id, p);
        
    }

    @Override
    public void updatePosition(float delta) {
        float d=delta;
        
        position.x += velocity.x * d;
        position.y += velocity.y * d;
        setPosition(new Vector2f(position.x, position.y));
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
        this.shape.setLocation(position);
    }

    public void update(GameContainer gc, StateBasedGame sb, float delta){
        updatePosition(delta);
    }


}
