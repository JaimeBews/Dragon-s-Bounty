package com.mygdx.game;

/**
 * Created by markapptist on 2018-11-12.
 */

public class Cowboy_Boss extends PowerRanger {
    float speed;
    float health =3;
    float badCounter=0.0f;
    float damageDelay=2.0f;

    BlueRanger other;
    Cowboy_Boss() {
    speed=5;
    this.setName("CowBoyBoss");
        idle = loadAnimationFromSheet("sprites/Macs/pirate side.png",1,4,0.3f,true);
     //  idle = loadAnimationFromFiles(idleString, 0.5f, true);

        this.setBoundaryRectangle();

        setScale(2.0f);

        setMaxSpeed(900);

    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if(other ==null){
            getplayer();
        }
        if(wasHit){
            badCounter+=dt;
            if(badCounter>damageDelay){
                wasHit=false;
                badCounter=0.0f;
            }

        }
        if(this.overlaps(other)){
            other.takeDamage(1);
        }
        setAcceleration(900);
    //    accelerateAtAngle(270);
     //   applyPhysics(dt);
    }
    boolean wasHit=false;
    public void takeDamage(int damageTaken){
        if(damageTaken>0&&wasHit==false) {
            health -= damageTaken;
            wasHit=true;
        }
        if (health<0) {
            health = 0;
            other.setFireUnlocked();
            other.healToFull();
            kill();

        }
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
