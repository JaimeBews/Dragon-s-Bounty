package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by markapptist on 2018-11-12.
 */

public class GameScreen extends GameScreenUI {



    ActorBeta foreground;
    ActorBeta background;


    public void initialize() {
        super.initialize();
        ActorBeta.setWorldBounds(WIDTH, HEIGHT);
         MyGame.gameOverScreen=null;
       MyGame.victoryScreen=null;
        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("sprites/backgrounds/background0_59.png");
        foreground.setSize(WIDTH, HEIGHT);

        background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        uiStage.addActor(tableContainer);

        //CREATE BLUE RANGER
        blueRanger = new BlueRanger();
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Beat One.mp3"));
        bgm.play();
        bgm.setLooping(true);
        loadUI();
    }


    public void update(float dt) {
        if(blueRanger!=null&& leftTransition!=null)
            if(blueRanger.overlaps(leftTransition)){
                background.setScale(0.1f);
                bgm.dispose();
           }

        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();
    }


}
