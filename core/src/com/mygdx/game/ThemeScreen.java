package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by Ver on 1/26/2017.
 */

public class ThemeScreen implements Screen {

    MyGame game;

    Skin skin;
    Stage stage;
    SpriteBatch batch;


    public ThemeScreen(MyGame gam){
        game = gam;
        create();
    }

    public void create(){
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        BitmapFont bfont = new BitmapFont();
        bfont.getData().setScale(1);
        skin.add("default", bfont);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        BitmapFont largeFont = new BitmapFont();
        largeFont.getData().setScale(2);

        skin.add("large", largeFont);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = skin.getFont("large");
        textFieldStyle.fontColor = Color.WHITE;

        skin.add("default", textFieldStyle);

        Image spaceshipImage = new Image(new Texture(Gdx.files.internal("spaceship.png")));
        spaceshipImage.setPosition(400,400);
        spaceshipImage.setSize(100,100);
        stage.addActor(spaceshipImage);

        Image asteroidImage = new Image(new Texture(Gdx.files.internal("asteroid.png")));
        asteroidImage.setPosition(400,500);
        asteroidImage.setSize(100,100);
        stage.addActor(asteroidImage);

        Image fishImage = new Image(new Texture(Gdx.files.internal("fish.png")));
        fishImage.setPosition(600,400);
        fishImage.setSize(100,100);
        stage.addActor(fishImage);

        Image sharkImage = new Image(new Texture(Gdx.files.internal("shark.png")));
        sharkImage.setPosition(600,500);
        sharkImage.setSize(100,100);
        stage.addActor(sharkImage);

        Image monkeyImage = new Image(new Texture(Gdx.files.internal("monkey.png")));
        monkeyImage.setPosition(800,400);
        monkeyImage.setSize(100,100);
        stage.addActor(monkeyImage);

        Image lionImage = new Image(new Texture(Gdx.files.internal("lion.png")));
        lionImage.setPosition(800,500);
        lionImage.setSize(100,100);
        stage.addActor(lionImage);

        Image personImage = new Image(new Texture(Gdx.files.internal("person.png")));
        personImage.setPosition(1000,400);
        personImage.setSize(100,100);
        stage.addActor(personImage);

        Image carImage = new Image(new Texture(Gdx.files.internal("car.png")));
        carImage.setPosition(1000,500);
        carImage.setSize(100,100);
        stage.addActor(carImage);

        final TextArea projectileTextArea = new TextArea("Projectile", skin);
        projectileTextArea.setPosition(200, 500);
        stage.addActor(projectileTextArea);

        final TextArea targetTextArea = new TextArea("Target", skin);
        targetTextArea.setPosition(200, 400);
        stage.addActor(targetTextArea);

        final TextButton mainMenuButton = new TextButton("MAIN MENU", textButtonStyle);
        mainMenuButton.setPosition(200,200);
        stage.addActor(mainMenuButton);

        final TextButton spaceButton = new TextButton("SPACE", textButtonStyle);
        spaceButton.setPosition(400, 200);
        spaceButton.setChecked(true);
        stage.addActor(spaceButton);

        final TextButton aquaticButton = new TextButton("AQUATIC", textButtonStyle);
        aquaticButton.setPosition(600,200);
        stage.addActor(aquaticButton);

        final TextButton safariButton = new TextButton("SAFARI", textButtonStyle);
        safariButton.setPosition(800,200);
        stage.addActor(safariButton);

        final TextButton cityButton = new TextButton("CITY", textButtonStyle);
        cityButton.setPosition(1000,200);
        stage.addActor(cityButton);

        ButtonGroup<TextButton> buttonGroup = new ButtonGroup<TextButton>(mainMenuButton, spaceButton, aquaticButton, safariButton, cityButton);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(1);
        buttonGroup.setUncheckLast(true);


        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        spaceButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.projectileImage=new Texture(Gdx.files.internal("asteroid.png"));
                Settings.targetImage=new Texture(Gdx.files.internal("spaceship.png"));
                Settings.background=new Texture(Gdx.files.internal("space.jpeg"));
            }
        });

        aquaticButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.projectileImage=new Texture(Gdx.files.internal("shark.png"));
                Settings.targetImage=new Texture(Gdx.files.internal("fish.png"));
                Settings.background=new Texture(Gdx.files.internal("underwater.jpg"));
            }
        });

        safariButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.projectileImage=new Texture(Gdx.files.internal("lion.png"));
                Settings.targetImage=new Texture(Gdx.files.internal("monkey.png"));
                Settings.background=new Texture(Gdx.files.internal("safari.jpg"));
            }
        });

        cityButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.projectileImage=new Texture(Gdx.files.internal("car.png"));
                Settings.targetImage=new Texture(Gdx.files.internal("person.png"));
                Settings.background=new Texture(Gdx.files.internal("city.png"));
            }
        });




    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }
}
