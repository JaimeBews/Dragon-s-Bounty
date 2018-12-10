package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;

import java.awt.Label;

public class EnemyBase extends ActorBeta {

    Animation<TextureRegion> idle;
    Animation<TextureRegion> walkLeft;
    Animation<TextureRegion> walkRight;
    Animation<TextureRegion> walkUp;
    Animation<TextureRegion> walkDown;
    Animation<TextureRegion> attackLeft;
    Animation<TextureRegion> attackRight;
    Animation<TextureRegion> attackUp;
    Animation<TextureRegion> attackDown;
    boolean move  = true;
    boolean attack = false;
    int movedir =0;
    BlueRanger other;
    float attackDelay = 2.0f;
    EnemyBase(){
        setName("Enemy");
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        getplayer();
        goToPlayer(other, dt);
        //   setAcceleration(900);
        //   accelerateAtAngle(270);
        //  applyPhysics(dt);
    }

    public void goToPlayer(BlueRanger other, float dt)
    {
        Vector2 startPos = new Vector2(this.getX(),this.getY());
        Vector2 endPos = new Vector2 (other.getX(), other.getY());
        float speed = 1.5f;
        float dist;
        dist = Vector2.dst(endPos.x,endPos.y,startPos.x,startPos.y);
        Vector2 direction = endPos.sub(startPos);
        direction.nor();
        System.out.println(dist);
        System.out.println(movedir);
        if(dist < 500) {
            attackDelay = attackDelay -dt;
            if (move == true && dt!=0) {

                if (this.overlaps(other) == false) {
                    // System.out.println("not touching");
                    this.setPosition(this.getX() + (direction.x * speed), this.getY() + (direction.y * speed));

                    if(endPos.x == 0.0f && endPos.y == 0.0f){
                        this.movedir =0;
                    }

                    if (endPos.x < 0.0f)
                    {
                        this.movedir =1;
                    }else if (endPos.x > 0.0f)
                    {
                        this.movedir =2;
                    }
                    else if (endPos.y > 0.0f)
                    {
                        this.movedir =3;
                    }else if (endPos.y < 0.0f)
                    {
                        this.movedir =4;
                    }
                }
            }
            if (this.overlaps(other) == true && attackDelay < 0.0f) {
                this.attack =true;
                this.move = false;
                System.out.println("Imma hitting you");
                other.takeDamage(1);
                attackDelay = 5.0f;
            }
        }

    }
    public void getplayer() {
        if(this != null && this.getStage() != null) {
            if (this.getStage().getRoot().findActor("Player") != null) {
                other = this.getStage().getRoot().findActor("Player");
                Gdx.app.log("test", "" + other.getX());
            }
        } else
        {
            Gdx.app.log("test", "enemy is null" );
        }
    }
    public void kill(){this.remove();}
}

