package ninja.compenent.skill;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import ninja.compenent.ImageRenderComponent;
import ninja.entity.Entity;
import ninja.game.IConstants;

public class SkillQ extends Entity implements IConstants{

    public SkillQ(String id, Polygon p) {
        super(id, p);
        // TODO Auto-generated constructor stub
    }
    
public Entity init(){
        
        setPosition(new Vector2f(SCREEN_WIDTH/2 - SKILL_HEIGHT*2 -32, SCREEN_HEIGHT-SKILL_HEIGHT));
        
        try {
            AddComponent( new ImageRenderComponent("skillq", new Image("assets/skillQ.png")) );
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return this;
    }

@Override
public void updatePosition(float delta) {
    // TODO Auto-generated method stub
    
}

@Override
public void setPosition(Vector2f position) {
    this.position = position;
    this.shape.setLocation(position);
    
}

@Override
public void update(GameContainer gc, StateBasedGame sb, float delta) {
    // TODO Auto-generated method stub
    
}
       
}
