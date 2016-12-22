package com.epicodus.gravityapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.view.View;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer extends View {


    Paint paint;
    Context context;
    float x;
    float y;

    public OpenGLRenderer(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        x = 100.0f;
        y = 100.0f;
    }



    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);

        Point vert1 = new Point((int)x,(int)y );
        Point vert2 = new Point((int)x,(int)y+100);
        Point vert3 = new Point((int)x+75,(int)y+50);

        Path p = new Path();
        p.setFillType(Path.FillType.EVEN_ODD);
        p.moveTo(vert1.x, vert1.y);
        p.lineTo(vert2.x, vert2.y);
        p.lineTo(vert3.x, vert3.y);
        p.lineTo(vert1.x, vert1.y);
        p.close();

        canvas.drawPath(p, paint);
    }

}