package com.epicodus.gravityapp;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {



    private final String vertexCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    " gl_Position = vPosition;" +
                    "}";

    private final String fragmentCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    " gl_FragColor = vColor;" +
                    "}";

    private FloatBuffer vertexBuffer;


    static final int COORDS_PER_VERTEX = 3;
    static float triangleCoords[] = {
            0.0f,  0.622008459f, 0.0f,
            -0.5f, -0.311004243f, 0.0f,
            0.5f, -0.311004243f, 0.0f
    };


    float color[] = { 0.93671875f, 0.36953125f, 0.42265625f, 0.5f };


    private  final int mProgram;

    public Triangle() {

        ByteBuffer bb = ByteBuffer.allocateDirect(
                triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);

        int vertexShader = OpenGLRenderer.myShader(GLES20.GL_VERTEX_SHADER, vertexCode);
        int fragmentShader = OpenGLRenderer.myShader(GLES20.GL_FRAGMENT_SHADER, fragmentCode);

        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);
    }

    private int mPositioner;
    private int mColorer;

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;

    public void draw() {
        GLES20.glUseProgram(mProgram);
        mPositioner = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositioner);
        GLES20.glVertexAttribPointer(mPositioner,COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);
        mColorer = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mColorer,1,color,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0, vertexCount);
        GLES20.glDisableVertexAttribArray(mPositioner);
    }
}