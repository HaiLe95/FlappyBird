package com.canonneers.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;

    protected State(GameStateManager gsm) {
        this.gameStateManager = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    /**
     *
     */
    protected abstract void handleInput();

    /**
     * @param deltaTime is the difference between one frame rendered and next frame rendered
     */
    public abstract void update(float deltaTime);

    /**
     * @param spriteBatch container for everything that should be needed to be rendered
     */
    public abstract void render(SpriteBatch spriteBatch);

    public abstract void dispose();
}
