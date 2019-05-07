package com.canonneers.game.states.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.canonneers.game.FlappyPig;
import com.canonneers.game.sprites.Pig;
import com.canonneers.game.sprites.Tube;
import com.canonneers.game.states.GameStateManager;
import com.canonneers.game.states.State;

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;
    private Pig pig;
    private Texture bg, ground, gameOverImg;
    private boolean gameOver;
    private Vector2 groundPos1, groundPos2;
    private Array<Tube> tubes;
    private int score;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        pig = new Pig(50, 300);
        camera.setToOrtho(false, FlappyPig.WIDTH / 2, FlappyPig.HEIGHT / 2);
        bg = new Texture("graphics/backgrounds/bg.png");
        ground = new Texture("graphics/objects/ground.png");
        gameOverImg = new Texture("graphics/info/gameover.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        gameOver = false;
        score = 0;

        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            if (gameOver) {
                gameStateManager.set(new PlayState(gameStateManager));
            } else {
                pig.jump();
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        updateGround();
        pig.update(deltaTime);
        camera.position.x = pig.getPosition().x + 75;
        for(int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(pig.getBounds())) {
                pig.colliding = true;
                gameOver = true;
            }
        }
        if(pig.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            pig.getPosition().y = 0;
            pig.colliding = true;
            gameOver = true;
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.draw(pig.getPigTexture(), pig.getPosition().x, pig.getPosition().y);
        for(Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        spriteBatch.draw(ground, groundPos1.x, groundPos1.y);
        spriteBatch.draw(ground, groundPos2.x, groundPos2.y);
        if(gameOver) {
            spriteBatch.draw(gameOverImg, camera.position.x - gameOverImg.getWidth() / 2, camera.position.y);
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        pig.dispose();
        ground.dispose();
        for(Tube tube: tubes) {
            tube.dispose();
        }
    }

    private void updateGround() {
        if(camera.position.x - (camera.viewportWidth/2) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(camera.position.x - (camera.viewportWidth/2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
