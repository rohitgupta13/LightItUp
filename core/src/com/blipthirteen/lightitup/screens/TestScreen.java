package com.blipthirteen.lightitup.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 10/15/2017.
 */

public class TestScreen implements Screen {


    HashMap<String, String> levelMap;
    HashMap<String, Texture> textureMap;
    HashMap<String, Float> rotationMap;

    StretchViewport stretchViewport;
    FitViewport fitViewport;
    Stage stage;
    SpriteBatch spriteBatch;

    ArrayList<ImageButton> imageButtons;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        initMap();
        initUI();
        initRotationMap();
        loadAssets();
        Gdx.input.setInputProcessor(stage);
        createAndPlaceButtons();

        addListeners();


    }

    private void initMap(){
        levelMap = new HashMap<String, String>();
        // Data
        levelMap.put("3,1", "3,1");
        levelMap.put("3,2", "1,0");
        levelMap.put("3,3", "1,0");
        levelMap.put("3,4", "1,0");
        levelMap.put("3,5", "3,0");
    }

    private void initUI(){
        fitViewport = new FitViewport(1080,1920);
        stretchViewport = new StretchViewport(1080,1920);
        stage = new Stage();
        stage.setViewport(fitViewport);

    }


    private void loadAssets(){
        textureMap = new HashMap<String, Texture>();
        textureMap.put("1",new Texture("1.png"));
        textureMap.put("2",new Texture("2.png"));
        textureMap.put("3",new Texture("3.png"));
    }

    private void initRotationMap(){
        rotationMap = new HashMap<String, Float>();
        rotationMap.put("0",0f);
        rotationMap.put("1",90f);
        rotationMap.put("2",180f);
        rotationMap.put("3",270f);
    }

    private void createAndPlaceButtons(){
        imageButtons = new ArrayList<ImageButton>();

        for (Map.Entry<String, String> entry : levelMap.entrySet()) {
            Vector2 position = getPosition(entry.getKey());
            String[] sa = entry.getValue().split(",");
            ImageButton imageButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(textureMap.get(sa[0]))));
            imageButton.setPosition(position.x,position.y);
            imageButton.setRotation(rotationMap.get(sa[1]));
            imageButtons.add(imageButton);
            stage.addActor(imageButton);
        }
    }

    private Vector2 getPosition(String s){
        String[] sa = s.split(",");
        int xPos = Integer.parseInt(sa[0]);
        int yPos = Integer.parseInt(sa[1]);
        return new Vector2(120 + xPos * 120, 540 + yPos * 200);
    }

    private void addListeners() {
        stage.setDebugAll(true);

        for (int i = 0; i < imageButtons.size(); i++) {
            final  ImageButton imageButton = imageButtons.get(i);
            imageButton.setOrigin(imageButton.getWidth()/2, imageButton.getHeight()/2);
            imageButton.addListener(new InputListener() {

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("Touch");
                    super.touchUp(event, x, y, pointer, button);
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("Clicked   " + imageButton.toString());
                    imageButton.rotateBy(-45);
                    imageButton.addAction(Actions.rotateBy(-300, 0.2f));
                    imageButton.setRotation(200);
                    stage.act();
                    stage.draw();
                    return true;
                }
            });
        }
    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor((float)230/255,(float)230/255,(float)230/255,1);

        spriteBatch.setProjectionMatrix(fitViewport.getCamera().combined);

        spriteBatch.begin();
        spriteBatch.end();
        fitViewport.apply();
        stage.act();
        stage.draw();


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
}