package com.blipthirteen.lightitup.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blipthirteen.lightitup.adhandler.AdHandler;
import com.blipthirteen.lightitup.misc.Constants;
import com.blipthirteen.lightitup.misc.LevelData;
import com.blipthirteen.lightitup.misc.Tile;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 10/15/2017.
 */

public class GameScreen implements Screen, InputProcessor {

    AdHandler handler;

    HashMap<String, String> levelMap;
    HashMap<String, Texture> textureMap;
    HashMap<String, Float> rotationMap;
    HashMap<String, Tile> tileMap;

    StretchViewport stretchViewport;
    FitViewport fitViewport;
    Stage stage;
    SpriteBatch spriteBatch;
    BitmapFont font;


    boolean complete;
    Preferences prefs;
    int level;

    public GameScreen(int level, AdHandler handler) {
        this.handler = handler;
        this.level = level;
    }

    @Override
    public void show() {
        handler.showAds(true);
        prefs = Gdx.app.getPreferences(Constants.PREF_NAME);
        complete = false;
        spriteBatch = new SpriteBatch();
        initMap();
        initUI();
        loadAssets();
        Gdx.input.setInputProcessor(this);
        createAndPlaceButtons();
    }

    private void initMap() {
        levelMap = LevelData.levels.get(this.level);
    }

    private void initUI() {
        fitViewport = new FitViewport(1080, 1920);
        stretchViewport = new StretchViewport(1080, 1920);
        stage = new Stage();
        stage.setViewport(fitViewport);
    }

    private void loadAssets() {
        textureMap = new HashMap<String, Texture>();
        textureMap.put("1", new Texture("1.png"));
        textureMap.put("2", new Texture("2.png"));
        textureMap.put("3", new Texture("3.png"));
        textureMap.put("4", new Texture("4.png"));
    }

    private void createAndPlaceButtons() {
        tileMap = new HashMap<String, Tile>();

        for (Map.Entry<String, String> entry : levelMap.entrySet()) {
            Vector2 position = getPosition(entry.getKey());
            String[] sa = entry.getValue().split(",");

            int randomRotation = MathUtils.random(0, 3);
            Gdx.app.log(randomRotation + " ", "" + randomRotation * 90);
            Tile tile = new Tile(entry.getKey(), sa[0], textureMap.get(sa[0]), position, randomRotation * 90);
            tileMap.put(entry.getKey(), tile);
        }
        if (checkForCompletion()) {
            createAndPlaceButtons();
        }
    }

    private Vector2 getPosition(String s) {
        String[] sa = s.split(",");
        int xPos = Integer.parseInt(sa[0]);
        int yPos = Integer.parseInt(sa[1]);
        return new Vector2(120 + xPos * 120, 1080 - yPos * 120);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor((float) 33 / 255, (float) 38 / 255, (float) 58 / 255, 1);

        spriteBatch.setProjectionMatrix(fitViewport.getCamera().combined);
        spriteBatch.begin();

        for (Map.Entry<String, Tile> entry : tileMap.entrySet()) {
            Tile tile = entry.getValue();
            tile.draw(spriteBatch);
        }

        spriteBatch.end();

        fitViewport.getCamera().update();

        fitViewport.apply();
        stage.act();
        stage.draw();

        if (!complete) {
            if (checkForCompletion()) {
                complete = true;
                textureMap.put("1", new Texture("5.png"));
                textureMap.put("2", new Texture("6.png"));
                textureMap.put("3", new Texture("7.png"));
                textureMap.put("4", new Texture("8.png"));
                for (Map.Entry<String, Tile> entry : tileMap.entrySet()) {
                    Tile tile = entry.getValue();
                    tile.getSprite().setTexture(textureMap.get(tile.getId()));
                }
                if (prefs.getInteger("completed") <= this.level) {
                    prefs.putInteger("completed", level + 1);
                    prefs.flush();
                }
                fadeInButton();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            handler.showAds(false);
            ((Game) Gdx.app.getApplicationListener()).setScreen(new LevelSelectScreen(handler));
        }
    }

    private void generateFont() {
        font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("BRLNSB.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    private void fadeInButton() {
        generateFont();
        Skin skin = new Skin();
        Color gold = new Color(0.96f, 0.91f, 0.30f, 1);
        font.setColor(gold);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, gold);

        Texture frame = new Texture("frame.png");


        Drawable drawable = new TextureRegionDrawable(new TextureRegion(frame));
        ImageButton.ImageButtonStyle butttonStyle = new ImageButton.ImageButtonStyle();
        butttonStyle.up = skin.newDrawable(drawable);
        butttonStyle.down = skin.newDrawable(drawable, new Color(Color.WHITE));
        ImageButton ib = new ImageButton(butttonStyle);
        Stack stack = new Stack();

        Label levelLabel = null;
        if(this.level == Constants.MAX_LEVEL){
            levelLabel = new Label("More Games", labelStyle);
        }else{
            levelLabel = new Label("Next", labelStyle);
        }

        levelLabel.setAlignment(Align.center);


        stack.add(levelLabel);
        stack.add(ib);
        ib.addListener(new ActorGestureListener() {
                           public void touchUp(InputEvent event, float x, float y, int count, int button) {

                               Timer.schedule(new Timer.Task() {
                                   @Override
                                   public void run() {
                                       if(level == Constants.MAX_LEVEL){
                                           Gdx.net.openURI("https://play.google.com/store/apps/developer?id=blipthirteen");
                                       }else{
                                           ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(level+1, handler));
                                       }
                                    }
                               }, 0.1f);
                           }
                       }
        );
        stack.setSize(frame.getWidth(), frame.getHeight());
        stack.setPosition(540 - stack.getWidth()/2, 150);
        stage.addActor(stack);
        Gdx.input.setInputProcessor(stage);
    }

    private boolean checkForCompletion(){
        int completionSize = 0;

        for (Map.Entry<String, String> entry : levelMap.entrySet()) {
            String[] sa = entry.getValue().split(",");
            float rotation = Float.parseFloat(sa[1]);

            Tile t = tileMap.get(entry.getKey());
            float r = t.getRotation();
            if(r == 360){
                r = 0;
            }

            if(t.getId().equals("1")){
                if (rotation == 0 && (r == 0 || r == 180)){
                    //Gdx.app.log("" + t.getKey(), "complete");
                    completionSize++;
                }else if (rotation == 90 && (r == 90 || r == 270)){
                    completionSize++;
                }else{
                    Gdx.app.log("" + t.getKey(), "Not complete");
                }

            }else{
                if(rotation == r){
                    //Gdx.app.log("" + t.getKey(), "complete");
                    completionSize++;
                }else{
                    Gdx.app.log("" + t.getKey(), "Not complete");
                }
            }
        }

        if(completionSize == levelMap.size())
            return true;
        else return false;
    }

    @Override
    public void resize(int width, int height) {
        stretchViewport.update(width,height, true);
        fitViewport.update(width, height, true);
        stage.getViewport().update(width, height, true);
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

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!complete){
            for (Map.Entry<String, Tile> entry : tileMap.entrySet()) {
                Tile tile = entry.getValue();
                Vector3 touch = fitViewport.getCamera().unproject(new Vector3(screenX,screenY,0));
                if (tile.isTouched(new Vector2(touch.x, touch.y))) {
                    if(tile.getRotation() == 360){
                        tile.setRotation(0);
                    }
                    tile.setRotation(tile.getRotation() + 90);
                }
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}