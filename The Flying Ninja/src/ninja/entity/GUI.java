package ninja.entity;

import ninja.compenent.ImageRenderComponent;
import ninja.game.IConstants;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class GUI extends Entity implements IConstants{

    public GUI(String id, Polygon p) {
        super(id, p);
    }
    
    public Entity init(){
        try {
            AddComponent( new ImageRenderComponent("GUIRender", new Image("assets/ninja_gui.png")) );
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setPosition(new Vector2f(0,0));
        return this;
    }

    @Override
    public void updatePosition(float delta) {       

    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
        this.shape.setLocation(position);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, float delta) {     
    }

}
