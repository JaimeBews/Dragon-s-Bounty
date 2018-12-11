package com.mygdx.game;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Cowboy_Boss extends PowerRanger {
    float speed;
    Cowboy_Boss() {
    speed=5;

        idle = loadAnimationFromSheet("sprites/Macs/pirate side.png",1,4,0.3f,true);
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
