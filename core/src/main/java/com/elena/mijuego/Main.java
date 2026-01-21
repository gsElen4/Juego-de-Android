package com.elena.mijuego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends ApplicationAdapter {

    // Resolución virtual
    public static final float WORLD_WIDTH = 1920;
    public static final float WORLD_HEIGHT = 1080;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private ParallaxVista fondo;
    private ParallaxVista nubes;
    private ParallaxVista arboles;
    private ParallaxVista sombra;

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply();

        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);

        // Ahora pasan WORLD_HEIGHT
        fondo   = new ParallaxVista("fondo.png", 0f, WORLD_HEIGHT);
        nubes   = new ParallaxVista("nubes.png", 20f, WORLD_HEIGHT);
        arboles = new ParallaxVista("arbolesMontaje.png", 40f, WORLD_HEIGHT);
        sombra  = new ParallaxVista("sombra.png", 0f, WORLD_HEIGHT);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        fondo.update(delta);
        nubes.update(delta);
        arboles.update(delta);
        sombra.update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        fondo.draw(batch);
        nubes.draw(batch);
        arboles.draw(batch);
        sombra.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        fondo.dispose();
        nubes.dispose();
        arboles.dispose();
        sombra.dispose();
    }
}
