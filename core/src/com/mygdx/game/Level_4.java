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
    ActorBeta bottomBoundaryL;
    ActorBeta sideBoundaryL;
    ActorBeta sideBoundaryR;
    ActorBeta topBoundary;
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

        bottomBoundaryL = new ActorBeta(0,0,mainStage);
        bottomBoundaryL.setSize(WIDTH/0.9f, 100);
        bottomBoundaryL.setPosition(WIDTH / 1.8f, HEIGHT / 150);
        bottomBoundaryL.setBoundaryRectangle();

        sideBoundaryL = new ActorBeta(0,0,mainStage);
        sideBoundaryL.setSize(WIDTH/20.0f, 1000);
       // SideBoundaryL.setPosition(WIDTH / 1.3f, HEIGHT);
        sideBoundaryL.setBoundaryRectangle();

        sideBoundaryR = new ActorBeta(0,0,mainStage);
        sideBoundaryR.setSize(WIDTH/0.8f, 1000);
        sideBoundaryR.setPosition(WIDTH / 1.05f,0);
        sideBoundaryR.setBoundaryRectangle();

        topBoundary = new ActorBeta(0,0,mainStage);
        topBoundary.setSize(1800, 100);
         topBoundary.setPosition(WIDTH/20f, HEIGHT/1.1f);
        topBoundary.setBoundaryRectangle();

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Beat One.mp3"));
        bgm.play();
        bgm.setLooping(true);
        uiStage.addActor(tableContainer);


        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        //CREATE BLUE RANGER*/

        princess = new Princess();
        princess.setPosition(WIDTH / 4, HEIGHT / 3);
        mainStage.addActor(princess);
        blueRanger = new BlueRanger();
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();

        pirateA = new PirateA();
        pirateA.setPosition(WIDTH / 4, HEIGHT / 6);
        pirateA.getplayer(blueRanger);
        mainStage.addActor(pirateA);

        brigand = new Brigand();
        brigand.setPosition(WIDTH / 1.3f, HEIGHT / 6);
        brigand.getplayer(blueRanger);
        mainStage.addActor(brigand);


        bandit = new Bandit();
        bandit.setPosition(WIDTH / 2, HEIGHT / 6);
        bandit.getplayer(blueRanger);
        mainStage.addActor(bandit);

    }

    @Override
    public void update(float dt) {

        blueRanger.preventOverlap(bottomBoundary);
        blueRanger.preventOverlap(bottomBoundaryL);
        blueRanger.preventOverlap(sideBoundaryL);
        blueRanger.preventOverlap(sideBoundaryR);
        blueRanger.preventOverlap(topBoundary);

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
        bandit.act(dt);
        pirateA.act(dt);
        brigand.act(dt);
        blueRanger.boundToWorld();
    }



}
