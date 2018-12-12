package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Buccaneer_Boss_Room extends GameScreenUI {

    ActorBeta foreground;
    ActorBeta background;

    boolean ismovingleft=false;
    boolean ismovingright = false;
    boolean ismovingup = false;
    boolean ismovingdown = false;
    Vector2 DirToMove;
    float time;
    int i = 0;

    BulletL bullet;
    Cowboy_Boss cowboy_boss;
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
        foreground.loadTexture("Level Assets/LevelI.png");
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
        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        //CREATE BLUE RANGER*/

        cowboy_boss = new Cowboy_Boss();
        cowboy_boss.setPosition(WIDTH / 4, HEIGHT / 3);
        mainStage.addActor(cowboy_boss);



        blueRanger = MyGame.blueRanger;
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();
        DirToMove = new Vector2((blueRanger.getX()-cowboy_boss.getX()),(blueRanger.getY()-cowboy_boss.getY()));
    }

    @Override
    public void update(float dt) {

        cowboy_boss.preventOverlap(Left_Collider);
        cowboy_boss.preventOverlap(Right_Collider);
        cowboy_boss.preventOverlap(Bottom_Collider);
        cowboy_boss.preventOverlap(Top_Collider);
        if(i!=3 && dt!=0) {
            time=0;
            cowboy_boss.moveBy(DirToMove.x , DirToMove.y);
            if (cowboy_boss.overlaps(Top_Collider)) {
                DirToMove = new Vector2((blueRanger.getX() - cowboy_boss.getX()), (blueRanger.getY() - cowboy_boss.getY()));
                i += 1;
                ismovingdown = true;
                ismovingup = false;
            } else if (cowboy_boss.overlaps(Bottom_Collider)) {
                DirToMove = new Vector2((blueRanger.getX() - cowboy_boss.getX()), (blueRanger.getY() - cowboy_boss.getY()));
                ismovingdown = false;
                ismovingup = true;
                i += 1;
            } else if (cowboy_boss.overlaps(Right_Collider)) {
                DirToMove = new Vector2((blueRanger.getX() - cowboy_boss.getX()), (blueRanger.getY() - cowboy_boss.getY()));
                ismovingright = false;
                ismovingleft = true;
                i += 1;
            } else if (cowboy_boss.overlaps(Left_Collider)) {
                DirToMove = new Vector2((blueRanger.getX() - cowboy_boss.getX()), (blueRanger.getY() - cowboy_boss.getY()));
                ismovingleft = false;
                ismovingright = true;
                i += 1;
            }
        }else if(i==3)
        {
            ismovingleft = false;
            ismovingright = false;
            ismovingup = false;
            ismovingdown = false;
            time +=1;
        }
        if(time>100)
        {
            i=0;
        }
        //bandit_boss.boundToWorld();
        DirToMove.nor();

        DirToMove.x*=2.0f;
        DirToMove.y*=2.0f;
        Gdx.app.log("speed",""+DirToMove.y);
        //bandit_boss.moveBy(DirToMove.x,DirToMove.y);

        //bandit_boss.moveBy(DirToMove.x*0.01f,DirToMove.y*0.01f);
        if (ismovingdown) {

            cowboy_boss.moveBy(DirToMove.x , DirToMove.y );
        }
        else if (ismovingup) {

            cowboy_boss.moveBy(DirToMove.x , DirToMove.y );
        }
        else if (ismovingright) {

            cowboy_boss.moveBy(DirToMove.x , DirToMove.y );
        }else if (ismovingleft) {

            cowboy_boss.moveBy(DirToMove.x , DirToMove.y );
        }



        blueRanger.preventOverlap(Top_Collider);
        blueRanger.preventOverlap(Right_Collider);
        blueRanger.preventOverlap(Top_Collider);
        blueRanger.preventOverlap(Bottom_Collider);
        if(blueRanger!=null&& rightTransition!=null)
            if(blueRanger.overlaps(rightTransition)){
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
