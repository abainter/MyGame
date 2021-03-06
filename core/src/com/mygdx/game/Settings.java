package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Ver on 1/27/2017.
 */

public class Settings {

    static Texture projectileImage;
    static Texture targetImage;
    static String mode;
    static int speed;
    static int quantity;
    static int size;
    static Texture background;

    public Settings(){
        projectileImage = new Texture(Gdx.files.internal("asteroid.png"));
        targetImage = new Texture(Gdx.files.internal("spaceship.png"));
        mode = "SPEED";
        speed = 200;
        quantity = 1;
        size = 64;
        background = new Texture(Gdx.files.internal("space.jpeg"));
    }

    public static void makeDefault(){
        speed = 200;
        quantity = 1;
        size = 64;
    }


}
