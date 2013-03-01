package ninja.game;

import org.newdawn.slick.geom.Polygon;

public interface IConstants {
    

    static public int SCREEN_WIDTH = 1280;
    static public int SCREEN_HEIGHT = 800;
    static public int PLAYER_HEIGHT=64;
    static public int PLAYER_WIDTH=64;
    static public int BACKGROUND_HEIGHT=800;
    static public int BACKGROUND_WIDTH=1920;
    static public int BOARD_HEIGHT=96;
    static public int BOARD_WIDTH=32;
    static public int BIRD_HEIGHT=32;
    static public int BIRD_WIDTH=32;
    static public int STAR_HEIGHT=16;
    static public int STAR_WIDTH=16;
    static public int GUI_HEIGHT=105;
    static public int SKILL_HEIGHT=60;
    static public int SKILL_WIDTH=60;
    static public int BOTTOM_GUI_HEIGHT=64;
    
    static Polygon PLAYER_SHAPE = new Polygon(new float[]{
            0,0,
            PLAYER_WIDTH,0,
            PLAYER_WIDTH,PLAYER_HEIGHT,
            0,PLAYER_HEIGHT
    });
    
    static Polygon BACKGROUND_SHAPE= new Polygon(new float[]{
            0,0,
            BACKGROUND_WIDTH,0,
            BACKGROUND_WIDTH,BACKGROUND_HEIGHT,
            0,BACKGROUND_HEIGHT
    });
    
    static Polygon BOARD_SHAPE= new Polygon(new float[]{
            0,0,
            BOARD_WIDTH,0,
            BOARD_WIDTH,BOARD_HEIGHT,
            0,BOARD_HEIGHT
    });
    static Polygon STAR_SHAPE= new Polygon(new float[]{
            0,0,
            STAR_WIDTH,0,
            STAR_WIDTH,STAR_HEIGHT,
            0,STAR_HEIGHT
    });
    
    static Polygon SKILL_SHAPE= new Polygon(new float[]{
            0,0,
            SKILL_WIDTH,0,
            SKILL_WIDTH,SKILL_HEIGHT,
            0,SKILL_HEIGHT
    });
}
