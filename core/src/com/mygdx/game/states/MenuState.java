package com.mygdx.game.states;

import static com.badlogic.gdx.Gdx.gl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class MenuState extends State{

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("cover/bg.png");
        playBtn = new Texture("cover/playbtn.png");
    }

    @Override
    protected void touchInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        touchInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        sb.begin();
        sb.draw(background, 0,0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(playBtn, (MyGdxGame.WIDTH / 2) - (playBtn.getWidth() / 2), MyGdxGame.HEIGHT / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
