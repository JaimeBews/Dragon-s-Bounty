package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Brigand_Room extends GameScreenUI {

    ActorBeta foreground;
    ActorBeta background;
    Brigand brigand1;
    Brigand brigand2;

    //ActorBeta sideBoundaryR;
    //ActorBeta sideBoundaryL;
    //ActorBeta sideBoundaryL2;
    //ActorBeta topBoundary;
    //ActorBeta bottomBoundary;
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
        bgm.setVolume(.2f);
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
        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        //CREATE BLUE RANGER*/

        brigand1 = new Brigand();
        brigand1.setPosition(WIDTH / 6, HEIGHT / 6);
        mainStage.addActor(brigand1);

        brigand2 = new Brigand();
        brigand2.setPosition(WIDTH / 6, HEIGHT / 1.5f);
        mainStage.addActor(brigand2);

        blueRanger = MyGame.blueRanger;
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();
    }

    @Override
    public void update(float dt) {
        blueRanger.preventOverlap(Top_Collider);
        blueRanger.preventOverlap(Bottom_Collider);
        blueRanger.preventOverlap(Right_Collider);
        blueRanger.preventOverlap(Left_Collider);
        if(blueRanger!=null&& rightTransition!=null)
            if(blueRanger.overlaps(rightTransition)){
                bgm.dispose();
                MyGame.hubRoom = null;
                MyGame.hubRoom = new Hub_Room();
                MyGame.setActiveScreen(MyGame.hubRoom);
            }
        if(blueRanger!=null&& leftTransition!=null)
            if(blueRanger.overlaps(leftTransition)){
                bgm.dispose();
                MyGame.itemRoom = null;
                MyGame.itemRoom = new Item_Room();
                MyGame.setActiveScreen(MyGame.itemRoom);
            }
        if(blueRanger!=null&& upTransition!=null)
            if(blueRanger.overlaps(upTransition)){
                bgm.dispose();
                MyGame.treasureRoom = null;
                MyGame.treasureRoom = new Treasure_Room();
                MyGame.setActiveScreen(MyGame.treasureRoom);
            }
        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
        brigand1.act(dt);
        brigand2.act(dt);

    }




}
