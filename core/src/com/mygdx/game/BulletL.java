package com.mygdx.game;

public class BulletL extends PowerRanger {
    float speed;
    BlueRanger other;
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
        if(other==null)
            getplayer();
        setAcceleration(900);
        this.moveBy(-2,0);
        if(this.overlaps(other)&&other!=null) {
            other.takeDamage(1);
            this.remove();
            this.setX(100000);
        }
    }
    public void getplayer() {
        if(this != null && this.getStage() != null) {
            if (this.getStage().getRoot().findActor("Player") != null) {
                other = this.getStage().getRoot().findActor("Player");
            }
        }
    }


}
