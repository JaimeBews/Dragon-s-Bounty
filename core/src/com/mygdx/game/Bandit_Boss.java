package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Bandit_Boss extends PowerRanger {
    float speed;
    float health = 1;
    boolean wasHit=false;
    float damageDelay=2.1f;
    float attackDelay=2.5f;
    float badCounter=0.0f;
    BlueRanger other;
    BulletL bullet;

    Bandit_Boss() {
    speed=5;
    setName("BanditBoss");
        idle = loadAnimationFromSheet("sprites/Macs/cowboy side2.png",1,8,0.3f,true);
     //  idle = loadAnimationFromFiles(idleString, 0.5f, true);

        this.setBoundaryRectangle();

        setScale(4.0f);

        setMaxSpeed(900);

    }
    float attackCounter;
    @Override
    public void act(float dt) {
        super.act(dt);
        attackCounter+=dt;
        if(attackCounter>attackDelay){
            shoot();
            attackCounter=0;
        }


        if(other==null)
          getplayer();
        if(wasHit){
            badCounter+=dt;
            if(badCounter>damageDelay){
                wasHit=false;
                badCounter=0.0f;
            }

        }
        setAcceleration(900);
    //    accelerateAtAngle(270);
     //   applyPhysics(dt);
    }
    public void takeDamage(int damageTaken){
        if(damageTaken>0&&wasHit==false) {
            health -= damageTaken;
            wasHit=true;
        }
        if (health<0) {
            health = 0;
            other.setIceUnlocked();
            kill();

        }
    }

    public void shoot()
    {
            bullet = new BulletL();
            bullet.setSize(16,16 );
            bullet.setPosition(this.getX()-this.getWidth()*2,this.getY()-this.getHeight()/2 );
            this.getStage().addActor(bullet);


    }

    public void getplayer() {
        if(this != null && this.getStage() != null) {
            if (this.getStage().getRoot().findActor("Player") != null) {
                other = this.getStage().getRoot().findActor("Player");
            }
        }
    }

    public void kill(){
        this.remove();
        this.setX(1000000.0f);
    }


}
