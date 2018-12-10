package com.mygdx.game;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Brigand extends EnemyBase {
    float speed;
    Brigand() {
    speed=5;

        idle = loadAnimationFromSheet("sprites/Dennis/BrigandAnim/BrigandIdleLeft.png",1,2,0.3f,true);

        walkLeft = loadAnimationFromSheet("sprites/Dennis/BrigandAnim/BrigandWalkLeft.png",1,2,0.3f,true);
        walkRight = loadAnimationFromSheet("sprites/Dennis/BrigandAnim/BrigandWalkRight.png",1,2,0.3f,true);
        walkUp = loadAnimationFromSheet("sprites/Dennis/BrigandAnim/BrigandWalkUp.png",1,2,0.3f,true);
        walkDown = loadAnimationFromSheet("sprites/Dennis/BrigandAnim/BrigandWalkDown.png",1,2,0.3f,true);

        attackLeft = loadAnimationFromSheet("sprites/Dennis/BrigandAnim/BrigandAttackLeft.png",1,2,0.8f,true);
        attackRight = loadAnimationFromSheet("sprites/Dennis/BrigandAnim/BrigandAttackRight.png",1,2,0.8f,true);
        attackUp = loadAnimationFromSheet("sprites/Dennis/BrigandAnim/BrigandAttackUp.png",1,2,0.8f,true);
        attackDown = loadAnimationFromSheet("sprites/Dennis/BrigandAnim/BrigandAttackDown.png",1,2,0.8f,true);
     //  idle = loadAnimationFromFiles(idleString, 0.5f, true);

        this.setBoundaryRectangle();

        setScale(4.0f);

        setMaxSpeed(900);

    }

    @Override
    public void act(float dt) {
        super.act(dt);

        setAcceleration(900);
        SetAnimations();
    //    accelerateAtAngle(270);
     //   applyPhysics(dt);
    }

    private void SetAnimations(){
        if(this.attack == false) {
            if (movedir == 0) {
                this.setAnimation(idle);
            } else if (movedir == 1) {
                this.setAnimation(walkLeft);
            } else if (movedir == 2) {
                this.setAnimation(walkRight);
            } else if (movedir == 3) {
                this.setAnimation(walkUp);
            } else if (movedir == 4) {
                this.setAnimation(walkDown);
            }
        }else if(this.attack == true && this.move == false)
        {
            if (movedir == 0) {
                this.setAnimation(attackUp);
                if(isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                }
            } else if (movedir == 1) {
                this.setAnimation(attackLeft);
                if(isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                }
            } else if (movedir == 2) {
                this.setAnimation(attackRight);
                if(isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                }
            } else if (movedir == 3) {
                this.setAnimation(attackUp);
                if(isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                }
            } else if (movedir == 4) {
                this.setAnimation(attackDown);
                if(isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                }
            }
        }
    }

}
