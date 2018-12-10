package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Level_7 extends GameScreenUI {
    boolean ismovingleft=false;
    boolean ismovingright = false;
    boolean ismovingup = false;
    boolean ismovingdown = false;
    Vector2 DirToMove;
    float time;
    ActorBeta foreground;
    int i = 0;
    ActorBeta background;
/*
    ActorBeta Right_Collider;
    ActorBeta Left_Collider;
    ActorBeta Top_Collider;
    ActorBeta Bottom_Collider;
*/
    Pirate Boss;
    @Override
    public void initialize() {
        super.initialize();
        //ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("Level Assets/LevelG.png");
        foreground.setSize(WIDTH, HEIGHT);

        //bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Chronos.mp3"));
        //bgm.play();
        //bgm.setLooping(true);
        //uiStage.addActor(tableContainer);

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
        Boss = new Pirate();
        Boss.setPosition(WIDTH/2,HEIGHT/2);
        Boss.setSize(WIDTH/40,HEIGHT/20);
        Boss.setBoundaryRectangle();

        mainStage.addActor(Boss);

        /*background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        //CREATE BLUE RANGER*/
        blueRanger = MyGame.blueRanger;
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        blueRanger.setSize(WIDTH/20,HEIGHT/10);
        mainStage.addActor(blueRanger);
        DirToMove = new Vector2((blueRanger.getX()-Boss.getX()),(blueRanger.getY()-Boss.getY()));
        //Boss.moveBy(DirToMove.x*0.01f,DirToMove.y*0.01f);
        loadUI();
    }

    @Override
    public void update(float dt) {

        //int time = Time;

        Boss.preventOverlap(Left_Collider);
        Boss.preventOverlap(Right_Collider);
        Boss.preventOverlap(Bottom_Collider);
        Boss.preventOverlap(Top_Collider);
        if(i!=3 && dt!=0) {
            time=0;
            Boss.moveBy(DirToMove.x * 0.01f, DirToMove.y * 0.01f);
            if (Boss.overlaps(Top_Collider)) {
                DirToMove = new Vector2((blueRanger.getX() - Boss.getX()), (blueRanger.getY() - Boss.getY()));
                i += 1;
                ismovingdown = true;
                ismovingup = false;
            } else if (Boss.overlaps(Bottom_Collider)) {
                DirToMove = new Vector2((blueRanger.getX() - Boss.getX()), (blueRanger.getY() - Boss.getY()));
                ismovingdown = false;
                ismovingup = true;
                i += 1;
            } else if (Boss.overlaps(Right_Collider)) {
                DirToMove = new Vector2((blueRanger.getX() - Boss.getX()), (blueRanger.getY() - Boss.getY()));
                ismovingright = false;
                ismovingleft = true;
                i += 1;
            } else if (Boss.overlaps(Left_Collider)) {
                DirToMove = new Vector2((blueRanger.getX() - Boss.getX()), (blueRanger.getY() - Boss.getY()));
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
        //Boss.boundToWorld();
        //DirToMove.nor();
        //Vector2 DirToMove = new Vector2((blueRanger.getX()-Boss.getX()),(blueRanger.getY()-Boss.getY()));
        //Boss.moveBy(DirToMove.x,DirToMove.y);

        //Boss.moveBy(DirToMove.x*0.01f,DirToMove.y*0.01f);
        if (ismovingdown) {

            Boss.moveBy(DirToMove.x * 0.01f, DirToMove.y * 0.01f);
        }
        else if (ismovingup) {

            Boss.moveBy(DirToMove.x * 0.01f, DirToMove.y * 0.01f);
        }
        else if (ismovingright) {

            Boss.moveBy(DirToMove.x * 0.01f, DirToMove.y * 0.01f);
        }else if (ismovingleft) {

            Boss.moveBy(DirToMove.x * 0.01f, DirToMove.y * 0.01f);
        }
        if(blueRanger!=null&& leftTransition!=null)
            if(blueRanger.overlaps(leftTransition)){
                bgm.dispose();
                MyGame.level_5 = null;
                MyGame.level_5 = new Level_5();
                MyGame.setActiveScreen(MyGame.level_5);
            }

        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
        Boss.boundToWorld();
    }



}
