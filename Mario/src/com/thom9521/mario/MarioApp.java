package com.thom9521.mario;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;


public class MarioApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(15*70);
        settings.setHeight(10*70);
    }

    private Entity player;

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
            player.getControl(PlayerControl.class).left();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).right();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Jump") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).jump();
            }
        }, KeyCode.W);
    }

    @Override
    protected void initGame() {
        getGameWorld().setLevelFromMap("mario.json");

        player = getGameWorld().spawn("player",50,50);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(MarioType.PLAYER, MarioType.DOOR) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door) {
            getDisplay().showMessageBox("Level Complete!", () -> {
                System.out.println("Dialog Closed!");
            });
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
