package com.canonneers.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Pig {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 120;
    private Vector3 position, velocity;
    private Texture pigTexture;
    private Sound flap;
    private Animation birdAnimation;
    private Rectangle bounds;

    public boolean colliding;


    public Pig(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        pigTexture = new Texture("graphics/characters/bird.png");
        Texture texture = new Texture("graphics/characters/animations/birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5F);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("audiofiles/effects/sfx_wing.ogg"));
        colliding = false;
    }

    /**
     * Changing velocity of pig by adding negative argument to it's horizontal position and scale it every
     * @param deltaTime
     */
    public void update(float deltaTime) {
        birdAnimation.update(deltaTime);
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(deltaTime);
        if(!colliding) {
            position.add(MOVEMENT * deltaTime,velocity.y, 0);
        }
        if(position.y < 0) {
            position.y = 0;
        }
        velocity.scl(1/deltaTime);
        bounds.setPosition(position.x, position.y);

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void jump() {
        velocity.y = 250;
        flap.play(0.5f);
    }

    public void dispose() {
        pigTexture.dispose();
        flap.dispose();
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getPigTexture() {
        return birdAnimation.getFrame();
    }
}
