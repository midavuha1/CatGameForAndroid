package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Cat;
import com.mygdx.game.sprites.House;

public class PlayState extends State {

    public static final int HOUSE_SPACING = 75;
    public static final int HOUSE_WIDTH = 211;
    public static final int HOUSE_COUNT = 4;
    public static final int HOUSE_HEIGHT = 610;
    public static final int Ground_Y_OFFSET = -80;

    private Cat cat;
    private Texture bg;
    private House house;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<House> houses;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        cat = new Cat(50, 400);
        camera.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        bg = new Texture("cover/bg.png");
        house = new House(100);
        houses = new Array<House>();
        ground = new Texture("cover/ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, Ground_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), Ground_Y_OFFSET);

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
        updateGround();
        cat.update(dt);
        camera.position.x = cat.getPosition().x + 80;

        for (int i = 0; i < houses.size; i++) {
            House house = houses.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > house.getPosHouse().x + HOUSE_WIDTH) {
                house.reposition(house.getPosHouse().x + (HOUSE_SPACING + HOUSE_WIDTH) * HOUSE_COUNT);
            }

            if (house.collides(cat.getBounds()) && cat.getPosition().y >= 273 && cat.getPosition().y <= 287) {
                cat.jump();
            } else if (house.collides(cat.getBounds()) && cat.getPosition().y < 267 && cat.getPosition().x >= house.getPosHouse().x - HOUSE_WIDTH) {
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
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        house.dispose();
        ground.dispose();
        for (House house : houses) {
            house.dispose();
        }
    }

    private void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
