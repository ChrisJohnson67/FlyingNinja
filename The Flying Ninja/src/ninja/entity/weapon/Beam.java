package ninja.entity.weapon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import ninja.component.ImageRenderComponent;
import ninja.entity.Entity;
import ninja.game.IConstants;

public class Beam extends Weapon implements IConstants{

	public Beam(String id, Polygon p) {
		super(id, p);
	}
	
	public Entity init(float x, float y) {
        setVelocity(new Vector2f(0,0));
        setPosition(new Vector2f(x,  y));
        
        try {
            AddComponent( new ImageRenderComponent("Beam", new Image("assets/beam.png")) );
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
    
    public void updateBeam(GameContainer gc, StateBasedGame sb, float delta, Entity player){
        setPosition(new Vector2f(player.getPosition().x+30, player.getPosition().y));
    }

}
