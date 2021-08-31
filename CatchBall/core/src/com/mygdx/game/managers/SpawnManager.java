package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.objects.Ball;

import java.util.Random;

public class SpawnManager {

    private static float delayTime = 0.8f;
    private static float delayCounter = 0.0f;

    private static float width, height;

    private static Texture ballTexture;
    private static final float BALL_RESIZE_FACTOR = 2500f;

    private static IntArray removeIndices = new IntArray();
    private static Random random = new Random();


    public static void initialize(float width, float height, Texture ballTexture) {
        SpawnManager.width = width;
        SpawnManager.height = height;
        SpawnManager.ballTexture = ballTexture;
        ballPool.clear();
        delayCounter = 0.0f;
    }

    private static final Pool<Ball> ballPool = new Pool<Ball>() {
        @Override
        protected Ball newObject() {
            Ball ball = new Ball();

            ball.setBallSprite(new Sprite(ballTexture));
            return ball;
        }
    };

    public static Ball resetBall(Ball ball) {
        ball.getBallSprite().setSize(ball.getBallSprite().getTexture().getWidth() * (width / BALL_RESIZE_FACTOR),
                ball.getBallSprite().getTexture().getHeight() * (width / BALL_RESIZE_FACTOR));
        ball.getPosition().set(random.nextInt((int)(width - ball.getBallSprite().getWidth())), height - ball.getBallSprite().getHeight());
        ball.getVelocity().set(0, 0);
        ball.setAlive(true);

        Vector2 center = new Vector2();

        center.x = ball.getPosition().x + (ball.getBallSprite().getWidth() / 2);
        center.y = ball.getPosition().y + (ball.getBallSprite().getHeight() / 2);

        ball.setBallCircle(new Circle(center, (ball.getBallSprite().getHeight() / 2)));

        return ball;
    }

    public static void cleanUp(Array<Ball> balls) {
        removeIndices.clear();

        for (int i = balls.size - 1; i >= 0; i--) {
            if (!balls.get(i).isAlive()) {
                removeIndices.add(i);
            }
        }

        for (int i = 0; i < removeIndices.size; i++) {
            Ball ball = balls.removeIndex(i);
            ballPool.free(ball);
        }
    }

    public static void run(Array<Ball> balls) {

        if (delayCounter >= delayTime) {
            Ball ball = ballPool.obtain();
            resetBall(ball);
            balls.add(ball);
            delayCounter = 0.0f;
        } else {
            delayCounter += Gdx.graphics.getDeltaTime();
        }
    }
}
