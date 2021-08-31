package com.mygdx.game.catchBall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.managers.GameManager;
import com.mygdx.game.managers.InputManager;

public class CatchTheBall implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private static MainGame game;

    public CatchTheBall(MainGame game) {

        CatchTheBall.game = game;

        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();

        camera = new OrthographicCamera(width, height);
        camera.setToOrtho(false);

        batch = new SpriteBatch();

        GameManager.initialize(width, height);
        Gdx.input.setInputProcessor(new InputManager(camera));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        GameManager.renderGame(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        GameManager.dispose();
        GameManager.getBackgroundMusic().stop();
    }

    public static MainGame getGame() {
        return game;
    }
}
