package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Ver on 1/24/2017.
 */

public class Projectile {
    private Rectangle projectile;
    private int xSpeed;
    private int ySpeed;
    private int size;

    public Projectile() {
        projectile = new Rectangle();
        int yOrX = MathUtils.random(0,3);
        if(yOrX == 0){
            projectile.x = MathUtils.random(0, 800);
            projectile.y = 0;
        } else if (yOrX == 1){
            projectile.x = MathUtils.random(0, 800);
            projectile.y = 480;
        } else if (yOrX == 2){
            projectile.x = 0;
            projectile.y = MathUtils.random(0, 480);
        } else {
            projectile.x = 800;
            projectile.y = MathUtils.random(0, 480);
        }
        size = VariableWrapper.size;
        projectile.width = size;
        projectile.height = size;
        xSpeed = VariableWrapper.speed;
        ySpeed = VariableWrapper.speed;
    }

    public Rectangle getProjectile(){
        return projectile;
    }

    public void setX(float deltaTime){
        projectile.x += xSpeed * deltaTime;
        if(projectile.x < 0){
            xSpeed = -xSpeed;
            projectile.x = 0;
        } else if (projectile.x + size > 800){
            xSpeed = -xSpeed;
            projectile.x = 800 - size;
        }
    }

    public void setY(float deltaTime){
        projectile.y += ySpeed * deltaTime;
        if(projectile.y < 0){
            ySpeed = -ySpeed;
            projectile.y = 0;
        } else if (projectile.y + size > 480){
            ySpeed = -ySpeed;
            projectile.y = 480 - size;
        }
    }
}
