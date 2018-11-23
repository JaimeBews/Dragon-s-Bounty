package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Level_5 extends GameScreenUI {



    Cowboy cowboy;
    ActorBeta foreground;
    ActorBeta background;

    @Override
    public void initialize() {
        super.initialize();
        ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("Level Assets/LevelE.png");
        foreground.setSize(WIDTH, HEIGHT);

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Chronos.mp3"));
        bgm.play();
        bgm.setLooping(true);
        uiStage.addActor(tableContainer);


        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);


        //CREATE BLUE RANGER*/

        cowboy = new Cowboy();
        cowboy.setPosition(WIDTH / 4, HEIGHT / 3);
        mainStage.addActor(cowboy);
        blueRanger = new BlueRanger();
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();
    }

    @Override
    public void update(float dt) {
        if(blueRanger!=null&& downTransition!=null)
            if(blueRanger.overlaps(downTransition)){
                bgm.dispose();
                MyGame.level_1 = null;
                MyGame.level_1 = new Level_1();
                MyGame.setActiveScreen(MyGame.level_1);
            }
        if(blueRanger!=null&& rightTransition!=null)
            if(blueRanger.overlaps(rightTransition)){
                bgm.dispose();
                MyGame.level_7 = null;
                MyGame.level_7 = new Level_7();
                MyGame.setActiveScreen(MyGame.level_7);
            }
        if(blueRanger!=null&& leftTransition!=null)
            if(blueRanger.overlaps(leftTransition)){
                bgm.dispose();
                MyGame.level_10 = null;
                MyGame.level_10 = new Level_10();
                MyGame.setActiveScreen(MyGame.level_10);
            }
        if(blueRanger!=null&& upTransition!=null)
            if(blueRanger.overlaps(upTransition)){
                bgm.dispose();
                MyGame.level_6 = null;
                MyGame.level_6 = new Level_6();
                MyGame.setActiveScreen(MyGame.level_6);
            }
        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
    }



}
