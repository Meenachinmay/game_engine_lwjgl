package engineTester;

import org.lwjgl.opengl.Display;

import Shaders.StaticShader;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

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

		RawModel model = loader.loadToVAO(verticies, indices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			
			// game logic
			
			// start a shader
			shader.start();
			// render a model
			renderer.render(model);
			// stop a shader
			shader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
	}

}
