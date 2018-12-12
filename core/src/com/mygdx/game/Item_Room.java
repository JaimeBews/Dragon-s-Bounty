package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Item_Room extends GameScreenUI {


    ActorBeta foreground;
    ActorBeta background;
    //ActorBeta sideBoundaryR;
    //ActorBeta sideBoundaryL;
    //ActorBeta sideBoundaryL2;
    //ActorBeta topBoundary;
    //ActorBeta bottomBoundary;
    EvilPrincess evilPrincess;
    boolean ismovingleft = false;
    boolean ismovingright = false;
    boolean ismovingup = false;
    boolean ismovingdown = true;
    Vector2 DirToMove;
    float time;
    int i = 0;

    @Override
    public void initialize() {
        super.initialize();
        ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("Level Assets/LevelK.png");
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
        evilPrincess = new EvilPrincess();
        evilPrincess.setPosition(WIDTH / 15, HEIGHT / 3);
        mainStage.addActor(evilPrincess);

        blueRanger =MyGame.blueRanger;
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();
        DirToMove = new Vector2((blueRanger.getX() - evilPrincess.getX()), (blueRanger.getY() - evilPrincess.getY()));
    }

    @Override
    public void update(float dt) {
        evilPrincess.preventOverlap(Left_Collider);
        evilPrincess.preventOverlap(Right_Collider);
        evilPrincess.preventOverlap(Bottom_Collider);
        evilPrincess.preventOverlap(Top_Collider);
        if (evilPrincess.health>1)
        {
            if (i != 3 && dt != 0) {
                time = 0;
                evilPrincess.moveBy(DirToMove.x/20, DirToMove.y/20);
                if (evilPrincess.overlaps(Top_Collider)) {
                    DirToMove = new Vector2((blueRanger.getX() - evilPrincess.getX()), (blueRanger.getY() - evilPrincess.getY()));
                    i += 1;
                    ismovingdown = true;
                    ismovingup = false;
                } else if (evilPrincess.overlaps(Bottom_Collider)) {
                    DirToMove = new Vector2((blueRanger.getX() - evilPrincess.getX()), (blueRanger.getY() - evilPrincess.getY()));
                    ismovingdown = false;
                    ismovingup = true;
                    i += 1;
                } else if (evilPrincess.overlaps(Right_Collider)) {
                    DirToMove = new Vector2((blueRanger.getX() - evilPrincess.getX()), (blueRanger.getY() - evilPrincess.getY()));
                    ismovingright = false;
                    ismovingleft = true;
                    i += 1;
                } else if (evilPrincess.overlaps(Left_Collider)) {
                    DirToMove = new Vector2((blueRanger.getX() - evilPrincess.getX()), (blueRanger.getY() - evilPrincess.getY()));
                    ismovingleft = false;
                    ismovingright = true;
                    i += 1;
                }
            } else if (i == 3) {
                ismovingleft = false;
                ismovingright = false;
                ismovingup = false;
                ismovingdown = false;
                time += 1;
            }
            if (time > 100) {
                i = 0;
            }
            //bandit_boss.boundToWorld();
            DirToMove.nor();

            DirToMove.x *= 2.0f;
            DirToMove.y *= 2.0f;
            Gdx.app.log("speed", "" + DirToMove.y);
            //bandit_boss.moveBy(DirToMove.x,DirToMove.y);

            //bandit_boss.moveBy(DirToMove.x*0.01f,DirToMove.y*0.01f);
            if (ismovingdown) {

                evilPrincess.moveBy(DirToMove.x, DirToMove.y);
            } else if (ismovingup) {

                evilPrincess.moveBy(DirToMove.x, DirToMove.y);
            } else if (ismovingright) {

                evilPrincess.moveBy(DirToMove.x, DirToMove.y);
            } else if (ismovingleft) {

                evilPrincess.moveBy(DirToMove.x, DirToMove.y);
            }
        }

        if (evilPrincess.health == 1)
        {
            if (evilPrincess.getY()>HEIGHT) {
                //DirToMove = new Vector2((blueRanger.getX() - bandit_boss.getX()), (blueRanger.getY() - bandit_boss.getY()));
                i += 1;
                ismovingdown = true;
                ismovingup = false;
                ismovingleft = false;
                ismovingright = false;
            } else if (evilPrincess.getY()<0) {
                //DirToMove = new Vector2((blueRanger.getX() - bandit_boss.getX()), (blueRanger.getY() - bandit_boss.getY()));
                ismovingdown = false;
                ismovingup = true;
                ismovingleft = false;
                ismovingright = false;
                i += 1;
            }
            if (ismovingdown) {

                evilPrincess.moveBy( 0.00f, -1.0f);
            }
            else if (ismovingup) {

                evilPrincess.moveBy( 0.00f,  1.01f);
            }
        }
        blueRanger.preventOverlap(Top_Collider);
        blueRanger.preventOverlap(Right_Collider);
        blueRanger.preventOverlap(Top_Collider);
        blueRanger.preventOverlap(Bottom_Collider);
        if (blueRanger != null && rightTransition != null)
            if (blueRanger.overlaps(rightTransition)) {
                bgm.dispose();
                MyGame.buccaneerRoom = null;
                MyGame.buccaneerRoom = new Buccaneer_Room();
                MyGame.setActiveScreen(MyGame.buccaneerRoom);
            }

        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();

    }

}
