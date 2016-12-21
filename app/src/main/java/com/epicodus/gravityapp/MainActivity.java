package com.epicodus.gravityapp;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;


public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new openGLSurfaceView(this);
        setContentView(mGLView);
    }

public class openGLSurfaceView extends GLSurfaceView {

        private final OpenGLRenderer mRenderer;
        private final float TOUCH_SCALE_FACTOR = 180.0f/320;
        private float mPreviousX;
        private float mPreviousY;

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        float x = e.getX();
        float y = e.getY();

        switch(e.getAction()){
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                if (y>getHeight()/2){
                    dx = dx * -1;
                }
                if (x>getWidth()/2){
                    dy = dy * -1;
                }

                mRenderer.setAngle(
                        mRenderer.getAngle() + ((dx-dy) * TOUCH_SCALE_FACTOR));
                requestRender();

        }
        mPreviousX = x;
        mPreviousY = y;
        return true;
    }

        public openGLSurfaceView(Context context){
            super(context);


            setEGLContextClientVersion(2);

            mRenderer = new OpenGLRenderer();


            setRenderer(mRenderer);
            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        }
    }

    }


