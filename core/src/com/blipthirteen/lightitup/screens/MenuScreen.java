package com.blipthirteen.lightitup.screens;




import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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

public class MenuScreen implements Screen{

    AdHandler handler;

    // Scene 2D
    Stage stage;
    ImageButton playButton, moreGamesButton, helpButton;
    Image title;
    Skin uiSkin;
    TextureAtlas buttonatlas;

    TweenManager tm;

    SpriteBatch batch;
    FitViewport fitViewport;
    StretchViewport stretchViewport;
    Timer t;

    float fadeInTime, fadeOutTime;
    Preferences prefs;

    public MenuScreen(AdHandler handler){
        this.handler = handler;
    }

    private void initScene2D(){
        uiSkin = new Skin();
        buttonatlas = new TextureAtlas("menuPack.pack");
        uiSkin.addRegions(buttonatlas);

        Gdx.input.setInputProcessor(stage);

        playButton = new ImageButton(uiSkin.getDrawable("playButton"),uiSkin.getDrawable("playButtonDown"));
        moreGamesButton = new ImageButton(uiSkin.getDrawable("moreGamesButton"),uiSkin.getDrawable("moreGamesButtonDown"));
        helpButton = new ImageButton(uiSkin.getDrawable("helpButton"),uiSkin.getDrawable("helpButtonDown"));
        title = new Image(uiSkin.getDrawable("title"));

        addToScene();
        setPositions();
    }

    private void addToScene(){
        stage.addActor(playButton);
        stage.addActor(moreGamesButton);
        stage.addActor(helpButton);
        stage.addActor(title);
    }




    public void show() {
        handler.showAds(false);
        prefs =  Gdx.app.getPreferences(Constants.PREF_NAME);
        fadeInTime = 0.5f;
        fadeOutTime = 0.5f;

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
            Gdx.app.exit();
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

    private void setPositions() {
        title.setPosition(1080/2 - title.getWidth()/2, 1920/2 + 500 - title.getHeight()/2);
        playButton.setPosition(1080/2 - playButton.getWidth()/2, 1920/2 + 50 - playButton.getHeight()/2);
        helpButton.setPosition(1080/2 + 250 - helpButton.getWidth()/2, 1920/2 - 200 - helpButton.getHeight()/2);
        moreGamesButton.setPosition(1080/2 - moreGamesButton.getWidth()/2, 420);
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
        Gdx.input.setCatchBackKey(true);
        playButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                fadeOut();
                Timer.schedule(new Task(){
                    @Override
                    public void run() {
                        if(prefs.getInteger("completed") == 0){
                            ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(0, handler));
                        }else{
                            ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelSelectScreen(handler));
                        }

                    }
                }, fadeOutTime);
                return true;
            }
        });

        moreGamesButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Timer.schedule(new Task(){
                    @Override
                    public void run() {
                        Gdx.net.openURI("https://play.google.com/store/apps/developer?id=blipthirteen");
                    }
                }, 0.05f);
                return true;
            }
        });

        helpButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                fadeOut();
                Timer.schedule(new Task(){
                    @Override
                    public void run() {
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new HelpScreen(handler));
                    }
                }, fadeOutTime);
                return true;
            }
        });
    }
}