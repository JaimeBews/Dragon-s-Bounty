package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.ActorBeta;
import com.mygdx.game.BlueRanger;
import com.mygdx.game.ScreenBeta;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Level_10 extends GameScreenUI {

    ActorBeta foreground;
    ActorBeta background;
    ActorBeta sideBoundaryR;
    ActorBeta sideBoundaryL;
    ActorBeta sideBoundaryL2;
    ActorBeta topBoundary;
    ActorBeta bottomBoundary;

    ActorBeta iceWall;
    @Override
    public void initialize() {
        super.initialize();
        ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("Level Assets/LevelJ.png");
        foreground.setSize(WIDTH, HEIGHT);

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Bit Bit Loop.mp3"));
        bgm.play();
        bgm.setLooping(true);
        uiStage.addActor(tableContainer);

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

        iceWall = new ActorBeta(0,0,mainStage);
        iceWall.loadTexture("sprites/myBackgrounds/ice.png");
        iceWall.setSize(leftTransition.getWidth()*2, leftTransition.getHeight()*2);
        iceWall.setPosition(leftTransition.getX(), leftTransition.getY()-leftTransition.getHeight()/2);
        iceWall.setBoundaryRectangle();
        iceWall.setName("IceWall");
        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        //CREATE BLUE RANGER*/
        blueRanger =MyGame.blueRanger;

        MyGame.blueRanger=blueRanger;
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();
    }

    @Override
    public void update(float dt) {
        blueRanger.preventOverlap(sideBoundaryL);
        blueRanger.preventOverlap(sideBoundaryR);
        blueRanger.preventOverlap(bottomBoundary);
        blueRanger.preventOverlap(topBoundary);
        blueRanger.preventOverlap(iceWall);
        if(blueRanger!=null&& downTransition!=null)
            if(blueRanger.overlaps(downTransition)){
                bgm.dispose();
                MyGame.level_2 = null;
                MyGame.level_2 = new Level_2();
                MyGame.setActiveScreen(MyGame.level_2);
                MyGame.level_10=null;
            }
        if(blueRanger!=null&& rightTransition!=null)
            if(blueRanger.overlaps(rightTransition)){
                bgm.dispose();
                MyGame.level_5 = null;
                MyGame.level_5 = new Level_5();
                MyGame.setActiveScreen(MyGame.level_5);
            }
        if(blueRanger!=null&& leftTransition!=null)
            if(blueRanger.overlaps(leftTransition)){
                bgm.dispose();
                MyGame.level_8 = null;
                MyGame.level_8 = new Level_8();
                MyGame.setActiveScreen(MyGame.level_8);
            }
        if(blueRanger!=null&& upTransition!=null)
            if(blueRanger.overlaps(upTransition)){
                bgm.dispose();
                MyGame.level_4 = null;
                MyGame.level_4 = new Level_4();
                MyGame.setActiveScreen(MyGame.level_4);
            }
        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
    }




}
