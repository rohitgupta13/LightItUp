package com.blipthirteen.lightitup.misc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by HP on 10/16/2017.
 */

public class Tile {

    private String key;
    private String id;
    private Vector2 position;
    private float rotation;
    private Rectangle boundingRectangle;
    private Sprite sprite;

    public Tile(String key, String id, Texture texture, Vector2 position, float rotation) {
        this.key = key;
        this.id = id;
        this.position = position;
        this.rotation = rotation;
        this.boundingRectangle = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        this.sprite = new Sprite(texture);
    }

    public String getKey() {
        return key;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public String getId() {
        return id;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public boolean isTouched(Vector2 touchPos){
        if(boundingRectangle.contains(touchPos)) {
            return true;
        }else {
            return false;
        }
    }

    public void draw(SpriteBatch batch){
        sprite.setPosition(position.x, position.y);
        sprite.setRotation(rotation);
        this.sprite.draw(batch);
    }
}