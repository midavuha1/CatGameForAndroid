package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Cat;

public class PlayState extends State {

    private Cat cat;
    private Texture bg;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        cat = new Cat(50, 300);
        camera.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        bg = new Texture("cover/bg.png");
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
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, 0,0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(cat.getCat(), cat.getPosition().x, cat.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
