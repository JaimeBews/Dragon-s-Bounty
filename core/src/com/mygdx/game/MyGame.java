package com.mygdx.game;

/**
 * Created by markapptist on 2018-09-26.
 */

public class MyGame extends GameBeta {

    static MenuScreen menuScreen;
    static GameScreen gameScreen;
    static GameOverScreen gameOverScreen;
    static VictoryScreen victoryScreen;
    static Bandit_Room_2 banditRoom2;
    static Buccaneer_Room buccaneerRoom;
    static Treasure_Room treasureRoom;
    static Start_Room startRoom;
    static Bandit_Room banditRoom;
    static Brigand_Boss_Room brigandBossRoom;
    static Bandit_Boss_Room banditBossRoom;
    static Brigand_Room brigandRoom;
    static Buccaneer_Boss_Room buccaneerBossRoom;
    static Hub_Room hubRoom;
    static Item_Room itemRoom;

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
