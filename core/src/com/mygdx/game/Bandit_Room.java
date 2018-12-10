package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Bandit_Room extends GameScreenUI {



    Cowboy cowboy;
    ActorBeta foreground;
    ActorBeta background;
    //ActorBeta sideBoundaryR;
    //ActorBeta sideBoundaryL;
    //ActorBeta sideBoundaryL2;
    //ActorBeta topBoundary;
    //ctorBeta bottomBoundary;

    ActorBeta fireWall;
    ActorBeta iceWall;
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
        fireWall = new ActorBeta(0,0,mainStage);
        fireWall.loadTexture("sprites/myBackgrounds/fire.png");
        fireWall.setSize(downTransition.getWidth()*2, downTransition.getHeight()*2);
        fireWall.setPosition(downTransition.getX()-downTransition.getWidth()/2, downTransition.getY());
        fireWall.setBoundaryRectangle();
        fireWall.setName("FireWall");

        iceWall = new ActorBeta(0,0,mainStage);
        iceWall.loadTexture("sprites/myBackgrounds/ice.png");
        iceWall.setSize(upTransition.getWidth()*2, upTransition.getHeight()*2);
        iceWall.setPosition(upTransition.getX()-upTransition.getWidth()/2, upTransition.getY()-upTransition.getHeight());
        iceWall.setBoundaryRectangle();
        iceWall.setName("IceWall");

        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);


        //CREATE BLUE RANGER*/

        cowboy = new Cowboy();
        cowboy.setPosition(WIDTH / 4, HEIGHT / 3);
        mainStage.addActor(cowboy);
        blueRanger = MyGame.blueRanger;
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();
    }

    @Override
    public void update(float dt) {
        blueRanger.preventOverlap(Top_Collider);
        blueRanger.preventOverlap(Bottom_Collider);
        blueRanger.preventOverlap(Left_Collider);
        blueRanger.preventOverlap(Right_Collider);
        blueRanger.preventOverlap(fireWall);
        blueRanger.preventOverlap(iceWall);
        if(blueRanger!=null&& downTransition!=null)
            if(blueRanger.overlaps(downTransition)){
                bgm.dispose();
                MyGame.banditRoom2 = null;
                MyGame.banditRoom2 = new Bandit_Room_2();
                MyGame.setActiveScreen(MyGame.banditRoom2);
            }
        if(blueRanger!=null&& rightTransition!=null)
            if(blueRanger.overlaps(rightTransition)){
                bgm.dispose();
                MyGame.banditBossRoom = null;
                MyGame.banditBossRoom = new Bandit_Boss_Room();
                MyGame.setActiveScreen(MyGame.banditBossRoom);
            }
        if(blueRanger!=null&& leftTransition!=null)
            if(blueRanger.overlaps(leftTransition)){
                bgm.dispose();
                MyGame.hubRoom = null;
                MyGame.hubRoom = new Hub_Room();
                MyGame.setActiveScreen(MyGame.hubRoom);
            }
        if(blueRanger!=null&& upTransition!=null)
            if(blueRanger.overlaps(upTransition)){
                bgm.dispose();
                MyGame.brigandBossRoom = null;
                MyGame.brigandBossRoom = new Brigand_Boss_Room();
                MyGame.setActiveScreen(MyGame.brigandBossRoom);
            }
        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
    }



}
