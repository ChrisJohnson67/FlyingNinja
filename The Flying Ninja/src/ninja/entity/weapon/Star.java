package ninja.entity.weapon;

import ninja.component.ImageRenderComponent;
import ninja.entity.Entity;
import ninja.game.IConstants;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

public class Star extends Weapon implements IConstants {

    public Star(String id, Polygon p) {
        super(id, p);
        
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
