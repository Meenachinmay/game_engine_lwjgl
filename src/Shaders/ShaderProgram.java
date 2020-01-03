package Shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL20.*;

public abstract class ShaderProgram {
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	// constructor
	public ShaderProgram(String vertexFile, String fragmentFile) {
		vertexShaderID = loadShader(vertexFile, GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile, GL_FRAGMENT_SHADER);
		programID = glCreateProgram();
		glAttachShader(programID, vertexShaderID);
		glAttachShader(programID, fragmentShaderID);
		glLinkProgram(programID);
		glValidateProgram(programID);
		bindAttributes();
	}
	
	
	// start a program
	public void start() {
		 glUseProgram(programID);
	}
	
	// stop a program
	public void stop() {
		glUseProgram(0);
	}
	
	// clean up method, memory managment
	public void cleanUp() {
		stop();
		glDetachShader(programID, vertexShaderID);
		glDetachShader(programID, fragmentShaderID);
		glDeleteShader(vertexShaderID);
		glDeleteShader(fragmentShaderID);
		glDeleteProgram(programID);
	}
	
	protected abstract void bindAttributes();
	
	// to bind attributes to the program
	protected void bindAttributes(int attribute, String variableName) {
		glBindAttribLocation(programID, attribute, variableName);
	}
	
	// to load shader
	private static int loadShader(String file_name, int type) {
		StringBuilder shaderSource = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file_name));
			String line;
			while ((line = reader.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}
			reader.close();
		}catch(IOException e) {
			System.err.println("Could not read file!");
			e.printStackTrace();
			System.exit(-1);
		}
		
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID,  shaderSource);
		glCompileShader(shaderID);
		if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile the shader");
			System.exit(-1);
		}
		return shaderID;
	}
}
