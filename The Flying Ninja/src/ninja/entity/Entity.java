package ninja.entity;

import java.util.ArrayList;

import ninja.compenent.Component;
import ninja.compenent.RenderComponent;
import ninja.game.IConstants;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Entity implements IConstants{

    String id;
    protected Polygon shape;
    protected Vector2f position;
    float scale;
    float rotation;
    protected Vector2f velocity;

    RenderComponent renderComponent = null;

    ArrayList<Component> components = null;

    public Entity(String id, Polygon p) {
        this.id = id;
        shape = p;
        components = new ArrayList<Component>();
        velocity = new Vector2f(0, 0);
        position = new Vector2f(SCREEN_WIDTH, SCREEN_HEIGHT);
        scale = 1;
        rotation = 0;
    }

    public abstract Entity init();
    
    public Polygon getShape() {
        return shape;
    }

    public void setShape(Polygon shape) {
        this.shape = shape;
    }

    public void AddComponent(Component component) {
        if (RenderComponent.class.isInstance(component))
            renderComponent = (RenderComponent) component;

        component.setOwnerEntity(this);
        components.add(component);
    }

    public Component getComponent(String id) {
        for (Component comp : components) {
            if (comp.getId().equalsIgnoreCase(id))
                return comp;
        }

        return null;
    }

    public int entityCollisionWith() throws SlickException {
        return -1;
    }

    public abstract void updatePosition(float delta); 

    public Vector2f getPosition() {
        return position;
    }

    public float getScale() {
        return scale;
    }

    public float getRotation() {
        return rotation;
    }

    public String getId() {
        return id;
    }

    public abstract void setPosition(Vector2f position);

    public void setX(float x) {
        this.position.x = x;
        shape.setX(x);
    }

    public void setY(float y) {
        this.position.y = y;
        shape.setY(y);
    }

    public void setRotation(float rotate) {
        rotation = rotate;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public abstract void update(GameContainer gc, StateBasedGame sb, float delta);
    

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }


    public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
        if (renderComponent != null)
            renderComponent.render(gc, sb, gr);
    }

}