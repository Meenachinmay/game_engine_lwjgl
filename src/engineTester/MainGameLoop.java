package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {
	public static void main(String[] args) {
		
		DisplayManager .createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		 float[] verticies = {
            // Left Bottom Triangle
            -0.5F, 0.5F, 0f,
            -0.5F, -0.5F, 0f,
             0.5F, -0.5F, 0f,
             0.5F, 0.5F, 0f,
	    };
		 
		int[] indices = {
			0,1,3, // Top left triangle
			3,1,2 // bottom right triangle
		};

		RawModel model = loader.loadToVAO(verticies, indices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			// game logic
			renderer.render(model);
			
			DisplayManager.updateDisplay();
		}
		
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
	}

}
