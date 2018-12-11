package com.mygdx.game;

/**
 * Created by markapptist on 2018-11-12.
 */

public class PirateA extends EnemyBase {
    float speed;
    PirateA() {
    speed=5;

        idle = loadAnimationFromSheet("sprites/Dennis/PirateAAnim/PirateAIdleLeft.png",1,2,0.3f,true);

        walkLeft = loadAnimationFromSheet("sprites/Dennis/PirateAAnim/PirateAWalkLeft.png",1,2,0.3f,true);
        walkRight = loadAnimationFromSheet("sprites/Dennis/PirateAAnim/PirateAWalkRight.png",1,2,0.3f,true);
        walkUp = loadAnimationFromSheet("sprites/Dennis/PirateAAnim/PirateAWalkUp.png",1,2,0.3f,true);
        walkDown = loadAnimationFromSheet("sprites/Dennis/PirateAAnim/PirateAWalkDown.png",1,2,0.3f,true);

        attackLeft = loadAnimationFromSheet("sprites/Dennis/PirateAAnim/PirateAAttackLeft.png",1,2,1.0f,true);
        attackRight = loadAnimationFromSheet("sprites/Dennis/PirateAAnim/PirateAAttackRight.png",1,2,1.0f,true);
        attackUp = loadAnimationFromSheet("sprites/Dennis/PirateAAnim/PirateAAttackUp.png",1,2,1.0f,true);
        attackDown = loadAnimationFromSheet("sprites/Dennis/PirateAAnim/PirateAAttackDown.png",1,2,1.0f,true);
     //  idle = loadAnimationFromFiles(idleString, 0.5f, true);

        this.setBoundaryRectangle();

        setScale(4.0f);

        setMaxSpeed(900);

    }

    @Override
    public void act(float dt) {
        super.act(dt);
        SetAnimations();
        setAcceleration(900);
    //    accelerateAtAngle(270);
     //   applyPhysics(dt);
    }

    private void SetAnimations(){
        if(this.attack == false && this.move == true) {
            if (movedir == 0) {
                this.setAnimation(this.idle);
            } else if (movedir == 1) {
                this.setAnimation(walkLeft);
            } else if (movedir == 2) {
                this.setAnimation(this.walkRight);
            } else if (movedir == 3) {
                this.setAnimation(this.walkUp);
            } else if (movedir == 4) {
                this.setAnimation(this.walkDown);
            }
        }

        if(this.attack == true && this.move == false)
        {
            if (attackdir == 0) {
                this.setAnimation(this.attackUp);
                if(this.isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                    other.takeDamage(1);
                    attackDelay = 5.0f;
                }
            } else if (attackdir == 1) {
                this.setAnimation(this.attackLeft);
                if(this.isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                    other.takeDamage(1);
                    attackDelay = 5.0f;
                }
            } else if (attackdir == 2) {
                this.setAnimation(this.attackRight);
                if(this.isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                    other.takeDamage(1);
                    attackDelay = 5.0f;
                }
            } else if (attackdir == 3) {
                this.setAnimation(this.attackUp);
                if(this.isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                    other.takeDamage(1);
                    attackDelay = 5.0f;
                }
            } else if (attackdir == 4) {
                this.setAnimation(this.attackDown);
                if(this.isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                    other.takeDamage(1);
                    attackDelay = 5.0f;
                }
            }
        }
    }

}
