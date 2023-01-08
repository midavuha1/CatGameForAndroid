package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class House {

    public static final int FLUCTUATION = 130;

    private Texture house;
    private Vector2 posHouse;
    private Random rand;
    private Rectangle boundsHouse;

    public Texture getHouse() {
        return house;
    }

    public Vector2 getPosHouse() {
        return posHouse;
    }

    public House(float x) {
        house = new Texture("cover/house.png");
        rand = new Random();

        posHouse = new Vector2(x, 0);

        boundsHouse = new Rectangle(posHouse.x, posHouse.y, house.getWidth(), house.getHeight());

    }

    public void reposition(float x) {
        posHouse.set(x, 0);
        boundsHouse.setPosition(posHouse.x, posHouse.y);
    }

    public boolean collides (Rectangle player) {
        return player.overlaps(boundsHouse);
    }

    public void dispose() {
        house.dispose();
    }
}
