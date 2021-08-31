package com.mygdx.game.catchBall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Texture startButtonTexture;
    private Texture exitButtonTexture;
    private Texture backGroundTexture;

    private Sprite startButtonSprite;
    private Sprite exitButtonSprite;
    private Sprite backGroundSprite;

    private static final float BUTTON_RESIZE_FACTOR = 800f;
    private static final float START_VERT_POSITION_FACTOR = 2.7f;
    private static final float EXIT_VERT_POSITION_FACTOR = 4.2f;

    private MainGame game;
    private Vector3 temp = new Vector3();

    public MenuScreen(MainGame game) {
        this.game = game;
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        camera.setToOrtho(false); //center the camera
        batch = new SpriteBatch();

        startButtonTexture = new Texture(Gdx.files.internal("data/start_button.png"));
        exitButtonTexture = new Texture(Gdx.files.internal("data/exit_button.png"));
        backGroundTexture = new Texture(Gdx.files.internal("data/menubackground.jpg"));

        startButtonSprite = new Sprite(startButtonTexture);
        exitButtonSprite = new Sprite(exitButtonTexture);
        backGroundSprite = new Sprite(backGroundTexture);

        startButtonSprite.setSize(startButtonSprite.getWidth() * (width / BUTTON_RESIZE_FACTOR), startButtonSprite.getHeight() * (width / BUTTON_RESIZE_FACTOR));
        exitButtonSprite.setSize(exitButtonSprite.getWidth() * (width / BUTTON_RESIZE_FACTOR), exitButtonSprite.getHeight() * (width / BUTTON_RESIZE_FACTOR));
        backGroundSprite.setSize(width, height);

        startButtonSprite.setPosition((width / 2f - startButtonSprite.getWidth() / 2), width / START_VERT_POSITION_FACTOR);
        exitButtonSprite.setPosition((width / 2f - exitButtonSprite.getWidth() / 2), width / EXIT_VERT_POSITION_FACTOR);

        backGroundSprite.setAlpha(0.2f); //transparency to look cool

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        backGroundSprite.draw(batch);
        startButtonSprite.draw(batch);
        exitButtonSprite.draw(batch);
        batch.end();

        handleTouch();
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

    }

    @Override
    public void dispose() {
        startButtonTexture.dispose();
        exitButtonTexture.dispose();
        backGroundTexture.dispose();
        batch.dispose();
    }

    void handleTouch() {
        if (Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(temp);

            float touchX = temp.x;
            float touchY = temp.y;

            if (touchX >= startButtonSprite.getX() && touchX <= startButtonSprite.getX() + startButtonSprite.getWidth()
            && touchY >= startButtonSprite.getY() && touchY <= startButtonSprite.getY() + startButtonSprite.getHeight()) {
                game.setScreen(new CatchTheBall(game));
            } else if (touchX >= exitButtonSprite.getX() && touchX <= exitButtonSprite.getX() + exitButtonSprite.getWidth()
                    && touchY >= exitButtonSprite.getY() && touchY <= exitButtonSprite.getY() + exitButtonSprite.getHeight()) {
                Gdx.app.exit();
            }
        }
    }
}
