package com.blipthirteen.lightitup.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blipthirteen.lightitup.adhandler.AdHandler;
import com.blipthirteen.lightitup.misc.Constants;
import com.blipthirteen.lightitup.tween.ActorAccessor;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by rohit on 25-12-2016.
 */

public class LevelSelectScreen implements Screen{

    AdHandler handler;
    // Scene 2D
    Stage stage;
    Skin uiSkin;
    Preferences prefs;
    // Tween
    TweenManager tm;
    SpriteBatch batch;
    FitViewport fitViewport;
    StretchViewport stretchViewport;
    Timer t;
    BitmapFont font;
    float fadeInTime, fadeOutTime;

    public LevelSelectScreen(AdHandler handler){
        this.handler = handler;
    }

    private void generateFont() {
        font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("BRLNSB.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    private void initScene2D(){
        generateFont();
        Skin skin = new Skin();
        Color gold = new Color(0.96f, 0.91f, 0.30f, 1);
        font.setColor(gold);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, gold);

        Label title = new Label("SELECT", labelStyle);
        final Table scrollTable = new Table();
        scrollTable.add(title).padTop(150);
        scrollTable.row();

        final int size = prefs.getInteger("completed");
        Texture frame = new Texture("frame.png");

        Label levelLabel = new Label("Resume", labelStyle);
        levelLabel.setAlignment(Align.center);

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(frame));
        ImageButton.ImageButtonStyle butttonStyle = new ImageButton.ImageButtonStyle();
        butttonStyle.up = skin.newDrawable(drawable);
        butttonStyle.down = skin.newDrawable(drawable,new Color(Color.WHITE));
        ImageButton ib = new ImageButton(butttonStyle);
        Stack stack = new Stack();
        if(size <= Constants.MAX_LEVEL)
        {
            stack.add(levelLabel);
            stack.add(ib);

            scrollTable.add(stack).padTop(150);
            scrollTable.row();
            ib.addListener(new ActorGestureListener() {
                               public void touchUp(InputEvent event, float x, float y, int count, int button) {
                                   fadeOut();
                                   Timer.schedule(new Timer.Task() {
                                       @Override
                                       public void run() {
                                           ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(size, handler));
                                       }
                                   }, fadeOutTime);
                               }
                           }
            );
            scrollTable.row();
        }

        for(int i =0 ; i< size; i++){
            final int k = i;
            levelLabel = new Label("Level " + (i+1), labelStyle);
            levelLabel.setAlignment(Align.center);

            ib = new ImageButton(butttonStyle);
            stack = new Stack();
            stack.add(levelLabel);
            stack.add(ib);
            scrollTable.add(stack).padTop(150);
            scrollTable.row();
            ib.addListener(new ActorGestureListener() {
                               public void touchUp (InputEvent event, float x, float y, int count, int button) {
                                   fadeOut();
                                   Timer.schedule(new Timer.Task() {
                                       @Override
                                       public void run() {
                                           ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(k, handler));
                                       }
                                   }, fadeOutTime);
                               }
                           }
            );

            scrollTable.row();
        }
        scrollTable.add().padBottom(100);

        final ScrollPane scroller = new ScrollPane(scrollTable);
        final Table table = new Table();
        table.setFillParent(true);
        table.add(scroller).fill().expand();
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    public void show() {
        handler.showAds(false);
        prefs = Gdx.app.getPreferences(Constants.PREF_NAME);
        fadeInTime = 0.5f;
        fadeOutTime = 0.5f;
        Gdx.input.setCatchBackKey(true);
        stage = new Stage();
        fitViewport = new FitViewport(1080,1920);
        stretchViewport = new StretchViewport(1080,1920);
        stage.setViewport(fitViewport);
        initScene2D();

        t = new Timer();
        t.scheduleTask(new Task() {
            @Override
            public void run() {
                setButtonListeners();
            }
        }, 0.5f);

        tm = new TweenManager();
        fadeIn();
    }

    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor((float)33/255,(float)38/255,(float)58/255,1);

        fitViewport.apply(true);

        stage.act();
        stage.draw();

        tm.update(delta);

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(handler));
        }
    }

    public void resize(int width, int height) {
        stretchViewport.update(width,height,true);
        fitViewport.update(width,height,true);
        stage.getViewport().update(width, height, true);
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void dispose() {
        stage.dispose();
        batch.dispose();
        uiSkin.dispose();
    }

    private void fadeIn(){
        Array<Actor> actorList = stage.getActors();
        Tween.registerAccessor(Actor.class,new ActorAccessor());
        for (Actor a:actorList){
            Tween.set(a, ActorAccessor.ALPHA).target(0).start(tm);
            Tween.to(a, ActorAccessor.ALPHA, fadeInTime).target(1).start(tm);
        }
        tm.update(Float.MIN_VALUE);
    }

    private void fadeOut(){
        Array<Actor> actorList = stage.getActors();
        Tween.registerAccessor(Actor.class,new ActorAccessor());
        for (Actor a:actorList){
            Tween.set(a, ActorAccessor.ALPHA).target(1).start(tm);
            Tween.to(a, ActorAccessor.ALPHA, fadeOutTime).target(0).start(tm);
        }
        tm.update(Float.MIN_VALUE);
    }

    private void setButtonListeners() {

    }
}