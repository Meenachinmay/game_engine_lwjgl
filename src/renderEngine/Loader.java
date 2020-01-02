package renderEngine;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Loader {

    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();

    public RawModel loadToVAO(float[] positions, int[] indices){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, positions);
        unbindVAO();

        return new RawModel(vaoID, indices.length);
    }

    public void cleanUp(){
        for (int vao:vaos){
            glDeleteVertexArrays(vao);
        }
        for (int vbo:vbos){
            glDeleteBuffers(vbo);
        }
    }

    private int createVAO(){
        int vaoID = glGenVertexArrays();
        vaos.add(vaoID);
        glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeDataInAttributeList(int attributeNumber, float[] data){
        int vboID = glGenBuffers();
        vbos.add(vboID);
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);

        glVertexAttribPointer(attributeNumber, 3, GL_FLOAT, false, 0, 0);
        // unbind the vertex buffer
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    
    private void bindIndicesBuffer(int[] indices) {
    	int vboID = glGenBuffers();
    	vbos.add(vboID);
    	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
    	IntBuffer buffer = storeDataInIntBuffer(indices);
    	glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    
    private IntBuffer storeDataInIntBuffer(int[] data) {
    	IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
    	buffer.put(data);
    	buffer.flip();
    	return buffer;
    }

    private void unbindVAO(){
            glBindVertexArray(0);
    }
}
