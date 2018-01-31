package com.thom9521.mario;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class PlayerControl extends Control {

    private PhysicsComponent physics;

    private AnimatedTexture texture;

    private AnimationChannel animIdle, animWalk;

    public PlayerControl(){

        animIdle = new AnimationChannel("dude.png", 4, 32, 42, Duration.seconds(1),1,1);
        animWalk = new AnimationChannel("dude.png", 4, 32, 42, Duration.seconds(1),0,3);

        texture = new AnimatedTexture(animIdle);

    }

    @Override
    public void onAdded(Entity entity) {
        entity.setView(texture);
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {

        if(isMoving()){
            texture.setAnimationChannel(animWalk);
        } else{
            texture.setAnimationChannel(animIdle);
        }
    }

    private boolean isMoving(){
        return FXGLMath.abs(physics.getVelocityX()) > 0;

    }

    public void left(){
        getEntity().setScaleX(-1);
        physics.setVelocityX(-150);
    }

    public void right(){
        getEntity().setScaleX(1);
        physics.setVelocityX(150);
    }

    public void jump(){
        physics.setVelocityY(-350);
    }
}
