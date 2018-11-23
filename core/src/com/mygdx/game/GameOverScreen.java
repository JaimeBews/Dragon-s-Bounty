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

public class GameOverScreen extends ScreenBeta {

    TextButton startButton;
    TextButton helpButton;
    TextButton exitButton;

    Label label;




    ActorBeta background;
    ActorBeta cloud;

    Music bgm;
    @Override
    public void initialize() {
        bgm = Gdx.audio.newMusic(Gdx.files.internal("Music/Chronos.mp3"));
        bgm.play();
        bgm.setLooping(true);
        MyGame.gameScreen = null;
       // uiTable.background(skin.getDrawable("window-c"));
        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("sprites/Macs/defeat.png");
        background.setSize(WIDTH, HEIGHT);



        uiStage.addActor(tableContainer);

        startButton = new TextButton("Restart", skin.get(("default"), TextButton.TextButtonStyle.class));
        startButton.setOrigin(Align.center);
        startButton.setTransform(true);
        startButton.setScale(3);

        exitButton = new TextButton("Quit", skin.get(("default"), TextButton.TextButtonStyle.class));
        exitButton.setOrigin(Align.center);
        exitButton.setTransform(true);
        exitButton.setScale(3);

        setUpButtons();

        label = new Label("LABEL", labelStyle);

        //Add to TABLE

        uiTable.row().padTop(HEIGHT / 12).padBottom(HEIGHT / 12);
        uiTable.add(startButton).size(startButton.getWidth(), startButton.getHeight()).expandX();


        uiTable.row().padTop(HEIGHT / 12).padBottom(HEIGHT / 12);
        uiTable.add(exitButton).size(exitButton.getWidth(), exitButton.getHeight()).expandX();

    }

    public void setUpButtons() {

        startButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                MyGame.level_4 = null;
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
