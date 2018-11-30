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
    //J-Stuff
    ActorBeta Right_Collider;
    ActorBeta Left_Collider;
    ActorBeta Top_Collider;
    ActorBeta Bottom_Collider;
    //J-Stuff
    @Override
    public void initialize() {
        hearts= new ActorBeta[3];

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        //J-Stuff
        //Colliders();
        //J-Stuff
        leftTransition = new ActorBeta(0, HEIGHT / 2, mainStage);
        leftTransition.setSize(WIDTH/20,HEIGHT/10);
       // leftTransition.loadTexture("sprites/backgrounds/background0_59.png");
        leftTransition.setBoundaryRectangle();

        rightTransition = new ActorBeta(WIDTH-(WIDTH/20), HEIGHT / 2, mainStage);
        rightTransition.setSize(WIDTH/20,HEIGHT/10);
        // leftTransition.loadTexture("sprites/backgrounds/background0_59.png");
        rightTransition.setBoundaryRectangle();

        downTransition = new ActorBeta(WIDTH/2-(WIDTH/20), 0, mainStage);
        downTransition.setSize(WIDTH/20,HEIGHT/10);
        // leftTransition.loadTexture("sprites/backgrounds/background0_59.png");
        downTransition.setBoundaryRectangle();

        upTransition = new ActorBeta(WIDTH/2-(WIDTH/20), HEIGHT-(HEIGHT/10), mainStage);
        upTransition.setSize(WIDTH/20,HEIGHT/10);
        // leftTransition.loadTexture("sprites/backgrounds/background0_59.png");
        upTransition.setBoundaryRectangle();

        mainStage.addActor(leftTransition);
        skin = new Skin(Gdx.files.internal("skins/neon/skin/neon-ui.json"));
        uiSkin = new Skin(Gdx.files.internal("skins/neon/skin/neon-ui.json"));

        uiStage.addActor(tableContainer);

        touchpad = new Touchpad(40.0f, skin, "default");
        touchpad.setResetOnTouchUp(true);
        touchpad.getColor().a = 1.0f;

        uiTable.add(touchpad).width(WIDTH/5.5f).height(HEIGHT/3.5f).padRight(WIDTH-WIDTH/3.0f).padTop(HEIGHT-HEIGHT/3.0f).padLeft(50);

        Button aButton = new Button(uiSkin, "default");
        Button bButton = new Button(uiSkin, "default");
        aButton.getColor().a = 1.0f;
        bButton.getColor().a = 1.0f;

        uiTable.add(aButton).width(aButton.getWidth() * 2.0f).height(aButton.getHeight() * 2.0f).bottom();
        uiTable.add(bButton).width(bButton.getWidth() * 2.0f).height(bButton.getHeight() * 2.0f).bottom();


      //  mainStage.addActor(hearts);

        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float deltaX = ((Touchpad) actor).getKnobPercentX();
                float deltaY = ((Touchpad) actor).getKnobPercentY();
                blueRanger.setPosition(blueRanger.getX()+deltaX*(blueRanger.speed),blueRanger.getY()+deltaY*(blueRanger.speed));
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
            hearts[i] = new ActorBeta((WIDTH/20) * i, HEIGHT, mainStage);
            hearts[i].loadTexture("sprites/myBackgrounds/heart.png");
            hearts[i].setSize(WIDTH/20,HEIGHT/20);
            mainStage.addActor(hearts[i]);

            MoveToAction moveAction = new MoveToAction();
            moveAction.setPosition((WIDTH/20)*i, HEIGHT - HEIGHT/10);
            moveAction.setDuration(1f);
            MoveToAction moveAction2 = new MoveToAction();
            moveAction2.setPosition((WIDTH/20)*i, HEIGHT - ((HEIGHT/10)-30));
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
        /*
        blueRanger.preventOverlap(Bottom_Collider);
        blueRanger.preventOverlap(Top_Collider);
        blueRanger.preventOverlap(Left_Collider);
        blueRanger.preventOverlap(Right_Collider);
        */
        //blueRanger.preventOverlap(topBoundary);
        touchpad.act(dt);

        if(touchpad.getKnobPercentX() > 0.5 && touchpad.getKnobPercentX() < 0.9) {
            Gdx.app.log("Delta X", "Knob X is " + touchpad.getKnobPercentX());
        }

        if(touchpad.getKnobPercentY() > 0.5 && touchpad.getKnobPercentY() < 0.9) {
            Gdx.app.log("Delta Y", "Knob Y is " + touchpad.getKnobPercentX());
        }

    }
    /*
    public void Colliders()
    {
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
    }

*/
}
