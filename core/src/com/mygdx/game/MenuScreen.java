package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;

/**
 * Created by markapptist on 2018-11-12.
 */

public class MenuScreen extends ScreenBeta {

    TextButton startButton;
    TextButton helpButton;
    TextButton exitButton;

    Label label;

    /**
     * PARTICLE EFFECTS
     **/
    FireParticle fire;

    ActorBeta background;
    ActorBeta cloud;
    ActorBeta Title;
    Music bgm;
    @Override
    public void initialize() {
        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Chronos.mp3"));
        bgm.play();
        bgm.setLooping(true);
        bgm.setVolume(.2f);
       // uiTable.background(skin.getDrawable("window-c"));
        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("sprites/myBackgrounds/Main Menu.png");
        background.setSize(WIDTH, HEIGHT);

        cloud = new ActorBeta(50, 800, mainStage);
        cloud.loadTexture("sprites/myBackgrounds/cloud.png");
        cloud.setSize(300, 100);

        Title = new ActorBeta(WIDTH/10, HEIGHT-400, mainStage);
        Title.loadTexture("sprites/Dennis/Title.png");
        Title.setSize(600, 400);




        ScaleToAction scaleAction = new ScaleToAction();
        scaleAction.setScale(1);
        scaleAction.setDuration(5f);

        ScaleToAction scaleAction2 = new ScaleToAction();
        scaleAction2.setScale(.0001f);
        scaleAction2.setDuration(.0001f);

        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(scaleAction2);
        sequenceAction.addAction(scaleAction);
        uiStage.addActor(tableContainer);

        uiStage.addAction(sequenceAction);
        startButton = new TextButton("Start", skin.get(("default"), TextButton.TextButtonStyle.class));
        startButton.setOrigin(Align.center);
        startButton.setTransform(true);
        startButton.setScale(3);

        helpButton = new TextButton("Help", skin.get(("default"), TextButton.TextButtonStyle.class));
        helpButton.setOrigin(Align.center);
        helpButton.setTransform(true);
        helpButton.setScale(3);

        exitButton = new TextButton("Exit", skin.get(("default"), TextButton.TextButtonStyle.class));
        exitButton.setOrigin(Align.center);
        exitButton.setTransform(true);
        exitButton.setScale(3);

        setUpButtons();

        label = new Label("LABEL", labelStyle);

        //Add to TABLE

        uiTable.row().padTop(HEIGHT / 12).padBottom(HEIGHT / 12);
        uiTable.add(startButton).size(startButton.getWidth(), startButton.getHeight()).expandX();

        uiTable.row().padTop(HEIGHT / 12).padBottom(HEIGHT / 12);
        uiTable.add(helpButton).size(helpButton.getWidth(), helpButton.getHeight()).expandX();

        uiTable.row().padTop(HEIGHT / 12).padBottom(HEIGHT / 12);
        uiTable.add(exitButton).size(exitButton.getWidth(), exitButton.getHeight()).expandX();

        /**PARTICLE EFFECTS**/
    /*    fire = new FireParticle();
        fire.centerAtActor(startButton);
        fire.start();
        fire.setPosition(WIDTH / 2, HEIGHT / 2);
        fire.setScale(2.0f);

        mainStage.addActor(fire);*/
    }

    public void setUpButtons() {

        startButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                if (MyGame.startRoom == null) {
                    MyGame.startRoom = new Start_Room();
                    bgm.dispose();
                    MyGame.setActiveScreen(MyGame.startRoom);
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        bgm.dispose();
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
        if (cloud.getX()>Gdx.graphics.getWidth()){
            cloud.setX(-cloud.getWidth());
        }
        cloud.setX(cloud.getX()+.4f);

       // super.update(dt);
    }
  //  {fire.act(dt);
    // }
}
