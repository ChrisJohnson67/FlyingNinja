package ninja.entity.enemy;

import java.util.Random;

import ninja.compenent.ImageRenderComponent;
import ninja.entity.Entity;
import ninja.game.IConstants;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Bird extends Enemy implements IConstants {

    public Bird(String id, Polygon p) {
        super(id, p);
        
    }

    public Entity init(){
        
        setVelocity(new Vector2f(-0.3f,0.2f));
        
        try {
            AddComponent( new ImageRenderComponent("BirdRender", new Image("assets/bird.png")) );
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Random rng = new Random(System.currentTimeMillis());
        int r1 = rng.nextInt();
        if(r1<0) r1*=-1;
        this.setPosition(new Vector2f(SCREEN_WIDTH, Math.min(((int) r1 % SCREEN_HEIGHT) + GUI_HEIGHT + BIRD_HEIGHT, SCREEN_HEIGHT-BIRD_HEIGHT-64)));
        return this;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, float delta) {
        if(this.position.y > SCREEN_HEIGHT - BOTTOM_GUI_HEIGHT - BIRD_HEIGHT || this.position.y < 0 + GUI_HEIGHT + BIRD_HEIGHT){
            this.velocity.y*=-1;
        }
        this.updatePosition(delta);
    }

}
