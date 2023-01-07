package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Cat;
import com.mygdx.game.sprites.House;

public class PlayState extends State {

    public static final int HOUSE_SPACING = 250;
    public static final int HOUSE_WIDTH = 400;
    public static final int HOUSE_COUNT = 4;

    private Cat cat;
    private Texture bg;
    private House house;

    private Array<House> houses;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        cat = new Cat(50, 800);
        camera.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        bg = new Texture("cover/bg.png");
        house = new House(100);
        houses = new Array<House>();

        for (int i = 0; i < HOUSE_COUNT; i++) {
            houses.add(new House(i * (HOUSE_SPACING + HOUSE_WIDTH)));
        }
    }

    @Override
    protected void touchInput() {
        if(Gdx.input.justTouched()) {
            cat.jump();
        }
    }

    @Override
    public void update(float dt) {
        touchInput();
        cat.update(dt);
        camera.position.x = cat.getPosition().x + 80;

        for (House house : houses) {
            if (camera.position.x - (camera.viewportWidth / 2) > house.getPosHouse().x + HOUSE_WIDTH) {
                house.reposition(house.getPosHouse().x + (HOUSE_SPACING + HOUSE_WIDTH) * HOUSE_COUNT);
            }

            if (house.collides(cat.getBounds()) && cat.getPosition().y >= 610 && cat.getPosition().y <= 650) {
                cat.jump();
            } else if (house.collides(cat.getBounds()) && cat.getPosition().y <= 550 && cat.getPosition().x >= house.getPosHouse().x - HOUSE_WIDTH) {
                gsm.set(new MenuState(gsm));
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(cat.getCat(), cat.getPosition().x, cat.getPosition().y);
        for (House house : houses) {
            sb.draw(house.getHouse(), house.getPosHouse().x, house.getPosHouse().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
