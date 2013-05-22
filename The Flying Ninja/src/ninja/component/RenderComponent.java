package ninja.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class RenderComponent extends Component {

    public RenderComponent(String id) {
        this.id = id;
    }

    public abstract void render(GameContainer gc, StateBasedGame sb, Graphics gr);

	public void renderSkill(GameContainer gc, StateBasedGame sb, Graphics gr, boolean cd) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}