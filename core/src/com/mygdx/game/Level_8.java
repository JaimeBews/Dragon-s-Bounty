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

public class Level_8 extends GameScreenUI {

    Pirate pirate;
    ActorBeta foreground;
    ActorBeta background;

    @Override
    public void initialize() {
        super.initialize();
        ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("Level Assets/LevelH.png");
        foreground.setSize(WIDTH, HEIGHT);

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Chronos.mp3"));
        bgm.play();
        bgm.setLooping(true);
        uiStage.addActor(tableContainer);


        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        //CREATE BLUE RANGER*/
        pirate = new Pirate();

        pirate.setPosition(WIDTH / 2, HEIGHT / 3);
        blueRanger = new BlueRanger();
        mainStage.addActor(pirate);
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();
    }

    @Override
    public void update(float dt) {

        if(blueRanger!=null&& rightTransition!=null)
            if(blueRanger.overlaps(rightTransition)){
                bgm.dispose();
                MyGame.level_10 = null;
                MyGame.level_10 = new Level_10();
                MyGame.setActiveScreen(MyGame.level_10);
            }
        if(blueRanger!=null&& leftTransition!=null)
            if(blueRanger.overlaps(leftTransition)){
                bgm.dispose();
                MyGame.level_11 = null;
                MyGame.level_11 = new Level_11();
                MyGame.setActiveScreen(MyGame.level_11);
            }
        if(blueRanger!=null&& upTransition!=null)
            if(blueRanger.overlaps(upTransition)){
                bgm.dispose();
                MyGame.level_3 = null;
                MyGame.level_3 = new Level_3();
                MyGame.setActiveScreen(MyGame.level_3);
            }
        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
    }




}
