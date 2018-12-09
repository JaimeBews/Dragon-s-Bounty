package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;

import java.awt.Label;

public class EnemyBase extends ActorBeta {

    Animation<TextureRegion> idle;
    Animation<TextureRegion> walk;
    boolean move  = true;
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
        float speed = 2;
        float dist;
        dist = Vector2.dst(endPos.x,endPos.y,startPos.x,startPos.y);
        Vector2 direction = endPos.sub(startPos);
        direction.nor();
        System.out.println(dist);
        attackDelay = attackDelay -dt;

        if(dist < 500) {
            if (move == true) {
                if (this.overlaps(other) == false) {
                    // System.out.println("not touching");
                    this.setPosition(this.getX() + (direction.x * speed), this.getY() + (direction.y * speed));
                }

                if (this.overlaps(other) == true && attackDelay < 0.0f) {

                    // System.out.println("Imma hitting you");
                 other.takeDamage(1);
                }

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

