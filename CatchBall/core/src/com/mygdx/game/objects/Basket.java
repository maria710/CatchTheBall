package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Basket {

    private Sprite basketSprite;
    private Rectangle basketRectangle = new Rectangle();

    public void render(SpriteBatch batch) {
        basketSprite.draw(batch);
    }

    public void setPosition(float x, float y) {
        basketSprite.setPosition(x, y);
        basketRectangle.setPosition(x, y);
    }

    public void handleTouch(float x, float y) {
        if (x - (basketSprite.getWidth() / 2) > 0.0f) {
            setPosition(x - (basketSprite.getWidth() / 2), 0);
        } else if (x < basketSprite.getWidth() / 2) {
            setPosition(0, 0);
        }
    }

    public void setBasketSprite(Sprite basketSprite) {
        this.basketSprite = basketSprite;
    }

    public Sprite getBasketSprite() {
        return basketSprite;
    }

    public Rectangle getBasketRectangle() {
        return basketRectangle;
    }
}
