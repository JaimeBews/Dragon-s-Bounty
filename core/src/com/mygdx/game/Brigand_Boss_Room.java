package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Brigand_Boss_Room extends GameScreenUI {


    ActorBeta foreground;
    ActorBeta background;

    @Override
    public void initialize() {
        super.initialize();
        ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("Level Assets/LevelF.png");
        foreground.setSize(WIDTH, HEIGHT);

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Chronos.mp3"));
        bgm.play();
        bgm.setLooping(true);
        uiStage.addActor(tableContainer);


        //CREATE BLUE RANGER*/
        blueRanger = MyGame.blueRanger;
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();
    }

    @Override
    public void update(float dt) {

        blueRanger.preventOverlap(Top_Collider);
        blueRanger.preventOverlap(Right_Collider);
        blueRanger.preventOverlap(Top_Collider);
        blueRanger.preventOverlap(Bottom_Collider);
        if (blueRanger != null && downTransition != null)
            if (blueRanger.overlaps(downTransition)) {
                bgm.dispose();
                MyGame.banditRoom = null;
                MyGame.banditRoom = new Bandit_Room();
                MyGame.setActiveScreen(MyGame.banditRoom);
            }

        super.update(dt);
        blueRanger.act(dt);
        blueRanger.boundToWorld();

    }
}
