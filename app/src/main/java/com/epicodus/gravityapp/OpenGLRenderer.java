package com.epicodus.gravityapp;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private float[] mRotMatrix = new float[16];
    public volatile float mAngle;

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle=angle;
    }



    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        mTriangle = new Triangle();
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        float[] scratch = new float[16];
//        long time = SystemClock.uptimeMillis() % 4000L;
//        float angle = 0.090f * ((int)time);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        Matrix.setRotateM(mRotMatrix,0,mAngle,0,0,-1.0f);
        Matrix.setLookAtM(mViewMatrix,0,0,0,5f,0f,0f,0f,0f,1.0f,0.0f);
        Matrix.multiplyMM(mMVPMatrix,0,mProjectionMatrix,0,mViewMatrix, 0);
        Matrix.multiplyMM(scratch,0, mMVPMatrix,0, mRotMatrix, 0);
        Matrix.translateM(mTriangle.mMatrix, 0, 0.5f,0f,0f);
        mTriangle.draw(scratch);
    }
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width/height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio,-1,1,1,10);
    }
    public static int myShader(int type, String code) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, code);
        GLES20.glCompileShader(shader);
        return shader;

    }
}