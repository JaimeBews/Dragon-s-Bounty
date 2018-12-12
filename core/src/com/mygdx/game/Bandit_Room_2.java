package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.ActorBeta;
import com.mygdx.game.BlueRanger;
import com.mygdx.game.ScreenBeta;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Bandit_Room_2 extends GameScreenUI {



    Touchpad touchpad;

    ActorBeta foreground;
    ActorBeta background;

    @Override
    public void initialize() {
        super.initialize();
        ActorBeta.setWorldBounds(WIDTH, HEIGHT);

        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("Level Assets/LevelA.png");
        foreground.setSize(WIDTH, HEIGHT);

        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Beat One.mp3"));
        bgm.play();
        bgm.setLooping(true);
        bgm.setVolume(.2f);
        //background = new ActorBeta(900, 300, mainStage);
       // background.loadTexture("sprites/backgrounds/background0_20.png");
        //background.setScale(2.0f);

        //skin = new Skin(Gdx.files.internal("skins/pixthulhu/skin/pixthulhu-ui.json"));
        //uiSkin = new Skin(Gdx.files.internal("skins/arcade/skin/arcade-ui.json"));

        //uiStage.addActor(tableContainer);
        //Touchpad
        //touchpad = new Touchpad(40.0f, skin, "default");
        //touchpad.setPosition(WIDTH / 5, HEIGHT / 3);
        //touchpad.setResetOnTouchUp(true);
        //touchpad.getColor().a = 1.0f;

        //uiTable.add(touchpad).width(touchpad.getWidth() * 1.5f).height(touchpad.getHeight() * 1.5f).padRight(800).padTop(600);

        //Button aButton = new Button(uiSkin, "red");
       // Button bButton = new Button(uiSkin, "blue");
       // aButton.getColor().a = 1.0f;
       // bButton.getColor().a = 1.0f;

       // uiTable.padRight(50).add(aButton).width(aButton.getWidth() * 2.0f).height(aButton.getHeight() * 2.0f).bottom().padRight(120);
        //uiTable.add(bButton).width(bButton.getWidth() * 2.0f).height(bButton.getHeight() * 2.0f).bottom().padBottom(120);

       // touchpad.addListener(new ChangeListener() {
            //@Override
           // public void changed(ChangeEvent event, Actor actor) {
           //     float deltaX = ((Touchpad) actor).getKnobPercentX();
           //     float deltaY = ((Touchpad) actor).getKnobPercentY();

            //    Gdx.app.log("Delta X", "" + deltaX);
           //     Gdx.app.log("Delta Y", "" + deltaY);
          //  }
        //});

        //CREATE BLUE RANGER
        blueRanger = MyGame.blueRanger;
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);
        mainStage.addActor(blueRanger);
        loadUI();
   }

    @Override
    public void update(float dt) {

        if(blueRanger!=null&& upTransition!=null)
            if(blueRanger.overlaps(upTransition)){
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
