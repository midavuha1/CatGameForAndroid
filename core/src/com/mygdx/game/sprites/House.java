package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class House {

    public static final int FLUCTUATION = 130;

    private Texture house;
    private Vector2 posHouse;
    private Random rand;

    public House(float x) {
        house = new Texture("cover/house.png");
        rand = new Random();

        posHouse = new Vector2(x, 0 + rand.nextInt(FLUCTUATION));
    }
}
