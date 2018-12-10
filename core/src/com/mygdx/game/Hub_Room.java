package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Hub_Room extends GameScreenUI {

    ActorBeta foreground;
    ActorBeta background;
    //ActorBeta sideBoundaryR;
    //ActorBeta sideBoundaryL;
    //ActorBeta sideBoundaryL2;
    //ActorBeta topBoundary;
    //ActorBeta bottomBoundary;

    ActorBeta iceWall;
    ActorBeta fireWall;
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
        iceWall = new ActorBeta(0,0,mainStage);
        iceWall.loadTexture("sprites/myBackgrounds/ice.png");
        iceWall.setSize(leftTransition.getWidth()*2, leftTransition.getHeight()*2);
        iceWall.setPosition(leftTransition.getX(), leftTransition.getY()-leftTransition.getHeight()/2);
        iceWall.setBoundaryRectangle();
        iceWall.setName("IceWall");

        fireWall = new ActorBeta(0,0,mainStage);
        fireWall.loadTexture("sprites/myBackgrounds/fire.png");
        fireWall.setSize(downTransition.getWidth()*2, downTransition.getHeight()*2);
        fireWall.setPosition(downTransition.getX()-downTransition.getWidth()/2, downTransition.getY());
        fireWall.setBoundaryRectangle();
        fireWall.setName("FireWall");

        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        //CREATE BLUE RANGER*/
        blueRanger =MyGame.blueRanger;

        //MyGame.blueRanger=blueRanger;
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
        blueRanger.preventOverlap(iceWall);
        blueRanger.preventOverlap(fireWall);
        if(blueRanger!=null&& downTransition!=null)
            if(blueRanger.overlaps(downTransition)){
                bgm.dispose();
                MyGame.buccaneerRoom = null;
                MyGame.buccaneerRoom = new Buccaneer_Room();
                MyGame.setActiveScreen(MyGame.buccaneerRoom);
                MyGame.hubRoom =null;
            }
        if(blueRanger!=null&& rightTransition!=null)
            if(blueRanger.overlaps(rightTransition)){
                bgm.dispose();
                MyGame.banditRoom = null;
                MyGame.banditRoom = new Bandit_Room();
                MyGame.setActiveScreen(MyGame.banditRoom);
            }
        if(blueRanger!=null&& leftTransition!=null)
            if(blueRanger.overlaps(leftTransition)){
                bgm.dispose();
                MyGame.brigandRoom = null;
                MyGame.brigandRoom = new Brigand_Room();
                MyGame.setActiveScreen(MyGame.brigandRoom);
            }
        if(blueRanger!=null&& upTransition!=null)
            if(blueRanger.overlaps(upTransition)){
                bgm.dispose();
                MyGame.startRoom = null;
                MyGame.startRoom = new Start_Room();
                MyGame.setActiveScreen(MyGame.startRoom);
            }
        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
    }




}
