package com.platformer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import static com.platformer.game.AID.*;

public class Renderer {
    private PerspectiveCamera camera;

    //Decal + Image initial
    private DecalBatch batch;
    private Array<Character> cman;
    private Vector3 cposition, cdirection, E1, E2;


    private Decal swordy;

    private GUI gui;

    //Time
    float stateTime;
    float tempX, tempZ, tempDX, tempDZ, tempDY, tempY;

    public Renderer(PerspectiveCamera cam, Array<Character> C) {
        this.camera = cam;
        batch = new DecalBatch(new CameraGroupStrategy(camera));
        this.cman = C;

        cposition = new Vector3(cam.position);
        cdirection = new Vector3(cam.direction);

        gui = new GUI();

        swordy = Decal.newDecal(2,1.5f,new TextureRegion(new Texture("assets/unknown.png")),true);
        swordy.setPosition(cam.position.x + 1,cam.position.y,cam.position.z+1);
    }

    public void render(float delta) {
        stateTime += delta;
        E1 = new Vector3(0f,0f, 0f);
        E2 = new Vector3(0f,0f, 0f);
        camera.update();

        cposition.set(camera.position);
        cdirection.set(camera.direction);
        //gets all Character - sets positions, orientations, and textures
        for(int i  = 0; i < cman.size; i++) {
            cman.get(i).step(delta);
            cman.get(i).decal.setPosition(cman.get(i).position);
            cman.get(i).decal.setTextureRegion(cman.get(i).anis.getKeyFrame(stateTime, true));
            cman.get(i).decal.lookAt(camera.position, camera.up);
            batch.add(cman.get(i).decal);
        }

        tempDX = camera.direction.x;
        tempDY = camera.direction.y;
        tempDZ = camera.direction.z;
        tempX = (float) (tempDX * Math.cos(Math.PI/4f)) - (float)(tempDZ * Math.sin(Math.PI/4f));
        tempY = (float) (tempDX * Math.sin(Math.PI/6f)) + (float)(tempDZ * Math.cos(Math.PI/6f));
        tempZ = (float) (tempDX * Math.sin(Math.PI/4f)) + (float)(tempDZ * Math.cos(Math.PI/4f));
        //System.out.println(camera.direction);
        //System.out.println(new Vector3(tempX, camera.direction.y, tempZ));
        //set to correct position
        E1.set(camera.position.x + tempX,camera.position.y,camera.position.z + tempZ);
        tempX = (float) (tempDX * Math.cos(-Math.PI/4f)) - (float)(tempDZ * Math.sin(-Math.PI/4f));
        tempZ = (float) (tempDX * Math.sin(-Math.PI/4f)) + (float)(tempDZ * Math.cos(-Math.PI/4f));
        E2.set(camera.position.x + tempX,camera.position.y,camera.position.z + tempZ);
        swordy.setPosition(E1);


        swordy.lookAt(E2,camera.up);

        batch.add(swordy);



        batch.flush();

        gui.render(delta);

    }

    public void dispose() {
        batch.dispose();
    }

}

