package com.mygdx.game;

/**
 * Created by markapptist on 2018-09-26.
 */

public class MyGame extends GameBeta {

    static MenuScreen menuScreen;
    static GameScreen gameScreen;
    static GameOverScreen gameOverScreen;
    static VictoryScreen victoryScreen;
    static Level_1 level_1;
    static Level_2 level_2;
    static Level_3 level_3;
    static Level_4 level_4;
    static Level_5 level_5;
    static Level_6 level_6;
    static Level_7 level_7;
    static Level_8 level_8;
    static Level_9 level_9;
    static Level_10 level_10;
    static Level_11 level_11;

    static BlueRanger blueRanger;





    boolean paused = false;

    @Override
    public void create() {

        super.create();
        blueRanger = new BlueRanger();
        menuScreen = new MenuScreen();

        setScreen(menuScreen);
    }

}
