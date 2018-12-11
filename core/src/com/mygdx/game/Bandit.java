package com.mygdx.game;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Bandit extends EnemyBase {
    float speed;
    Bandit() {
    speed=5;
        idle = loadAnimationFromSheet("sprites/Dennis/BanditAnim/BanditIdleLeft.png",1,2,0.3f,true);

        walkLeft = loadAnimationFromSheet("sprites/Dennis/BanditAnim/BanditWalkLeft.png",1,2,0.3f,true);
        walkRight = loadAnimationFromSheet("sprites/Dennis/BanditAnim/BanditWalkRight.png",1,2,0.3f,true);
        walkUp = loadAnimationFromSheet("sprites/Dennis/BanditAnim/BanditWalkUp.png",1,2,0.3f,true);
        walkDown = loadAnimationFromSheet("sprites/Dennis/BanditAnim/BanditWalkDown.png",1,2,0.3f,true);

        attackLeft = loadAnimationFromSheet("sprites/Dennis/BanditAnim/BanditAttackLeft.png",1,2,1.0f,true);
        attackRight = loadAnimationFromSheet("sprites/Dennis/BanditAnim/BanditAttackRight.png",1,2,1.0f,true);
        attackUp = loadAnimationFromSheet("sprites/Dennis/BanditAnim/BanditAttackUp.png",1,2,1.0f,true);
        attackDown = loadAnimationFromSheet("sprites/Dennis/BanditAnim/BanditAttackDown.png",1,2,1.0f,true);



        //  idle = loadAnimationFromFiles(idleString, 0.5f, true);

        this.setBoundaryRectangle();

        setScale(4.0f);

        setMaxSpeed(900);

    }

    @Override
    public void act(float dt) {
        super.act(dt);
        shoot(other);
        SetAnimations();
        setAcceleration(900);
    //    accelerateAtAngle(270);
     //   applyPhysics(dt);
    }

    public void shoot(ActorBeta other)
    {

        if(this.isWithinDistance(150.0f, other )) {
            this.move = false;
            //this.attack = true;
            System.out.println("shoot");
        }
         else
        {
            System.out.println("not in dist");
            this.move = true;
            this.attack = false;
        }

    }

    private void SetAnimations(){
        if(this.attack == false && this.move == true) {
            if (movedir == 0) {
                this.setAnimation(this.idle);
            } else if (movedir == 1) {
                this.setAnimation(this.walkLeft);
            } else if (movedir == 2) {
                this.setAnimation(this.walkRight);
            } else if (movedir == 3) {
                this.setAnimation(this.walkUp);
            } else if (movedir == 4) {
                this.setAnimation(this.walkDown);
            }
        }else if(this.attack == true && this.move == false)
        {
            if (movedir == 0) {
                this.setAnimation(this.attackUp);
                if(this.isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                    other.takeDamage(1);
                    attackDelay = 5.0f;
                }
            } else if (movedir == 1) {
                this.setAnimation(this.attackLeft);
                if(this.isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                    other.takeDamage(1);
                    attackDelay = 5.0f;
                }
            } else if (movedir == 2) {
                this.setAnimation(this.attackRight);
                if(this.isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                    other.takeDamage(1);
                    attackDelay = 5.0f;
                }
            } else if (movedir == 3) {
                this.setAnimation(this.attackUp);
                if(this.isAnimationFinished()) {
                    this.attack = false;
                    this.move = true;
                    other.takeDamage(1);
                    attackDelay = 5.0f;
                }
            } else if (movedir == 4) {
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
