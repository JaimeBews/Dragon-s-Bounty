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

public class Level_4 extends GameScreenUI {
    PirateA pirateA;
    Princess princess;
    Bandit bandit;
    Brigand brigand;
    ActorBeta foreground;
    ActorBeta background;
    ActorBeta bottomBoundary;//ALJON LOOK HERE
    @Override
    public void initialize() {
        super.initialize();
        ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("Level Assets/LevelD.png");
        foreground.setSize(WIDTH, HEIGHT);

        bottomBoundary = new ActorBeta(0,0,mainStage);
        bottomBoundary.setSize(WIDTH/2,100);
        bottomBoundary.setBoundaryRectangle();

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Beat One.mp3"));
        bgm.play();
        bgm.setLooping(true);
        uiStage.addActor(tableContainer);


        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        //CREATE BLUE RANGER*/

        pirateA = new PirateA();
        pirateA.setPosition(WIDTH / 4, HEIGHT / 6);
        mainStage.addActor(pirateA);

        brigand = new Brigand();
        brigand.setPosition(WIDTH / 1.3f, HEIGHT / 6);
        mainStage.addActor(brigand);


        bandit = new Bandit();
        bandit.setPosition(WIDTH / 2, HEIGHT / 6);
        mainStage.addActor(bandit);
        princess = new Princess();
        princess.setPosition(WIDTH / 4, HEIGHT / 3);
        mainStage.addActor(princess);
        blueRanger = new BlueRanger();
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();

    }

    @Override
    public void update(float dt) {
        blueRanger.preventOverlap(bottomBoundary);
         if(blueRanger!=null&& downTransition!=null)
            if(blueRanger.overlaps(downTransition)){
                bgm.dispose();
                MyGame.level_10 = null;
                MyGame.level_10 = new Level_10();
                MyGame.setActiveScreen(MyGame.level_10);
                MyGame.level_4=null;
                }
        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
    }



}
