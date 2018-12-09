package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by markapptist on 2018-11-12.
 */

public class BlueRanger extends PowerRanger {
    float speed;
    float health=3;

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

        biteAttack = loadAnimationFromSheet("sprites/Macs/dragonbiteleft.png",1,8,0.6f,true);

        breathInit = loadAnimationFromSheet("sprites/Macs/dragonfireinit.png",1,2,0.3f,false);
        breathHold = loadAnimationFromSheet("sprites/Macs/dragonfireleft.png",1,17,0.3f,false);
        breathEnd = loadAnimationFromSheet("sprites/Macs/dragonfireend.png",1,2,0.3f,false);
        //  idle = loadAnimationFromFiles(idleString, 0.5f, true);

        this.setBoundaryRectangle();

        setScale(4.0f);

        setMaxSpeed(900);

    }

    @Override
    public void act(float dt) {
        super.act(dt);

        setAcceleration(900);
    //    accelerateAtAngle(270);
     //   applyPhysics(dt);
    }
    public void takeDamage(int damageTaken){
        if(damageTaken>0)
            health -= damageTaken;
        if (health<0)
            health = 0;
    }
}
