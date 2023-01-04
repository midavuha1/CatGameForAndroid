package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Cat {

    public static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;

    private Texture cat;

    public Cat(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        cat = new Texture("cover/cat.gif");
    }

    public void update(float dt) {
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(0, velocity.y, 0);
        if(position.y < 0) {
            position.y = 0;
        }
        velocity.scl(1 / dt);
    }

    public void jump() {
        velocity.y = 250;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getCat() {
        return cat;
    }
}
