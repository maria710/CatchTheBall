package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextManager {

    private static BitmapFont font;

    private static float width,height;

    public static void initialize(float width, float height) {
        font = new BitmapFont(Gdx.files.internal("data/fonts/ken.fnt"));
        TextManager.width = width;
        TextManager.height = height;

        font.setColor(Color.RED);
        TextManager.font.getData().setScale(width / 1400f);
    }

    public static void displayMessage(SpriteBatch batch) {
        float fontWidth = 50;
        font.draw(batch, "Score: " + GameManager.getScore(), width - fontWidth - (width / 15f), height * 0.95f);
        font.draw(batch, "High Score: " + GameManager.getHighScore(), width / 40f, height * 0.95f);
    }


}
