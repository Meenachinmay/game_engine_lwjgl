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
            // Right Top Triangle
            0.5F, -0.5F, 0f,
            0.5F, 0.5F, 0f,
            -0.5F, 0.5F, 0f,
	    };

		 RawModel model = loader.loadToVAO(verticies);
		
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
