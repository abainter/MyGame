package com.mygdx.game;

/**
 * Created by Ver on 12/6/2016.
 */

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
    final MyGame game;

    Texture projectileImage;
    Texture targetImage;
    Sound collisionSound;
    Music music;
    OrthographicCamera camera;
    Rectangle target;
    Array<Projectile> projectiles;
    long lastSpawnTime;
    long startTime;
    int level;
    int lives;
    Texture background;


    public GameScreen(final MyGame gam) {
        this.game = gam;

        projectileImage = Settings.projectileImage;
        targetImage = Settings.targetImage;

        collisionSound = Gdx.audio.newSound(Gdx.files.internal("baddrop.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        music.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        target = new Rectangle();
        target.x = 800 / 2 - 64 / 2; // center the target horizontally
        target.y = 20; // bottom left corner of the target is 20 pixels above
        // the bottom screen edge
        target.width = 64;
        target.height = 64;

        // create the projectiles array and spawn the first raindrop
        projectiles = new Array<Projectile>();
        spawnRaindrop();


        startTime = System.currentTimeMillis();

        level = 1;

        lives = 3;

        background = Settings.background;
    }

    private void spawnRaindrop() {
        Projectile projectile = new Projectile();
        projectiles.add(projectile);
        lastSpawnTime = TimeUtils.nanoTime();
    }


    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        long timeNow = System.currentTimeMillis();

        String countdown = Long.toString(10-(timeNow - startTime)/1000);

        // begin a new batch and draw the target and
        // all drops
        game.batch.begin();
        game.batch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, "Time remaining: " + countdown, 0 , 480);
        game.font.draw(game.batch, "Current level: "+level, 0, 450);
        game.font.draw(game.batch, "Lives remaining: "+lives, 0, 420);
        game.batch.draw(targetImage, target.x, target.y, target.width, target.height);
        for (Projectile projectile : projectiles) {
            game.batch.draw(projectileImage, projectile.getProjectile().x, projectile.getProjectile().y);
            projectile.getProjectile().width = Settings.size;
            projectile.getProjectile().height = Settings.size;
        }
        game.batch.end();

        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            target.x = touchPos.x - 64 / 2;
            target.y = touchPos.y - 64 / 2 ;
        }

        // check if we need to create a new raindrop
        if (Settings.mode.equals( "UNLIMITED") && TimeUtils.nanoTime() - lastSpawnTime > 1000000000) {
            spawnRaindrop();
        } else if(10-(timeNow - startTime)/1000<= 0){
            level++;
            startTime = System.currentTimeMillis();
            if(Settings.mode.equals("SPEED")){
                Settings.speed += 50;
                spawnRaindrop();
            } else if (Settings.mode.equals("QUANTITY")){
                Settings.quantity += 1;
                for(int i = 0; i < Settings.quantity; i++){
                    spawnRaindrop();
                }
            } else {
                Settings.size *= 1.1;
                spawnRaindrop();
            }
        }

        // move the projectiles, remove any that are beneath the bottom edge of
        // the screen or that hit the target. In the later case we increase the
        // value our drops counter and add a sound effect.
        Iterator<Projectile> iter = projectiles.iterator();
        while (iter.hasNext()) {
            Projectile projectile = iter.next();
            float deltaTime = Gdx.graphics.getDeltaTime();
            projectile.setX(deltaTime);
            projectile.setY(deltaTime);
            if (projectile.getProjectile().overlaps(target)) {
                //you suck
                collisionSound.play();
                lives--;
                iter.remove();
            }
        }
        if(lives <= 0){
            game.setScreen(new EndScreen(game));
            Settings.makeDefault();
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
     //   rainMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        collisionSound.dispose();
        music.dispose();
    }

}