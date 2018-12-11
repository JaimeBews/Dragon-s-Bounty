package com.mygdx.game;

public class BulletL extends PowerRanger {
    float speed;
    BulletL() {
        speed=5;

        idle = loadAnimationFromSheet("sprites/Macs/bulletL.png",1,4,0.3f,true);
        //  idle = loadAnimationFromFiles(idleString, 0.5f, true);

        this.setBoundaryRectangle();

        setScale(4.0f);

        setMaxSpeed(900);

    }

    @Override
    public void act(float dt) {
        super.act(dt);
        setAcceleration(900);
    }
}
