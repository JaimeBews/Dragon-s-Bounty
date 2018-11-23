package com.mygdx.game;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Princess extends PowerRanger {
    float speed;
    Princess() {
    speed=5;
        String[] idleString = {"sprites/rangers/blue/BlueRanger_0.png", "sprites/rangers/blue/BlueRanger_1.png",
                "sprites/rangers/blue/BlueRanger_2.png", "sprites/rangers/blue/BlueRanger_3.png"};
        idle = loadAnimationFromSheet("sprites/Macs/princess.png",1,8,0.3f,true);
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

}
