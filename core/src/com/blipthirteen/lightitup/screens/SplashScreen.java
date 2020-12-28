package com.blipthirteen.lightitup.screens;


import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.blipthirteen.lightitup.adhandler.AdHandler;
import com.blipthirteen.lightitup.misc.Constants;
import com.blipthirteen.lightitup.tween.ActorAccessor;


/**
 * Created by rohit on 25-12-2016.
 */

public class SplashScreen implements Screen {

    AdHandler handler;

    SpriteBatch batch;
    FitViewport fitViewport;
    TweenManager tm;

    Stage stage;

    Image logo;
    Preferences prefs;

    public SplashScreen(AdHandler handler){
        this.handler = handler;
    }

    private void  initPrefs(){
        prefs = Gdx.app.getPreferences(Constants.PREF_NAME);
        if(!prefs.contains("default")){
            prefs.putString("default","prefs");
            prefs.putInteger("completed", 0);
            prefs.flush();
        }
    }

    public void show() {
        handler.showAds(false);
        initPrefs();
        stage = new Stage();
        fitViewport = new FitViewport(1080,1920);
        stage.setViewport(fitViewport);
        logo = new Image(new Texture("logo.png"));
        logo.setPosition(540 - logo.getWidth()/2, 1920/2 - logo.getHeight()/2);
        stage.addActor(logo);

        batch = new SpriteBatch();

        tm = new TweenManager();
        Tween.registerAccessor(Image.class,new ActorAccessor());

        tween();

    }
    private void tween(){
        Tween.set(logo, ActorAccessor.ALPHA).target(0).start(tm);
        Tween.to(logo, ActorAccessor.ALPHA, 1f).target(1).repeatYoyo(1, .5f).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int arg0, BaseTween<?> arg1) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen(handler));
            }
        }).start(tm);

        tm.update(Float.MIN_VALUE);
    }

    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor((float)33/255,(float)38/255,(float)58/255,1);
        batch.setProjectionMatrix(fitViewport.getCamera().combined);
        fitViewport.apply(true);
        batch.begin();
        batch.end();

        stage.act();
        stage.draw();
        tm.update(delta);
    }

    public void resize(int width, int height) {
        fitViewport.update(width,height, true);
    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {

    }
}