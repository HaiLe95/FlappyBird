package com.canonneers.game.states.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.canonneers.game.FlappyPig;
import com.canonneers.game.states.GameStateManager;
import com.canonneers.game.states.State;

public class MenuState extends State {

    private Texture background;
    private Texture playButton;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyPig.WIDTH / 2, FlappyPig.HEIGHT / 2);
        background = new Texture("graphics/backgrounds/bg.png");
        playButton = new Texture("graphics/buttons/playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    /**
     * To start rendering SpriteBatch always have to be ended before starting to draw(!)
     * @param spriteBatch container for everything that should be needed to be rendered
     */
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(playButton, camera.position.x - playButton.getWidth()/ 2, camera.position.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
