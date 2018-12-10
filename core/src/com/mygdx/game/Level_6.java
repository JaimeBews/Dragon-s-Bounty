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

public class Level_6 extends GameScreenUI {


    ActorBeta foreground;
    ActorBeta background;
    //ActorBeta sideBoundaryR;
    //ActorBeta sideBoundaryL;
    //ActorBeta sideBoundaryL2;
    //ctorBeta topBoundary;
    //ActorBeta bottomBoundary;
    EvilPrincess evilPrincess;
    @Override
    public void initialize() {
        super.initialize();
        ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("Level Assets/LevelF.png");
        foreground.setSize(WIDTH, HEIGHT);

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Chronos.mp3"));
        bgm.play();
        bgm.setLooping(true);
        uiStage.addActor(tableContainer);
        /*
        sideBoundaryL = new ActorBeta(0,0,mainStage);
        sideBoundaryL.setSize(WIDTH/20.0f, 1000);
        // SideBoundaryL.setPosition(WIDTH / 1.3f, HEIGHT);
        sideBoundaryL.setBoundaryRectangle();

        sideBoundaryR = new ActorBeta(0,0,mainStage);
        sideBoundaryR.setSize(WIDTH/0.8f, 1000);
        sideBoundaryR.setPosition(WIDTH / 1.05f,0);
        sideBoundaryR.setBoundaryRectangle();

        bottomBoundary = new ActorBeta(0,0,mainStage);
        bottomBoundary.setSize(1800,100);
        bottomBoundary.setBoundaryRectangle();

        topBoundary = new ActorBeta(0,0,mainStage);
        topBoundary.setSize(1800, 100);
        topBoundary.setPosition(WIDTH/20f, HEIGHT/1.1f);
        topBoundary.setBoundaryRectangle();
        */
        evilPrincess = new EvilPrincess();
        evilPrincess.setPosition(WIDTH / 4, HEIGHT / 3);
        mainStage.addActor(evilPrincess);
        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        //CREATE BLUE RANGER*/
        blueRanger = MyGame.blueRanger;
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();
    }

    @Override
    public void update(float dt) {
        blueRanger.preventOverlap(Top_Collider);
        blueRanger.preventOverlap(Left_Collider);
        blueRanger.preventOverlap(Right_Collider);
        blueRanger.preventOverlap(Bottom_Collider);
        if(blueRanger!=null&& downTransition!=null)
            if(blueRanger.overlaps(downTransition)){
                bgm.dispose();
                MyGame.level_5 = null;
                MyGame.level_5 = new Level_5();
                MyGame.setActiveScreen(MyGame.level_5);
            }


        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
    }




}
