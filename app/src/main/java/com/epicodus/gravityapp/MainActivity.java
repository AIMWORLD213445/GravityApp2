package com.epicodus.gravityapp;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final OpenGLRenderer draw = new OpenGLRenderer(this);
        setContentView(draw);
        draw.requestFocus();
        draw.setOnTouchListener(new View.OnTouchListener()

        {

            @Override
            public boolean onTouch (View v, MotionEvent e)
            {
                draw.x = e.getX();
                draw.y = e.getY();
                draw.invalidate();
                return true;
            }


        });
    }



}





