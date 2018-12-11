package com.mygdx.game;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Bandit_Boss extends PowerRanger {
    float speed;
    float health = 100;
    Bandit_Boss() {
    speed=5;

        idle = loadAnimationFromSheet("sprites/Macs/cowboy side2.png",1,8,0.3f,true);
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
