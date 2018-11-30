package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.awt.Label;

public class EnemyBase extends ActorBeta {

    Animation<TextureRegion> idle;
    Animation<TextureRegion> walk;
    boolean move  = true;
    ActorBeta other;
    EnemyBase(){

    }

    @Override
    public void act(float dt) {
        super.act(dt);
        goToPlayer(other);
        //   setAcceleration(900);
        //   accelerateAtAngle(270);
        //  applyPhysics(dt);
    }

    public void goToPlayer(ActorBeta other)
    {
        Vector2 startPos = new Vector2(this.getX(),this.getY());
        Vector2 endPos = new Vector2 (other.getX(), other.getY());
        float speed = 2;
        float dist;
        dist = Vector2.dst(endPos.x,endPos.y,startPos.x,startPos.y);
        Vector2 direction = endPos.sub(startPos);
        direction.nor();
        System.out.println(dist);

        if(dist < 500) {
            if (move == true) {
                if (this.overlaps(other) == false) {
                    // System.out.println("not touching");
                    this.setPosition(this.getX() + (direction.x * speed), this.getY() + (direction.y * speed));
                }

                if (this.overlaps(other) == true) {

                    // System.out.println("Imma hitting you");
                }

            }
        }

    }
    public void getplayer(ActorBeta otherd) {
        other = otherd;
    }
}

