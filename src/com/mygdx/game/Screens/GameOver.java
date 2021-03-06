package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.GameCore;
import com.mygdx.game.Screens.MissionsScreen.Mission1;
import com.mygdx.game.Screens.MissionsScreen.Mission2;
import com.mygdx.game.Screens.MissionsScreen.Mission3;
import com.mygdx.game.Screens.MissionsScreen.Mission4;
import com.mygdx.game.Screens.MissionsScreen.Mission5;
import com.mygdx.game.Singleton;

/**
 * Created by Wqawer on 2018-04-11.
 */

public class GameOver implements Screen{
    BitmapFont gameOver;
    GameCore game;
    Texture texture;
    public GameOver(GameCore game){
        gameOver= new BitmapFont();
        this.game=game;
        game.camera.position.set(0,0,0);
        game.shootMeneger.reset();
        texture= new Texture("gameOver.png");

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.position.set(0,0,0);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.batch.draw(texture,-400,-240);
        gameOver.draw(game.batch,"GAME OVER",0,0);
        game.batch.end();

        if(Gdx.input.isTouched()){
            switch (Singleton.getInstance().getLevel()){
                case 0:
                    game.setScreen(new Mission1(game));
                    break;
                case 1:
                    game.setScreen(new Mission2(game));
                    break;
                case 2:
                    game.setScreen(new Mission3(game));
                    break;
                case 3:
                    game.setScreen(new Mission4(game));
                    break;
                case 4:
                    game.setScreen(new Mission5(game));
            }}
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

    }
}


