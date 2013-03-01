package ninja.entity;

import ninja.compenent.ImageRenderComponent;
import ninja.game.IConstants;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class BackgroundCopy extends Entity implements IConstants{

    public BackgroundCopy(String id, Polygon p) {
        super(id, p);
        setVelocity(new Vector2f(-0.3f,0f));
    }

    public Entity init(){
        try {
            AddComponent( new ImageRenderComponent("BackgroundRender", new Image("assets/bkg.png")) );
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setPosition(new Vector2f(BACKGROUND_WIDTH,0));
        return this;
    }
    
    @Override
    public void updatePosition(float delta) {       
        position.x+=velocity.x*delta;
        position.y+=velocity.y*delta;
        setPosition(new Vector2f(position.x,position.y));
        
      //reset position of background to loop
        if(position.x<=-BACKGROUND_WIDTH){
            setPosition(new Vector2f(BACKGROUND_WIDTH-5,0));
        }
        
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
        this.shape.setLocation(position);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, float delta) {
        updatePosition(delta);        
    }

}
