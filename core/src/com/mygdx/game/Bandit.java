package com.mygdx.game;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Bandit extends EnemyBase {
    float speed;
    Bandit() {
    speed=5;

        idle = loadAnimationFromSheet("sprites/Dennis/Bandit.png",4,4,0.3f,true);
     //  idle = loadAnimationFromFiles(idleString, 0.5f, true);

        this.setBoundaryRectangle();

        setScale(4.0f);

        setMaxSpeed(900);

    }

    @Override
    public void act(float dt) {
        super.act(dt);
        shoot(other);
        setAcceleration(900);
    //    accelerateAtAngle(270);
     //   applyPhysics(dt);
    }

    public void shoot(ActorBeta other)
    {

        if(this.isWithinDistance(150.0f, other )) {
            this.move = false;
            System.out.println("shoot");
        }
         else
        {
            System.out.println("not in dist");
            this.move = true;
        }

    }


}
