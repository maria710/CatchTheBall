package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.objects.Ball;
import com.mygdx.game.objects.Basket;



public class GameManager {

    private static Basket basket;
    private static Texture basketTexture;
    private static Sprite backgroundSprite;
    private static Texture backgroundTexture;

    private static Sprite backButtonSprite;
    private static Texture backButtonTexture;

    private static Sound groundSound;
    private static Sound basketSound;

    private static Music backgroundMusic;

    private static Ball ball;
    private static Texture ballTexture;

    private static final float BALL_RESIZE_FACTOR = 2500f;
    private static final float BASKET_RESIZE_FACTOR = 3000F;
    private static final float BACK_BIN_RESIZE_FACTOR = 1500f;

    private static Array<Ball> balls = new Array<>();

    private static int score;
    private static int highScore;
    private static Preferences preferences;


    public static void initialize(float width, float height) {

        basket = new Basket();
        basketTexture = new Texture(Gdx.files.internal("data/basket.png"));
        basket.setBasketSprite(new Sprite(basketTexture));
        basket.getBasketSprite().setSize(basket.getBasketSprite().getWidth() * (width / BASKET_RESIZE_FACTOR),
                basket.getBasketSprite().getHeight() * (width / BASKET_RESIZE_FACTOR));
        basket.setPosition(0, 0);
        basket.getBasketRectangle().setSize(basket.getBasketSprite().getWidth(), basket.getBasketSprite().getHeight());

        backgroundTexture = new Texture(Gdx.files.internal("data/background.jpg"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(width, height);

        ballTexture = new Texture(Gdx.files.internal("data/ball.png"));

        SpawnManager.initialize(width, height, ballTexture);
        score = 0;
        TextManager.initialize(width, height);

        preferences = Gdx.app.getPreferences("My Preferences"); // in order to get previous result from game
        highScore = preferences.getInteger("highScore");

        backButtonTexture = new Texture(Gdx.files.internal("data/backbutton.png"));
        backButtonSprite = new Sprite(backButtonTexture);

        backButtonSprite.setSize(backButtonSprite.getWidth() * (width / BACK_BIN_RESIZE_FACTOR),
                backButtonSprite.getHeight() * (width / BACK_BIN_RESIZE_FACTOR));
        backButtonSprite.setPosition(width / 2 - backButtonSprite.getWidth() / 2, height * 0.935f);

        Gdx.input.setCatchBackKey(true);

        groundSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/groundHit.wav"));
        basketSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/basketHit.wav"));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("data/sounds/backmusic.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    public static void renderGame(SpriteBatch batch) {
        backgroundSprite.draw(batch);
        basket.render(batch);

        SpawnManager.run(balls);
        for (Ball ball1 : balls) {
            if (ball1.isAlive()) {
                ball1.update();
                ball1.render(batch);
            }
        }

        SpawnManager.cleanUp(balls);
        TextManager.displayMessage(batch);

        backButtonSprite.draw(batch);

        preferences.putInteger("highScore", score);
        preferences.flush();
    }

    public static void dispose() {

        backgroundTexture.dispose();
        basketTexture.dispose();
        ballTexture.dispose();
        backButtonTexture.dispose();

        groundSound.dispose();
        basketSound.dispose();

        backgroundMusic.dispose();

        balls.clear();
    }

    public static Basket getBasket() {
        return basket;
    }

    public static int getScore() {
        return score;
    }

    public static int getHighScore() {
        return highScore;
    }

    public static void setScore(int score) {
        GameManager.score = score;
    }

    public static void setHighScore(int highScore) {
        GameManager.highScore = highScore;
    }

    public static Sprite getBackButtonSprite() {
        return backButtonSprite;
    }

    public static Sound getGroundSound() {
        return groundSound;
    }

    public static Sound getBasketSound() {
        return basketSound;
    }

    public static Music getBackgroundMusic() {
        return backgroundMusic;
    }
}
