package engineTester;

import org.lwjgl.opengl.Display;

import Shaders.StaticShader;
import model.RawModel;
import model.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import textures.ModelTexture;

public class MainGameLoop {
	public static void main(String[] args) {
		
		DisplayManager .createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		 float[] verticies = {
            // Left Bottom Triangle
            -0.5F, 0.5F, 0f, 	 // V0
            -0.5F, -0.5F, 0f, 	// V1
             0.5F, -0.5F, 0f,  // V2
             0.5F, 0.5F, 0f,  // V3
	    };
		 
		int[] indices = {
			0,1,3, // Top left triangle
			3,1,2 // bottom right triangle
		};
		
		float[] textureCoords = {
			0,0,
			0,1,
			1,1,
			1,0
		};

		RawModel model = loader.loadToVAO(verticies, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			
			// game logic
			
			// start a shader
			shader.start();
			// render a model
			renderer.render(texturedModel);
			// stop a shader
			shader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
	}

}
