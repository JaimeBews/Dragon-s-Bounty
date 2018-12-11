package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
import com.badlogic.gdx.utils.Array;


/**
 * Created by markapptist on 2018-11-12.
 */

public class GameScreenUI extends ScreenBeta {

    BlueRanger blueRanger;
    Touchpad touchpad;

    Skin skin;
    Skin uiSkin;
    Music bgm;
    Sound biteSFX;
    Sound breathSFX;
    Sound enemyDeathSFX;
    Sound fireWallDestroyedSFX;
    Sound iceWallDestroyedSFX;



    ActorBeta leftTransition;
    ActorBeta rightTransition;
    ActorBeta downTransition;
    ActorBeta upTransition;

    ActorBeta pausedOverlay;

    ActorBeta[] hearts;

    FireBreathP fireBreath;
    IceBreathP iceBreath;
    ActorBeta attackBounds;

    ActorBeta Right_Collider;
    ActorBeta Left_Collider;
    ActorBeta Top_Collider;
    ActorBeta Bottom_Collider;

    boolean wasFacingLeft;
    boolean isAttacking=false;
    boolean pause;
    int faceDir;//left right up down
    int attackType;//fire ice bite

    boolean fireUnlocked =false;
    boolean iceUnlocked =false;

    @Override
    public void initialize() {
        Colliders();
        hearts= new ActorBeta[3];
        biteSFX = Gdx.audio.newSound(Gdx.files.internal("Sounds/BiteAttack.mp3"));
        breathSFX = Gdx.audio.newSound(Gdx.files.internal("Sounds/BreathAttack.mp3"));

        enemyDeathSFX = Gdx.audio.newSound(Gdx.files.internal("Sounds/ZombiePain.mp3"));
        fireWallDestroyedSFX = Gdx.audio.newSound(Gdx.files.internal("Sounds/Sizzling.mp3"));
        iceWallDestroyedSFX = Gdx.audio.newSound(Gdx.files.internal("Sounds/Freeze.mp3"));

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);

        leftTransition = new ActorBeta(0, HEIGHT / 2, mainStage);
        leftTransition.setSize(WIDTH/19.0f,100);
       // leftTransition.loadTexture("sprites/backgrounds/background0_59.png");
        leftTransition.setBoundaryRectangle();

        rightTransition = new ActorBeta(WIDTH-100, HEIGHT / 2, mainStage);
        rightTransition.setSize(WIDTH/20.0f,100);
        // leftTransition.loadTexture("sprites/backgrounds/background0_59.png");
        rightTransition.setBoundaryRectangle();

        downTransition = new ActorBeta(WIDTH/2, 0, mainStage);
        downTransition.setSize(WIDTH/20.0f,100);
        // leftTransition.loadTexture("sprites/backgrounds/background0_59.png");
        downTransition.setBoundaryRectangle();

        upTransition = new ActorBeta(WIDTH/2, HEIGHT-100, mainStage);
        upTransition.setSize(WIDTH/20.0f,100);
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

       // uiTable.add(pauseButton);
        uiTable.add(touchpad).width(touchpad.getWidth() * 1.5f).height(touchpad.getHeight() * 1.5f).padRight(WIDTH/1.5f).padTop(600);
        Button pauseButton = new Button(uiSkin, "default");
        Button aButton = new Button(uiSkin, "default");
        Button bButton = new Button(uiSkin, "default2");
        Button cButton = new Button(uiSkin, "default3");
        aButton.getColor().a = 1.0f;
        cButton.getColor().a =1.0f;

        uiTable.padLeft(WIDTH/20).add(aButton).width(aButton.getWidth() * 2.0f).height(aButton.getHeight() * 2.0f).bottom().padRight(0);
        uiTable.add(bButton).width(bButton.getWidth() * 2.0f).height(bButton.getHeight() * 2.0f).bottom().padBottom(HEIGHT/20).padRight(0);
        uiTable.add(pauseButton).width(bButton.getWidth() * 1.5f).height(bButton.getHeight() * 1.5f).top().padBottom(HEIGHT/30);
        uiTable.row();
        uiTable.add(cButton).width(cButton.getWidth() * 2.0f).height(cButton.getHeight() * 2.0f).bottom().padBottom(HEIGHT/30).padRight(-WIDTH/10).right();

        //uiTable.debug();
      //  mainStage.addActor(hearts);

        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float deltaX = ((Touchpad) actor).getKnobPercentX();
                float deltaY = ((Touchpad) actor).getKnobPercentY();

            }
        });
        pauseButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                pause=!pause;
                isPaused=!isPaused;
                if(pause) {
                    pausedOverlay = new ActorBeta(0, 0 , mainStage);

                    pausedOverlay.loadTexture("sprites/myBackgrounds/Paused.png");
                    pausedOverlay.setSize(WIDTH , HEIGHT);
                }else
                    pausedOverlay.remove();
            }
        });
        aButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                if(!isAttacking&&iceUnlocked) {
                    breathSFX.play();
                    isAttacking = true;
                    attackType=1;
                    attackBounds = new ActorBeta();
                    attackBounds.setSize(WIDTH / 8, HEIGHT / 8);
                    attackBounds.setBoundaryRectangle();
                    mainStage.addActor(attackBounds);
                    blueRanger.elapsedTime=0;
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
                if(!isAttacking&&fireUnlocked) {
                    breathSFX.play();
                    isAttacking = true;
                    attackType=2;
                    attackBounds = new ActorBeta();
                    attackBounds.setSize(WIDTH / 8, HEIGHT / 8);
                    attackBounds.setBoundaryRectangle();
                    mainStage.addActor(attackBounds);
                    blueRanger.elapsedTime=0;
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
        cButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                if(!isAttacking&&!pause) {
                    biteSFX.play();
                    isAttacking = true;
                    attackType=3;
                    attackBounds = new ActorBeta();
                    attackBounds.setSize(WIDTH / 32, HEIGHT / 16);
                    attackBounds.setBoundaryRectangle();
                    mainStage.addActor(attackBounds);
                    blueRanger.elapsedTime=0;
                    if (faceDir == 1) {
                        attackBounds.setPosition(blueRanger.getX() - (WIDTH / 32), blueRanger.getY());

                    } else {
                        blueRanger.setX(blueRanger.getX() + blueRanger.getWidth());
                        attackBounds.setPosition(blueRanger.getX(), blueRanger.getY());
                    }
                }
            }
        });
    }
    public void setFireUnlocked(){
        fireUnlocked=true;
    }
    public void setIceUnlocked(){
        iceUnlocked=true;
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
        if(blueRanger.health==2&&hearts[2]!=null)
            hearts[2].remove();
        if(blueRanger.health==1&&hearts[1]!=null)
            hearts[1].remove();
        if(blueRanger.health==0&&hearts[0]!=null)
            hearts[0].remove();
        if(!pause) {

            touchpad.act(dt);

            SetAnimations(dt);
            CheckAttackCollisions();
            if(!isAttacking) {
                CheckDirection();
                blueRanger.setPosition(blueRanger.getX() + touchpad.getKnobPercentX() * (blueRanger.speed), blueRanger.getY() + touchpad.getKnobPercentY() * (blueRanger.speed));
            }
        }
    }
    private void CheckAttackCollisions(){
        if(mainStage.getRoot().findActor("Enemy")!=null&&attackBounds!=null) {
            Array<Actor> stageActors = mainStage.getActors();
          //  EnemyBase test = mainStage.getRoot().findActor("Enemy");
          //  if(attackBounds.overlaps(test)) {
           //     test.kill();
         //       enemyDeathSFX.play();
          //  }

            for(int i=0; i<stageActors.size; i++){
                Actor a = stageActors.get(i);

                if(a.getClass()==Bandit.class||a.getClass()==Brigand.class||a.getClass()==PirateA.class) {
                    if (a != null ) {
                        if (attackBounds.overlaps((EnemyBase) a)) {
                            ((EnemyBase) a).kill();
                            enemyDeathSFX.play();
                            Gdx.app.log("tesssst",""+a);
                        }
                    }
                }
            }

        }
        if(mainStage.getRoot().findActor("IceWall")!=null&&attackBounds!=null&&attackType==2) {
            ActorBeta test = mainStage.getRoot().findActor("IceWall");
            if(attackBounds.overlaps(test)) {
                test.setX(-100000);
                test.remove();
                iceWallDestroyedSFX.play();
            }
        }
        if(mainStage.getRoot().findActor("FireWall")!=null&&attackBounds!=null&&attackType==1) {
            ActorBeta test = mainStage.getRoot().findActor("FireWall");
            if(attackBounds.overlaps(test)) {
                test.setX(-100000);
                test.remove();
                fireWallDestroyedSFX.play();
            }
        }
    }
    float counter;
    private void SetAnimations(float dt){
        if(isAttacking) {
            if(attackType==1||attackType==2) {
                blueRanger.setAnimation(blueRanger.breathHold);

            } else
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
    private void CheckDirection(){//left right up down
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
        if(faceDir==2)
            fireBreath.setPosition(fireBreath.getX()+blueRanger.getWidth()/2,fireBreath.getY()+blueRanger.getHeight()/4);
        else
            fireBreath.setPosition(fireBreath.getX()-blueRanger.getWidth()/2,fireBreath.getY()+blueRanger.getHeight()/4);
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
        if(faceDir==2)
            iceBreath.setPosition(iceBreath.getX()+blueRanger.getWidth()/2,iceBreath.getY()+blueRanger.getHeight()/4);
        else
            iceBreath.setPosition(iceBreath.getX()-blueRanger.getWidth()/2,iceBreath.getY()+blueRanger.getHeight()/4);
    }

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

}
