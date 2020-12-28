package com.blipthirteen.lightitup.screens;

/**
 * Created by HP on 10/19/2017.
 */



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.blipthirteen.lightitup.adhandler.AdHandler;
import com.blipthirteen.lightitup.tween.ActorAccessor;


import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by HP on 7/9/2017.
 */

public class HelpScreen implements Screen {

    AdHandler handler;
    SpriteBatch batch;
    FitViewport fitViewport;
    TweenManager tm;
    Label bt;
    BitmapFont font;
    Stage stage;
    float fadeOutTime, fadeInTime;

    public HelpScreen(AdHandler handler){
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

    public void show() {
        handler.showAds(false);
        fadeInTime = 0.5f;
        fadeOutTime = 0.5f;

        generateFont();
        stage = new Stage();
        fitViewport = new FitViewport(1080,1920);
        stage.setViewport(fitViewport);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        final Label.LabelStyle btLabelStyle = new Label.LabelStyle(font, new Color(0.96f, 0.91f, 0.30f, 1));
        bt = new Label("Touch the parts to rotate\n" +
                "them around their centre.\n\n" +
                "They will light up \n" +
                "when the circuit is complete.\n" +
                "", btLabelStyle);
        bt.setAlignment(Align.center);
        bt.setPosition(540 - bt.getWidth()/2, 1920/2 - bt.getHeight()/2);
        bt.setVisible(true);

        Texture frame = new Texture("frame.png");

        Skin skin = new Skin();
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(frame));
        ImageButton.ImageButtonStyle butttonStyle = new ImageButton.ImageButtonStyle();
        butttonStyle.up = skin.newDrawable(drawable);
        butttonStyle.down = skin.newDrawable(drawable, new Color(Color.WHITE));
        ImageButton ib = new ImageButton(butttonStyle);
        Stack stack = new Stack();

        Label levelLabel = null;
        levelLabel = new Label("Back", btLabelStyle);


        levelLabel.setAlignment(Align.center);

        stack.add(levelLabel);
        stack.add(ib);
        ib.addListener(new ActorGestureListener() {
                           public void touchUp(InputEvent event, float x, float y, int count, int button) {
                               fadeOut();
                               Timer.schedule(new Timer.Task() {
                                   @Override
                                   public void run() {
                                       ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(handler));

                                   }
                               }, fadeOutTime);
                           }
                       }
        );
        stack.setSize(frame.getWidth(), frame.getHeight());
        stack.setPosition(540 - stack.getWidth()/2, 300);
        stage.addActor(stack);
         stage.addActor(bt);


        batch = new SpriteBatch();

        tm = new TweenManager();
        fadeIn();

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


        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(handler));
        }
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
        font.dispose();
        batch.dispose();
    }
}