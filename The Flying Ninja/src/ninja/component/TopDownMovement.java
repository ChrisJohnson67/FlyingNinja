package ninja.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class TopDownMovement extends Component {
    float speed;

    public TopDownMovement(String id) {
        this.id = id;
        speed = 0.5f;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, float delta)
            throws SlickException {

        if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            owner.setVelocity(new Vector2f(-speed*delta, owner.getVelocity().y));
        }

        if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            owner.setVelocity(new Vector2f(speed*delta, owner.getVelocity().y));
        }

        if (gc.getInput().isKeyDown(Input.KEY_UP)) {
            owner.setVelocity(new Vector2f(owner.getVelocity().x, -speed*delta));
        }
        if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            owner.setVelocity(new Vector2f(owner.getVelocity().x, speed*delta));
        }

        owner.updatePosition(delta);
        owner.setVelocity(new Vector2f(0,0));
        

    }

}