package ninja.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class ImageRenderComponent extends RenderComponent {
	private Image image;

    public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	

    public ImageRenderComponent(String id, Image image) {
        super(id);
        this.image = image;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
        Vector2f pos = owner.getPosition();
        float scale = owner.getScale();

        image.draw(pos.x, pos.y, scale);
    }
    
    @Override
    public void renderSkill(GameContainer gc, StateBasedGame sb, Graphics gr, boolean cd) throws SlickException {
        Vector2f pos = owner.getPosition();
        float scale = owner.getScale();
        image.draw(pos.x, pos.y, scale);
        if(cd){
        	image.setAlpha(0.3f);
        } else {
        	image.setAlpha(1.0f);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, float delta) {
        image.rotate(owner.getRotation() - image.getRotation());
    }

}