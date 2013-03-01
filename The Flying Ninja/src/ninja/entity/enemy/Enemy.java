package ninja.entity.enemy;

import ninja.entity.Entity;
import ninja.game.IConstants;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Enemy extends Entity implements IConstants {

    public Enemy(String id, Polygon p) {
        super(id, p);
    }

    @Override
    public void updatePosition(float delta) {
        float d=delta;
        
        this.position.x += this.velocity.x * d;
        this.position.y += this.velocity.y * d;
        setPosition(new Vector2f(this.position.x, this.position.y));
/*        shape.setX(shape.getX() + velocity.x * d);
        shape.setY(shape.getY() + velocity.y * d);*/
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
        this.shape.setLocation(position);
    }

    public abstract void update(GameContainer gc, StateBasedGame sb, float delta); 

}
