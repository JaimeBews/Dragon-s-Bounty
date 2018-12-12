package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by markapptist on 2018-11-12.
 */

public class PowerRanger extends ActorBeta {

    Animation<TextureRegion> idle;
    Animation<TextureRegion> walkLeft;
    Animation<TextureRegion> walkRight;
    Animation<TextureRegion> walkUp;
    Animation<TextureRegion> walkDown;
    Animation<TextureRegion> biteAttackLeft;
    Animation<TextureRegion> biteAttackRight;
    Animation<TextureRegion> breathInit;
    Animation<TextureRegion> breathHold;
    Animation<TextureRegion> breathEnd;

    Animation<TextureRegion> walk;

    PowerRanger() {

    }

    @Override
    public void act(float dt) {
        super.act(dt);

     //   setAcceleration(900);
     //   accelerateAtAngle(270);
      //  applyPhysics(dt);
    }
}
