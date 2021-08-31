package com.mygdx.game.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.catchBall.CatchTheBall;
import com.mygdx.game.catchBall.MenuScreen;

public class InputManager extends InputAdapter {

    private OrthographicCamera camera;
    private static Vector3 temp = new Vector3();

    public InputManager(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { //in case of playing game on touch screen

        temp.set(screenX, screenY, 0);
        camera.unproject(temp);

        float touchX = temp.x;
        float touchY = temp.y;

        GameManager.getBasket().handleTouch(touchX, touchY);
        handleBackButton(touchX, touchY);
        return false;
//        return super.touchUp(screenX, screenY, pointer, button);
    }

    public void handleBackButton(float touchX, float touchY) {
        if (touchX >= GameManager.getBackButtonSprite().getX() && touchX <=(GameManager.getBackButtonSprite().getX() + GameManager.getBackButtonSprite().getWidth())
        && touchY >= GameManager.getBackButtonSprite().getY() && touchY <= GameManager.getBackButtonSprite().getY() + GameManager.getBackButtonSprite().getHeight()) {
            CatchTheBall.getGame().setScreen(new MenuScreen(CatchTheBall.getGame())); //bring the menu screen to front
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            CatchTheBall.getGame().setScreen(new MenuScreen(CatchTheBall.getGame()));
        }
        return false;
    }
}
