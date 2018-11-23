package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;

/**
 * Created by markapptist on 2018-11-12.
 */

public class VictoryScreen extends ScreenBeta {

    TextButton startButton;
    TextButton helpButton;
    TextButton exitButton;

    Label label;

    /**
     * PARTICLE EFFECTS
     **/
    FireParticle fire;
    FireParticle fire2;
    FireParticle fire3;
    ActorBeta background;
    Music bgm;
    @Override
    public void initialize() {
        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Chronos.mp3"));
        bgm.play();
        bgm.setLooping(true);
        MyGame.gameScreen = null;
       // uiTable.background(skin.getDrawable("window-c"));
        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("sprites/Macs/victory.png");
        background.setSize(WIDTH, HEIGHT);


        uiStage.addActor(tableContainer);

        startButton = new TextButton("Start", skin.get(("default"), TextButton.TextButtonStyle.class));
        startButton.setOrigin(Align.center);
        startButton.setTransform(true);
        startButton.setScale(3);

        setUpButtons();

        label = new Label("LABEL", labelStyle);

        //Add to TABLE

        uiTable.row().padTop(HEIGHT / 12).padBottom(HEIGHT / 12);
        uiTable.add(startButton).size(startButton.getWidth(), startButton.getHeight()).expandX();



        /**PARTICLE EFFECTS**/
        fire = new FireParticle();
        fire.centerAtActor(startButton);
        fire.start();
        fire.setPosition(WIDTH / 2, HEIGHT / 3);
        fire.setScale(2.0f);

        mainStage.addActor(fire);

        fire2 = new FireParticle();
        fire2.centerAtActor(startButton);
        fire2.start();
        fire2.setPosition(0 , HEIGHT  /3);
        fire2.setScale(2.0f);

        mainStage.addActor(fire2);

        fire3 = new FireParticle();
        fire3.centerAtActor(startButton);
        fire3.start();
        fire3.setPosition(WIDTH / 1.5f, HEIGHT / 3);
        fire3.setScale(2.0f);

        mainStage.addActor(fire3);
    }

    public void setUpButtons() {

        startButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                MyGame.level_4 = null ;
                    MyGame.level_4 = new Level_4();
                    bgm.dispose();
                    MyGame.setActiveScreen(MyGame.level_4);


            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void update(float dt) {


       // super.update(dt);
    }
  //  {fire.act(dt);
    // }
}
