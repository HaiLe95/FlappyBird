package com.canonneers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    private static final int FLUCTUATION = 130;
    private static  final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 100;
    public static final  int  TUBE_WIDTH = 50;

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle boundsTop, boundsBot;
    private Random random;

    public Tube(float x) {
        topTube = new Texture("graphics/objects/toptube.png");
        bottomTube = new Texture("graphics/objects/bottomtube.png");
        random = new Random();
        /**
         * Here we are placing our tube inside the PlayState
         */
        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), topTube.getHeight());
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());

    }

    public void reposition(float x) {
        posTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }



    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }

    public Texture getTopTube() {
        return topTube;
    }
    public Texture getBottomTube() {
        return bottomTube;
    }
    public Vector2 getPosTopTube() {
        return posTopTube;
    }
    public Vector2 getPosBotTube() {
        return posBotTube;
    }
}
