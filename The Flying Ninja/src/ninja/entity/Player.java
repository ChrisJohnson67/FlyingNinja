package ninja.entity;

import ninja.component.Component;
import ninja.component.ImageRenderComponent;
import ninja.component.TopDownMovement;
import ninja.game.IConstants;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Entity implements IConstants {

    public Player(String id, Polygon p) {
        super(id, p);
        // TODO Auto-generated constructor stub
    }

    public Entity init(){
        try {
            AddComponent( new ImageRenderComponent("PlayerRender", new Image("assets/flying_ninja.png")) );
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        AddComponent( new TopDownMovement("PlayerMovement") );
        setPosition(new Vector2f(250,SCREEN_HEIGHT/2));
        return this;
    }
    @Override
    public void updatePosition(float delta) {

        // constrain player within window
        position.x = Math.max(
                Math.min(position.x + velocity.x*delta, SCREEN_WIDTH - 200), 100);
        position.y = Math.min(Math.max(position.y + velocity.y*delta, GUI_HEIGHT),
                SCREEN_HEIGHT - PLAYER_HEIGHT * 2);
        setPosition(position);
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
        this.shape.setLocation(position);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, float delta) {
        for (Component component : components) {
            try {
                component.update(gc, sb, delta);
            } catch (SlickException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
