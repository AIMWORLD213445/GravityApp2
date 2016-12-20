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

        public openGLSurfaceView(Context context){
            super(context);


            setEGLContextClientVersion(2);

            mRenderer = new OpenGLRenderer();


            setRenderer(mRenderer);
        }
    }

    }


