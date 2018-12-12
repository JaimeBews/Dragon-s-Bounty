package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Bandit_Boss_Room extends GameScreenUI {
    boolean ismovingleft=false;
    boolean ismovingright = false;
    boolean ismovingup = false;
    boolean ismovingdown = true;



    Vector2 DirToMove;
    float time;
    int i = 0;

    ActorBeta background;
    ActorBeta foreground;
/*
    ActorBeta Right_Collider;
    ActorBeta Left_Collider;
    ActorBeta Top_Collider;
    ActorBeta Bottom_Collider;
*/
    Bandit_Boss bandit_boss;
    @Override
    public void initialize() {
        super.initialize();
        //ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("Level Assets/LevelG.png");
        foreground.setSize(WIDTH, HEIGHT);

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Bit Bit Loop.mp3"));
        bgm.play();
        bgm.setLooping(true);
        bgm.setVolume(.2f);
        //uiStage.addActor(tableContainer);
        ismovingdown = true;
/*
        Left_Collider = new ActorBeta();
        Left_Collider.setSize(10, HEIGHT/1.0f);
        Left_Collider.setPosition(0, 0);
        Left_Collider.setBoundaryRectangle();
        mainStage.addActor(Left_Collider);

        Right_Collider = new ActorBeta();
        Right_Collider.setSize(10, HEIGHT/1.0f);
        Right_Collider.setPosition(WIDTH,200);
        Right_Collider.setBoundaryRectangle();
        mainStage.addActor(Right_Collider);

        Bottom_Collider = new ActorBeta();
        Bottom_Collider.setSize(WIDTH/1.0f,15);
        Bottom_Collider.setPosition(0,-15);
        Bottom_Collider.setBoundaryRectangle();
        mainStage.addActor(Bottom_Collider);

        Top_Collider = new ActorBeta();
        Top_Collider.setSize(WIDTH/1.0f, 15);
        Top_Collider.setPosition(0, HEIGHT/1.0f);
        Top_Collider.setBoundaryRectangle();
        mainStage.addActor(Top_Collider);

*/
        bandit_boss = new Bandit_Boss();
        bandit_boss.setPosition(WIDTH-100,HEIGHT/2);
        bandit_boss.setSize(WIDTH/40,HEIGHT/20);
        bandit_boss.setBoundaryRectangle();

        mainStage.addActor(bandit_boss);

        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        //CREATE BLUE RANGER*/
        blueRanger = MyGame.blueRanger;
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        blueRanger.setSize(WIDTH/20,HEIGHT/10);
        mainStage.addActor(blueRanger);
        DirToMove = new Vector2((blueRanger.getX()- bandit_boss.getX()),(blueRanger.getY()- bandit_boss.getY()));
        //bandit_boss.moveBy(DirToMove.x*0.01f,DirToMove.y*0.01f);
        loadUI();
    }

    @Override
    public void update(float dt) {

        //int time = Time;

        blueRanger.preventOverlap(Left_Collider);
        blueRanger.preventOverlap(Right_Collider);
        blueRanger.preventOverlap(Bottom_Collider);
        blueRanger.preventOverlap(Top_Collider);
        //if(i!=3 && dt!=0) {
          //  time=0;
            //bandit_boss.moveBy( 0.00f,  1.00f);
            if (bandit_boss.getY()>HEIGHT) {
                //DirToMove = new Vector2((blueRanger.getX() - bandit_boss.getX()), (blueRanger.getY() - bandit_boss.getY()));
                i += 1;
                ismovingdown = true;
                ismovingup = false;
            } else if (bandit_boss.getY()<0+bandit_boss.getHeight()/2) {
                //DirToMove = new Vector2((blueRanger.getX() - bandit_boss.getX()), (blueRanger.getY() - bandit_boss.getY()));
                ismovingdown = false;
                ismovingup = true;
                i += 1;
            } /*else if (bandit_boss.overlaps(Right_Collider)) {
                DirToMove = new Vector2((blueRanger.getX() - bandit_boss.getX()), (blueRanger.getY() - bandit_boss.getY()));
                ismovingright = false;
                ismovingleft = true;
                i += 1;
            } else if (bandit_boss.overlaps(Left_Collider)) {
                DirToMove = new Vector2((blueRanger.getX() - bandit_boss.getX()), (blueRanger.getY() - bandit_boss.getY()));
                ismovingleft = false;
                ismovingright = true;
                i += 1;
            }*/
        //}/*else if(i==3)
        /*{
            ismovingleft = false;
            ismovingright = false;
            ismovingup = false;
            ismovingdown = false;
            time +=1;
        }
        if(time>100)
        {
            i=0;
        }*/
        //bandit_boss.boundToWorld();
        //DirToMove.nor();
        //Vector2 DirToMove = new Vector2((blueRanger.getX()-bandit_boss.getX()),(blueRanger.getY()-bandit_boss.getY()));
        //bandit_boss.moveBy(DirToMove.x,DirToMove.y);

        //bandit_boss.moveBy(DirToMove.x*0.01f,DirToMove.y*0.01f);*/
        if (ismovingdown) {

            bandit_boss.moveBy( 0.00f, -1.0f);
        }
        else if (ismovingup) {

            bandit_boss.moveBy( 0.00f,  1.01f);
        }/*
        else if (ismovingright) {

            bandit_boss.moveBy(DirToMove.x * 0.01f, DirToMove.y * 0.01f);
        }else if (ismovingleft) {

            bandit_boss.moveBy(DirToMove.x * 0.01f, DirToMove.y * 0.01f);
        }*/
        if(blueRanger!=null&& leftTransition!=null)
            if(blueRanger.overlaps(leftTransition)){
                bgm.dispose();
                MyGame.banditRoom = null;
                MyGame.banditRoom = new Bandit_Room();
                MyGame.setActiveScreen(MyGame.banditRoom);
            }

        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
      //  bandit_boss.boundToWorld();
    }



}
