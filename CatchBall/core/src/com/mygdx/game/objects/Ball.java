package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.GameManager;

public class Ball {

    private Sprite ballSprite;
    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();

    private final Vector2 gravity = new Vector2(0, -0.4f);

    private Circle ballCircle;
    private boolean isAlive;

    public void update() {
        velocity.add(gravity);
        position.add(velocity);
        ballSprite.setPosition(position.x, position.y);
        ballCircle.setPosition(position.x + (getBallSprite().getWidth() / 2), position.y + ballSprite.getHeight() / 2);
        checkCollisions();
    }

    public void render(SpriteBatch batch) {
        ballSprite.draw(batch);
    }

    public boolean checkCollisionsWithGround() {
        if (position.y <= 0) {
            GameManager.getGroundSound().play(0.3f);
            isAlive = false;
            return true;
        }
        return false;
    }

    public boolean checkCollisionsWithBasket() {
        if (Intersector.overlaps(ballCircle, GameManager.getBasket().getBasketRectangle())) {
            GameManager.getBasketSound().play(0.3f);
            GameManager.setScore(GameManager.getScore() + 1);
            if (GameManager.getScore() > GameManager.getHighScore()) {
                GameManager.setHighScore(GameManager.getScore());
            }

            isAlive = false;
            return true;
        }
        return false;
    }

    public void checkCollisions() {
        checkCollisionsWithBasket();
        checkCollisionsWithGround();
    }
    public boolean isAlive() {
        return isAlive;
    }

    public Sprite getBallSprite() {
        return ballSprite;
    }

    public void setBallSprite(Sprite ballSprite) {
        this.ballSprite = ballSprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Circle getBallCircle() {
        return ballCircle;
    }

    public void setBallCircle(Circle ballCircle) {
        this.ballCircle = ballCircle;
    }
}
