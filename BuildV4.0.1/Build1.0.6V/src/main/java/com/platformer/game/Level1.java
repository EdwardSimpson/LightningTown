package com.platformer.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Disposable;

import static com.badlogic.gdx.utils.JsonValue.ValueType.object;


public class Level1 implements Disposable {

    public ModelBuilder modelBuilder = new ModelBuilder();
    public ArrayMap<String, Model> Models;
    public Model Floor, Path, PathRotated, House, Roof, LampPost, HillBlock;
    public Array<ModelInstance> modelInstances = new Array<ModelInstance>();
    public ArrayMap<String, Vector3> Points = new ArrayMap<String,Vector3>();
    public Quad quad = new Quad();
    float[][] points;


    public Array<ModelInstance> CreateLevel1(){

        modelBuilder = new ModelBuilder();
        Models = new ArrayMap<String, Model>();

        Floor = modelBuilder.createBox(800f,0.1f,800f,
               new Material(ColorAttribute.createDiffuse(Color.FOREST)),
               VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);

        HillBlock = modelBuilder.createBox(50f,0.1f, 50f,
                new Material(ColorAttribute.createDiffuse(Color.FOREST)),
                VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);

        Path = modelBuilder.createBox(3.5f,1f,10f,
                new Material(ColorAttribute.createDiffuse(Color.GRAY)),
                VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);

        PathRotated = modelBuilder.createBox(10f,1f,3.5f,
                new Material(ColorAttribute.createDiffuse(Color.GRAY)),
                VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);

        House = modelBuilder.createBox(4f,4f,4f,
                new Material(ColorAttribute.createDiffuse(Color.BROWN)),
                VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);

        Roof = modelBuilder.createCone(8f,2f,8f, 10,
                new Material(ColorAttribute.createDiffuse(Color.BLACK)),
                VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);

        LampPost = modelBuilder.createBox(0.5f,10f,0.5f,
                new Material(ColorAttribute.createDiffuse(Color.BLACK)),
                VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);


        //Floor
        modelInstances.add(new ModelInstance(Floor,0f,-2f,0f));

        //Main Path
        modelInstances.add(new ModelInstance(Path,0f,-2.3f,0f));
        modelInstances.add(new ModelInstance(Path,0f,-2.3f,-10f));
        modelInstances.add(new ModelInstance(Path,0f,-2.3f,-20f));
        modelInstances.add(new ModelInstance(Path,0f,-2.3f,-30f));

        //First Fork
        modelInstances.add(new ModelInstance(LampPost, -3f,0f,-27f));
        modelInstances.add(new ModelInstance(PathRotated, -3.5f,-2.3f,-30f));
        modelInstances.add(new ModelInstance(PathRotated, -13.5f,-2.3f,-30f));
        modelInstances.add(new ModelInstance(PathRotated, -23.5f,-2.3f,-30f));
        modelInstances.add(new ModelInstance(PathRotated, -33.5f,-2.3f,-30f));

        //First Fork House Set 1
        modelInstances.add(new ModelInstance(House, -18.5f,0f,-25f));
        modelInstances.add(new ModelInstance(Roof,-18.5f,3f,-25f));
        modelInstances.add(new ModelInstance(House, -13.5f,0f,-35f));
        modelInstances.add(new ModelInstance(Roof, -13.5f,3f,-35f));
        modelInstances.add(new ModelInstance(House, -23.5f,0f,-35f));
        modelInstances.add(new ModelInstance(Roof, -23.5f,3f,-35f));


        //Main Path
        modelInstances.add(new ModelInstance(Path,0f,-2.3f,-40f));
        modelInstances.add(new ModelInstance(Path,0f,-2.3f,-50f));
        modelInstances.add(new ModelInstance(Path,0f,-2.3f,-60f));

        //Second Fork
        modelInstances.add(new ModelInstance(LampPost, 3f,0f,-57f));
        modelInstances.add(new ModelInstance(PathRotated, 3.5f,-2.3f,-60f));
        modelInstances.add(new ModelInstance(PathRotated, 13.5f,-2.3f,-60f));
        modelInstances.add(new ModelInstance(PathRotated, 23.5f,-2.3f,-60f));
        modelInstances.add(new ModelInstance(PathRotated, 33.5f,-2.3f,-60f));

        //Second Fork House Set 2
        modelInstances.add(new ModelInstance(House, 18.5f,0f,-55f));
        modelInstances.add(new ModelInstance(Roof,18.5f,3f,-55f));
        modelInstances.add(new ModelInstance(House, 13.5f,0f,-65f));
        modelInstances.add(new ModelInstance(Roof, 13.5f,3f,-65f));
        modelInstances.add(new ModelInstance(House, 23.5f,0f,-65f));
        modelInstances.add(new ModelInstance(Roof, 23.5f,3f,-65f));

        //Main Path
        modelInstances.add(new ModelInstance(Path,0f,-2.3f,-70f));
        modelInstances.add(new ModelInstance(Path,0f,-2.3f,-80f));
        modelInstances.add(new ModelInstance(Path,0f,-2.3f,-90f));


        //Hill
        modelInstances.add(new ModelInstance(HillBlock,20f,0f,-100f));

        modelInstances.get(33).transform.rotate(new Vector3(1f,1f,0f),25);

        modelInstances.add(new ModelInstance(HillBlock,-20f,0f,-100f));

        modelInstances.get(34).transform.rotate(new Vector3(1f,-1f,0f),25);

        modelInstances.add(new ModelInstance(HillBlock,-40f,0f,-100f));

        modelInstances.get(35).transform.rotate(new Vector3(1f,-1f,0f),25);

        modelInstances.add(new ModelInstance(HillBlock,40f,0f,-100f));

        modelInstances.get(36).transform.rotate(new Vector3(1f,1f,0f),25);


        points = new float[][] { { -3f, -27f }, { -18.5f, -25f }, { -13.5f, -35f }, { -23.5f, -35f },
                { 3f,-57f }, { 18.5f, -55f }, { 13.5f, -65f }, { 23.5f, -65f }};


        return modelInstances;
    }

    public float[][] getPoints(){return points;}


    @Override
    public void dispose() {

        for(int i = 0; i < modelInstances.size; i++){
            modelInstances.get(i).model.dispose();
        }

    }
}
