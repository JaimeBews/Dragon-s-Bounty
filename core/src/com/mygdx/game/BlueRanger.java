package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by markapptist on 2018-11-12.
 */

public class BlueRanger extends PowerRanger {
    float speed;
    float health=3;
    boolean fireUnlocked =false;
    boolean iceUnlocked =false;
    boolean wasHit=false;
    float badCounter=0.0f;
    float damageDelay=3.0f;
    BlueRanger() {
        speed=5;
        setName("Player");
        String[] idleString = {"sprites/rangers/blue/BlueRanger_0.png", "sprites/rangers/blue/BlueRanger_1.png",
                "sprites/rangers/blue/BlueRanger_2.png", "sprites/rangers/blue/BlueRanger_3.png"};

        idle = loadAnimationFromSheet("sprites/Macs/dragon.png",1,12,0.3f,true);

        walkLeft = loadAnimationFromSheet("sprites/Macs/dragon4.png",1,4,0.3f,true);
        walkRight = loadAnimationFromSheet("sprites/Macs/dragon5.png",1,4,0.3f,true);

        walkUp = loadAnimationFromSheet("sprites/Macs/dragon3.png",1,4,0.3f,true);
        walkDown = loadAnimationFromSheet("sprites/Macs/dragon2.png",1,4,0.3f,true);

        biteAttackLeft = loadAnimationFromSheet("sprites/Macs/dragonbiteleft.png",1,8,0.6f,true);
        biteAttackRight = loadAnimationFromSheet("sprites/Macs/dragonbiteright.png",1,8,0.6f,true);

        breathInit = loadAnimationFromSheet("sprites/Macs/dragonfireinit.png",1,2,0.3f,false);
        breathHold = loadAnimationFromSheet("sprites/Macs/dragonfireleft.png",1,17,0.3f,false);
        breathEnd = loadAnimationFromSheet("sprites/Macs/dragonfireright.png",1,17,0.3f,false);
        //  idle = loadAnimationFromFiles(idleString, 0.5f, true);

        this.setBoundaryRectangle();

        setScale(4.0f);

        setMaxSpeed(900);

    }
    public void setFireUnlocked(){
        fireUnlocked=true;
    }
    public void setIceUnlocked(){
        iceUnlocked=true;
    }
    @Override

    public void act(float dt) {
        super.act(dt);
        if (wasHit) {
            badCounter += dt;
            if (badCounter > damageDelay) {
                wasHit = false;
                badCounter = 0.0f;
            }
            setAcceleration(900);
            //    accelerateAtAngle(270);
            //   applyPhysics(dt);
        }
    }
    public void wonGame(){
        if (MyGame.victoryScreen == null) {
            MyGame.victoryScreen = new VictoryScreen();

            MyGame.setActiveScreen(MyGame.victoryScreen);
        }else {
            MyGame.setActiveScreen(MyGame.victoryScreen);
        }
    }
    public void takeDamage(int damageTaken){
        if(damageTaken>0&&wasHit==false) {
            health -= damageTaken;
            wasHit=true;
        }
        if (health<0)
            health = 0;
    }
    public void healToFull(){
        health=3;
    }
}
