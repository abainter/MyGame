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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by Ver on 1/27/2017.
 */

public class ModeScreen implements Screen {

    MyGame game;

    Stage stage;
    SpriteBatch batch;
    Skin skin;

    public ModeScreen(MyGame gam){
        game = gam;
        create();
    }

    public void create() {
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

        final TextArea textArea = new TextArea("Select a mode", skin);
        textArea.setPosition(200, 500);
        textArea.setSize(800,50);
        stage.addActor(textArea);

        final TextButton mainMenuButton = new TextButton("MAIN MENU", textButtonStyle);
        mainMenuButton.setPosition(200, 200);
        stage.addActor(mainMenuButton);

        final TextButton speedButton = new TextButton("SPEED", textButtonStyle);
        speedButton.setPosition(400, 200);
        stage.addActor(speedButton);
        speedButton.setChecked(true);

        final TextButton quantityButton = new TextButton("QUANTITY", textButtonStyle);
        quantityButton.setPosition(600, 200);
        stage.addActor(quantityButton);

        final TextButton sizeButton = new TextButton("SIZE", textButtonStyle);
        sizeButton.setPosition(800, 200);
        stage.addActor(sizeButton);

        final TextButton unlimitedButton = new TextButton("UNLIMITED", textButtonStyle);
        unlimitedButton.setPosition(1000, 200);
        stage.addActor(unlimitedButton);

        ButtonGroup<TextButton> buttonGroup = new ButtonGroup<TextButton>(mainMenuButton, speedButton, quantityButton, sizeButton, unlimitedButton);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(1);
        buttonGroup.setUncheckLast(true);


        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainMenuButton.setText("Starting new game");
                game.setScreen(new MainMenuScreen(game));
            }
        });

        speedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                VariableWrapper.mode="SPEED";
                textArea.setText("New projectiles will spawn with increased velocity");
            }
        });

        quantityButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                VariableWrapper.mode = "QUANTITY";
                textArea.setText("New projectiles will spawn in an increasing amount");
            }
        });

        sizeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                VariableWrapper.mode = "SIZE";
                textArea.setText("New projectiles will spawn 110% bigger");
            }
        });

        unlimitedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                VariableWrapper.mode = "UNLIMITED";
                textArea.setText("New projectiles will spawn every second");
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
        stage.dispose();
        skin.dispose();
    }
}
