package com.platformer.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.graphics.g3d.particles.batches.PointSpriteParticleBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.platformer.game.InputController.InputHandler;

import java.util.Random;

public class Orchestrator extends ApplicationAdapter {

	public static PerspectiveCamera camera;
	public static ModelBatch modelBatch;
	private ModelBuilder modelBuilder;
	private Environment environment;
	private Renderer rendMan;
	protected final Vector3 tmp = new Vector3();
	private int frameCounter = 0;
	private Array<Character> cmans = new Array<Character>();
	private int jumpStart = -1000;
	private boolean jumping = false;
	public Level1 level1;
	private InputHandler inputHandler;
	private Lightning lightning;
	private Quad quadTree = new Quad();
	public float[][] points;
	private Array<ModelInstance> instances = new Array<ModelInstance>();
	public boolean Colliding = false;

	//Makeshift gui
	private Stage stage;
	private Label label;
	private BitmapFont font;
	private StringBuilder stringBuilder;

	public static boolean wait = true;
	public static byte hp = 100, lc = 0;

	//particles
	private ParticleEffect currentEffects;
	public ParticleSystem particleSystem;

	public Orchestrator(PerspectiveCamera cam) {
		this.create();
		camera = cam;
		wait = false;
	}

	@Override
	public void create () {

		//Camera
		camera = new PerspectiveCamera(90,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0f, 0f, 3f);
		camera.lookAt(0f,0f,0f);
		camera.near = 0.1f;
		camera.far = 1000f;

		//************************************************************************************************
		//particle system
		particleSystem = ParticleSystem.get();
		PointSpriteParticleBatch pointSpriteBatch = new PointSpriteParticleBatch();
		pointSpriteBatch.setCamera(camera);
		particleSystem = ParticleSystem.get();

		particleSystem.add(pointSpriteBatch);

		//asset loading
		doneLoading();

		//particle loading **************** ADD PARTICLES HERE
		currentEffects= Assets.manager.get("assets/particles/fire.pfx",ParticleEffect.class).copy();
		currentEffects.init();
		currentEffects.translate(new Vector3(0f,0f,12f));
		particleSystem.add(currentEffects);

		//*****************************************************************************************************


		//Makeshift gui display
		stage = new Stage();
		font = new BitmapFont();
		label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
		stage.addActor(label);
		stringBuilder = new StringBuilder();

		//Constructors
		lightning = new Lightning();
		inputHandler = new InputHandler();
		quadTree = new Quad();

		//Level1
		level1 = new Level1();

		//Environment
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.1f,0.1f,0.1f,1f));
		//environment.add(new DirectionalLight().set(0f, 0f, 0f, -1f, -2f, -0.5f));
		environment.add(new PointLight().set(1f, 1f, 1f, 3f, 11f, -57f, 200f));
		environment.add(new PointLight().set(1f, 1f, 1f, -3f, 11f, -27f, 200f));

		//Models
		initModels();

		//Inputs Handler
		Gdx.input.setInputProcessor(inputHandler);

		//Sound
		//initSounds();

		//Character Handler
		rendMan = new Renderer(GameScreen.cam,cmans);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(77/255f, 100/255f,105/255f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);

		Colliding = quadTree.SearchRegion((camera.position.x+2.5f),(camera.position.z+2.5f),(camera.position.x-2.5f),(camera.position.z-2.5f));

		if (Colliding && inputHandler.forward){
			if(!(inputHandler.backwards)) {
				tmp.set(camera.direction).nor().scl(0.05f);
				camera.position.add(-tmp.x, 0f, -tmp.z);
			} else {
				tmp.set(camera.direction).nor().scl(-0.05f);
				camera.position.add(tmp.x,0f, tmp.z);
			}
		}

		if (Colliding && inputHandler.sprinting && inputHandler.forward){
			if(!(inputHandler.backwards)) {
				tmp.set(camera.direction).nor().scl(0.15f);
				camera.position.add(-tmp.x, 0f, -tmp.z);
			} else {
				tmp.set(camera.direction).nor().scl(-0.15f);
				camera.position.add(tmp.x,0f, tmp.z);
			}
		}


		if(lightCheck() % 4 == 0)
			environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.1f,0.1f,0.1f,1f));
		else
			environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.8f,0.8f,0.8f,1f));


		if(inputHandler.mouseMoved(Gdx.graphics.getHeight(),Gdx.graphics.getWidth())){
			camera.direction.rotate(camera.up, -(Gdx.input.getDeltaX() * 0.5f));
			tmp.set(camera.direction).crs(camera.up).nor();
			camera.direction.rotate(tmp, -Gdx.input.getDeltaY() * 0.5f);
		}

		frameCounter++;

		if(frameCounter == 3){
			initSounds();
		}
		if(inputHandler.space && camera.position.y < 0.1){  // working on jump
			jumpStart = frameCounter;
			System.out.println("Jump start is: " + jumpStart);
		}

		if(frameCounter < jumpStart + 20 && frameCounter > 10){
			jumping = true;
			tmp.set(camera.direction).nor().scl(0.005f);
			camera.position.add(tmp.x, .1f, tmp.z);
		}
		if(frameCounter > jumpStart+20 && frameCounter <= jumpStart+40 && frameCounter > 10){
			tmp.set(camera.direction).nor().scl(0.005f);
			camera.position.add(tmp.x, -.1f, tmp.z);
			if(frameCounter == jumpStart + 40) {
				jumpStart = -1000;
				jumping = false;
			}
		}

		Gdx.input.setCursorCatched(true);
		if(inputHandler.forward && inputHandler.sprinting){  // sprinting
			tmp.set(camera.direction).nor().scl(0.15f);
			camera.position.add(tmp.x,0f, tmp.z);

		}

		if(inputHandler.forward) {
			tmp.set(camera.direction).nor().scl(0.05f);
			camera.position.add(tmp.x,0f, tmp.z);
		}

		if(inputHandler.backwards && inputHandler.sprinting){
			tmp.set(camera.direction).nor().scl(-0.15f);
			camera.position.add(tmp.x,0f, tmp.z);
		}
		if(inputHandler.backwards) {
			tmp.set(camera.direction).nor().scl(-0.05f);
			camera.position.add(tmp.x,0f, tmp.z);
		}

		if(inputHandler.left && inputHandler.sprinting){  // sprinting
			tmp.set(camera.direction).crs(camera.up).nor().scl(-0.15f);
			camera.position.add(tmp);

		}
		if(inputHandler.left){
			tmp.set(camera.direction).crs(camera.up).nor().scl(-0.05f);
			camera.position.add(tmp);
		}

		if(inputHandler.right && inputHandler.sprinting){  // sprinting
			tmp.set(camera.direction).crs(camera.up).nor().scl(0.15f);
			camera.position.add(tmp);
		}
		if(inputHandler.right){
			tmp.set(camera.direction).crs(camera.up).nor().scl(0.05f);
			camera.position.add(tmp);
		}


		//System.out.println(camera.position.x + tmp.x);
		while(camera.position.x > 400){
			tmp.set(camera.direction).nor().scl(0.05f);
			camera.position.add(-tmp.x,0f, -tmp.z);
		}

		while(camera.position.x < -400){
			tmp.set(camera.direction).nor().scl(0.05f);
			camera.position.add(-tmp.x,0f, -tmp.z);
		}

		while(camera.position.z > 400 ){
			tmp.set(camera.direction).nor().scl(0.05f);
			camera.position.add(-tmp.x,0f, -tmp.z);
		}

		while(camera.position.z < -400){
			tmp.set(camera.direction).nor().scl(0.05f);
			camera.position.add(-tmp.x,0f, -tmp.z);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}


		final Sound walking = Gdx.audio.newSound(Gdx.files.internal(Assets.walking));
		final Sound walking3 = Gdx.audio.newSound(Gdx.files.internal(Assets.sprinting));
		frameCounter++;

		if(inputHandler.right || inputHandler.left || inputHandler.backwards || inputHandler.forward && jumping == false){
			if(frameCounter % 40.0  == 0.0) {
				final long id = walking.play();
				walking.setVolume(id, .8f);
				if(inputHandler.sprinting){
					walking.pause(id);
					final long id3 = walking3.play();
				}
			}
		}

		camera.update();
		Assets.update();

		modelBatch.begin(camera);
		particleSystem.update();
		particleSystem.begin();
		particleSystem.draw();
		particleSystem.end();
		modelBatch.render(particleSystem);
		modelBatch.render(instances,environment);
		modelBatch.end();

		rendMan.render(Gdx.graphics.getDeltaTime());

		//makeshift gui render
		stringBuilder.setLength(0);
		stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());
		stringBuilder.append(" Time Left: ").append(lightning.getTime());
		stringBuilder.append(" Health: ").append(hp);
		label.setText(stringBuilder);
		stage.draw();

		//lightning steps
		lightning.step(Gdx.graphics.getDeltaTime());
		if(lightning.getTime() == 0) {
			lightning.strike(environment, camera);
			System.out.println("*****STRIKE******");

			hp -= 35;

			final Sound thunder = Gdx.audio.newSound(Gdx.files.internal(Assets.thunder));
			final long id = thunder.play();
			thunder.setVolume(id, .2f);
			lc = 16;
		}

		if(hp < 0) hp = 100;
	}

	private void initModels(){
		modelBatch = new ModelBatch();
		modelBuilder = new ModelBuilder();


		Model Player = modelBuilder.createBox(2f,2f,2f,
				new Material(ColorAttribute.createDiffuse(Color.GREEN)),
				VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);

		Model Player1 = modelBuilder.createBox(2f,2f,2f,
				new Material(ColorAttribute.createDiffuse(Color.GREEN)),
				VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);


		instances = level1.CreateLevel1();
		points = level1.getPoints();
		quadTree.DefineQuadTree(points);

		cmans.add(new Character(Player1, camera,"Bob",SPR.SKIN2,SPR.HAIR1_RED,SPR.EYES_RED,SPR.BLANK, 6f,0f,-16f));
		cmans.add(new Character(Player, camera,"Bob",SPR.SKIN1,SPR.HAIR1_BLACK,SPR.EYES_RED,SPR.BLANK, 12f,0f,-16f));

	}

	private void initSounds(){
		Random rand = new Random();
		byte int_random = (byte) rand.nextInt(4);

		Music music = Gdx.audio.newMusic(Gdx.files.internal(Assets.music)); // Will randomly play one out of the 4 songs for background music
		Music music1 = Gdx.audio.newMusic(Gdx.files.internal(Assets.music1));
		Music music2 = Gdx.audio.newMusic(Gdx.files.internal(Assets.music2));
		Music music3 = Gdx.audio.newMusic(Gdx.files.internal(Assets.music3));

		// sets the volume to half the maximum volume
		switch(int_random) {
			case 0:
				music.setLooping(true);
				music.setVolume(0.2f);
				music.play();
				break;

			case 1:
				music1.setLooping(true);
				music1.setVolume(0.2f);
				music1.play();
				break;

			case 2:
				music2.setLooping(true);
				music2.setVolume(0.2f);
				music2.play();
				break;

			case 3:
				music3.setLooping(true);
				music3.setVolume(0.2f);
				music3.play();

		}
	}

	private int lightCheck() {
		if(lc != 0) lc--;
		return (int) lc;
	}

	private void doneLoading() {
		Assets.load(particleSystem);
	}

	@Override
	public void dispose(){
		if (currentEffects!=null) currentEffects.dispose();
		modelBatch.dispose();
		rendMan.dispose();
		Assets.dispose();
	}

}
