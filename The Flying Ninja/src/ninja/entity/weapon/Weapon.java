package ninja.entity.weapon;

import ninja.compenent.ImageRenderComponent;
import ninja.entity.Entity;
import ninja.game.IConstants;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
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

       /* shape.setX(shape.getX() + velocity.x * d);
        shape.setY(shape.getY() + velocity.y * d);*/
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
        this.shape.setLocation(position);
    }

    public void update(GameContainer gc, StateBasedGame sb, float delta){
        updatePosition(delta);
    }

    public Entity init(float x, float y) {
        setVelocity(new Vector2f(2.0f,0));
        setPosition(new Vector2f(x,  y));
        
        try {
            AddComponent( new ImageRenderComponent("Star", new Image("assets/ninjaStar.png")) );
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public Entity init() {
        // TODO Auto-generated method stub
        return null;
    }

}
