package com.mygdx.game.Screens.MissionsScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemy.Bunker;
import com.mygdx.game.FlipButton;
import com.mygdx.game.GameCore;
import com.mygdx.game.Phisic;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.PlayerRocket;
import com.mygdx.game.Screens.GameOver;
import com.mygdx.game.Screens.Win;

/**
 * Created by Wqawer on 2018-04-11.
 */

public class Mission2 implements Screen {
    int kodKoloru;
    Color color;
    Texture mapa;
    GameCore game;
    Player player;
    Phisic phisic;
    Pixmap pixmap;
    FlipButton flipButton;
    PlayerRocket playerRocket;
    Bunker bunker;
    public Mission2(GameCore game){

        this.game=game;
        player=new Player(50,50);
        pixmap=new Pixmap(Gdx.files.internal("pixmap1.png"));
        mapa=new Texture("map1.png");
        phisic=new Phisic(player);
        flipButton= new FlipButton(player);
        playerRocket=new PlayerRocket(player.positionX,player.positionX,0);
        bunker=new Bunker(game,200,50);

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        pixmanager();
        phisic.update();
        if(Gdx.input.isTouched(1)){flipButton.onClick();}




        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.position.set(player.positionX,player.positionY,0);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();

        game.batch.draw(mapa,0,0);
        player.render(game);
        bunker.render();
        if(Gdx.input.isTouched(1)){
            player.playerRocketList.add(new PlayerRocket(player.positionX,player.positionY,player.getRotation()));}
        if(player.playerRocketList.size()>100)
        {player.playerRocketList.remove(1);}
        for(int i=0;i<player.playerRocketList.size();i++){
            player.playerRocketList.get(i).render(game);
            if(bunker.hitbox.overlaps(player.playerRocketList.get(i).hitbox.getX(),player.playerRocketList.get(i).hitbox.getY())){bunker.hp=0;}
        }
        game.batch.end();
        game.batch.setProjectionMatrix(game.cameraUserLayer.combined);
        game.batch.begin();
        //     flipButton.render(game.batch);
        game.batch.end();
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
    public void pixmanager(){
        kodKoloru=pixmap.getPixel((int)player.positionX,(int)player.positionY);
        color = new Color(kodKoloru);
        int blue=(int)(color.b*255);
        int green=(int)(color.g*255);
        int red=(int)(color.r*255);
        if(winManager(red)){game.setScreen(new Win(game));}
        if(blue==255){
            phisic.onGround=true;
            if(crash()){
                game.setScreen(new GameOver(game));
            }
        }
        else{phisic.onGround=false;}
    }
    //transport medyczny
    public boolean winManager(int red){


        if(red==255){return true;}
        return false;
    }
    public boolean crash(){

        return phisic.crash();


    }
}
