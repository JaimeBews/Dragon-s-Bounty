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

    FireBreathP fireBreath;
    IceBreathP iceBreath;
    ActorBeta attackBounds;
    boolean wasFacingLeft;
    boolean isAttacking=false;
    int faceDir;//left right up down

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

            }
        });
        aButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                if(!isAttacking) {
                    isAttacking = true;
                    attackBounds = new ActorBeta();
                    attackBounds.setSize(WIDTH / 8, HEIGHT / 8);
                    attackBounds.setBoundaryRectangle();
                    mainStage.addActor(attackBounds);
                    if (faceDir == 1) {
                        runIceBreath(-1);

                        attackBounds.setPosition(blueRanger.getX() - (WIDTH / 8), blueRanger.getY());

                    } else {
                        runIceBreath(1);
                        blueRanger.setX(blueRanger.getX() + blueRanger.getWidth());
                        attackBounds.setPosition(blueRanger.getX(), blueRanger.getY());
                    }
                }
            }
        });
        bButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                if(!isAttacking) {
                    isAttacking = true;
                    attackBounds = new ActorBeta();
                    attackBounds.setSize(WIDTH / 8, HEIGHT / 8);
                    attackBounds.setBoundaryRectangle();
                    mainStage.addActor(attackBounds);
                    if (faceDir == 1) {
                        runFireBreath(-1);

                        attackBounds.setPosition(blueRanger.getX() - (WIDTH / 8), blueRanger.getY());

                    } else {
                        runFireBreath(1);
                        blueRanger.setX(blueRanger.getX() + blueRanger.getWidth());
                        attackBounds.setPosition(blueRanger.getX(), blueRanger.getY());
                    }
                }

            }
        });

    }
    public void loadUI(){

        for(int i = 0; i<blueRanger.health; i++) {
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
        checkDirection();
        setAnimations(dt);

        if(mainStage.getRoot().findActor("Enemy")!=null&&attackBounds!=null) {
            EnemyBase test = mainStage.getRoot().findActor("Enemy");
            if(attackBounds.overlaps(test))
                test.kill();
        }

        blueRanger.setPosition(blueRanger.getX()+touchpad.getKnobPercentX()*(blueRanger.speed),blueRanger.getY()+touchpad.getKnobPercentY()*(blueRanger.speed));

    }
    float counter;
    private void setAnimations(float dt){
        if(isAttacking) {
            blueRanger.setAnimation(blueRanger.biteAttack);
             if(faceDir==2) {
                blueRanger.setWidth(-blueRanger.getWidth());

            }
            counter+=dt;
            if (counter>2) {
                isAttacking = false;
                counter = 0;
                blueRanger.setWidth(-blueRanger.getWidth());
                attackBounds.remove();
                if(faceDir==2)
                    blueRanger.setX(blueRanger.getX()-blueRanger.getWidth());
            }
        }
        else if(faceDir==1){
            blueRanger.setAnimation(blueRanger.walkLeft);
        }
        else if(faceDir==2){
            blueRanger.setAnimation(blueRanger.walkRight);
        }
    }
    private void gameOver(){
        if (MyGame.gameOverScreen == null) {
            MyGame.gameOverScreen = new GameOverScreen();
            bgm.dispose();
            MyGame.setActiveScreen(MyGame.gameOverScreen);
        }else {
            bgm.dispose();
            MyGame.setActiveScreen(MyGame.gameOverScreen);
        }
    }
    private void wonGame(){
        if (MyGame.victoryScreen == null) {
            MyGame.victoryScreen = new VictoryScreen();
            bgm.dispose();
            MyGame.setActiveScreen(MyGame.victoryScreen);
        }else {
            bgm.dispose();
            MyGame.setActiveScreen(MyGame.victoryScreen);
        }
    }
    private void checkDirection(){//left right up down
        if(touchpad.getKnobPercentX()<0){
            faceDir=1;
        }
        else if(touchpad.getKnobPercentX()>0){
            faceDir=2;
        }
    }
    private void runFireBreath(float scaleX){
        if(fireBreath!=null)
            fireBreath = null;
        fireBreath = new FireBreathP();
        fireBreath.setScaleX(scaleX);
        fireBreath.setScaleY(.5f);
        fireBreath.start();

        mainStage.addActor(fireBreath);
        fireBreath.centerAtActor(blueRanger);
    }
    private void runIceBreath(float scaleX){
        if(iceBreath!=null)
            iceBreath = null;
        iceBreath = new IceBreathP();
        iceBreath.setScaleX(scaleX);
        iceBreath.setScaleY(.5f);
        iceBreath.start();

        mainStage.addActor(iceBreath);
        iceBreath.centerAtActor(blueRanger);
    }

}
