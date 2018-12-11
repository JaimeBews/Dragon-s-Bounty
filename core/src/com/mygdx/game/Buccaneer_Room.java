package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Buccaneer_Room extends GameScreenUI {




    ActorBeta foreground;
    ActorBeta background;
    PirateA pirateA1;
    PirateA pirateA2;
/*
    ActorBeta sideBoundaryR;
    ActorBeta sideBoundaryL;
    ActorBeta sideBoundaryL2;
    ActorBeta topBoundary;
    ActorBeta bottomBoundary;
*/
    @Override
    public void initialize() {
        super.initialize();
        ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("Level Assets/LevelB.png");
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
        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        //CREATE BLUE RANGER*/

        pirateA1 = new PirateA();
        pirateA1.setPosition(WIDTH / 6, HEIGHT / 1.5f);
        mainStage.addActor(pirateA1);

        pirateA2 = new PirateA();
        pirateA2.setPosition(WIDTH / 6, HEIGHT / 6);
        mainStage.addActor(pirateA2);

        blueRanger = MyGame.blueRanger;
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();
    }

    @Override
    public void update(float dt) {
        blueRanger.preventOverlap(Right_Collider);
        blueRanger.preventOverlap(Left_Collider);
        blueRanger.preventOverlap(Top_Collider);
        blueRanger.preventOverlap(Bottom_Collider);
        if(blueRanger!=null&& leftTransition!=null)
            if(blueRanger.overlaps(leftTransition)){
                bgm.dispose();
                MyGame.buccaneerBossRoom = null;
                MyGame.buccaneerBossRoom = new Buccaneer_Boss_Room();
                MyGame.setActiveScreen(MyGame.buccaneerBossRoom);
            }
        if(blueRanger!=null&& upTransition!=null)
            if(blueRanger.overlaps(upTransition)){
                bgm.dispose();
                MyGame.hubRoom = null;
                MyGame.hubRoom = new Hub_Room();
                MyGame.setActiveScreen(MyGame.hubRoom);
            }
        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
        pirateA1.act(dt);
        pirateA2.act(dt);
    }


}
