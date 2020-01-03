package Shaders;

public class StaticShader extends ShaderProgram{
	private static final String VERTEX_FILE = "src/Shaders/VertexShader.txt";
	private static final String FRAGMENT_FILE = "src/Shaders/FragmentShader.txt";

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void bindAttributes() {
		// TODO Auto-generated method stub
		super.bindAttributes(0, "position");
		super.bindAttributes(1, "textureCoords");
		
	}

}
