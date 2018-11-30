package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;



/**
 * Created by markapptist on 2018-11-12.
 */

public class GameScreenUI extends ScreenBeta {

    BlueRanger blueRanger;
    Touchpad touchpad;

    Skin skin;
    Skin uiSkin;
    Music bgm;
    ActorBeta leftTransition;
    ActorBeta rightTransition;
    ActorBeta downTransition;
    ActorBeta upTransition;
    ActorBeta[] hearts;
    @Override
    public void initialize() {
        hearts= new ActorBeta[3];

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);

        leftTransition = new ActorBeta(0, HEIGHT / 2, mainStage);
        leftTransition.setSize(100,100);
       // leftTransition.loadTexture("sprites/backgrounds/background0_59.png");
        leftTransition.setBoundaryRectangle();

        rightTransition = new ActorBeta(WIDTH-100, HEIGHT / 2, mainStage);
        rightTransition.setSize(100,100);
        // leftTransition.loadTexture("sprites/backgrounds/background0_59.png");
        rightTransition.setBoundaryRectangle();

        downTransition = new ActorBeta(WIDTH/2, 0, mainStage);
        downTransition.setSize(100,100);
        // leftTransition.loadTexture("sprites/backgrounds/background0_59.png");
        downTransition.setBoundaryRectangle();

        upTransition = new ActorBeta(WIDTH/2, HEIGHT-100, mainStage);
        upTransition.setSize(100,100);
        // leftTransition.loadTexture("sprites/backgrounds/background0_59.png");
        upTransition.setBoundaryRectangle();

        mainStage.addActor(leftTransition);
        skin = new Skin(Gdx.files.internal("skins/neon/skin/neon-ui.json"));
        uiSkin = new Skin(Gdx.files.internal("skins/neon/skin/neon-ui.json"));

        uiStage.addActor(tableContainer);

        //mainStage.addActor(hearts);
        //Touchpad
        touchpad = new Touchpad(40.0f, skin, "default");
        touchpad.setPosition(0+touchpad.getWidth()+25, HEIGHT / 3);
        touchpad.setResetOnTouchUp(true);
        touchpad.getColor().a = 1.0f;

        uiTable.add(touchpad).width(touchpad.getWidth() * 1.5f).height(touchpad.getHeight() * 1.5f).padRight(1500).padTop(600);

        Button aButton = new Button(uiSkin, "default");
        Button bButton = new Button(uiSkin, "default");
        aButton.getColor().a = 1.0f;
        bButton.getColor().a = 1.0f;

        uiTable.padLeft(100).add(aButton).width(aButton.getWidth() * 2.0f).height(aButton.getHeight() * 2.0f).bottom().padRight(0);
        uiTable.add(bButton).width(bButton.getWidth() * 2.0f).height(bButton.getHeight() * 2.0f).bottom().padBottom(120).padRight(0);


      //  mainStage.addActor(hearts);

        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float deltaX = ((Touchpad) actor).getKnobPercentX();
                float deltaY = ((Touchpad) actor).getKnobPercentY();

                Gdx.app.log("Delta X", "" + deltaX);
                Gdx.app.log("Delta Y", "" + deltaY);
            }
        });
        aButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                if (MyGame.gameOverScreen == null) {
                    MyGame.gameOverScreen = new GameOverScreen();
                    bgm.dispose();
                    MyGame.setActiveScreen(MyGame.gameOverScreen);
                }else {
                    bgm.dispose();
                    MyGame.setActiveScreen(MyGame.gameOverScreen);
                }
            }
        });
        bButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                if (MyGame.victoryScreen == null) {
                    MyGame.victoryScreen = new VictoryScreen();
                    bgm.dispose();
                    MyGame.setActiveScreen(MyGame.victoryScreen);
                }else {
                    bgm.dispose();
                    MyGame.setActiveScreen(MyGame.victoryScreen);
                }
            }
        });

    }
    public void loadUI(){
        for(int i = 0; i<3; i++) {
            hearts[i] = new ActorBeta(100 * i, HEIGHT - 100, mainStage);
            hearts[i].loadTexture("sprites/myBackgrounds/heart.png");
            mainStage.addActor(hearts[i]);

            MoveToAction moveAction = new MoveToAction();
            moveAction.setPosition(100*i, HEIGHT - 100);
            moveAction.setDuration(1f);
            MoveToAction moveAction2 = new MoveToAction();
            moveAction2.setPosition(100*i, HEIGHT - 130);
            moveAction2.setDuration(1f);

            SequenceAction sAction = new SequenceAction();
            sAction.addAction(moveAction);
            sAction.addAction(moveAction2);
            RepeatAction repeatAction = new RepeatAction();
            repeatAction.setAction(sAction);
            repeatAction.setCount(-1);
            hearts[i].addAction(repeatAction);

            //TODO DONT DO THIS
            blueRanger.setScale(1.2f);

        }
    }
    @Override
    public void update(float dt) {

        touchpad.act(dt);
        blueRanger.setPosition(blueRanger.getX()+touchpad.getKnobPercentX()*(blueRanger.speed),blueRanger.getY()+touchpad.getKnobPercentY()*(blueRanger.speed));

    }


}
