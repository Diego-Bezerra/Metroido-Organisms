package com.bezerra.diego.metroidoorganismslivewallpaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Diego Bezerra on 12/01/17.
 */

public class Metroido {

    private static List<Bitmap> bitmapList;
    private Context context;
    private SurfaceHolder holder;
    private Point coordinates;
    private double acceleration;
    private int speed;
    public int surfaceWidth;
    public int surfaceHeight;
    private int countFrameDraw;
    public final int framesLength = 20;
    public final int frameMoveDuration = 60;
    public final int size = 600;

    public Metroido(Context context, SurfaceHolder holder) {
        this.context = context;
        this.holder = holder;
        loadBitmaps();
        beginBehavior();
    }

    private void loadBitmaps() {
        if (bitmapList == null) {
            bitmapList = new ArrayList<>();
            for (int i = 0; i < framesLength; i++) {
                bitmapList.add(BitmapFactory.decodeResource(context.getResources(),
                        context.getResources().getIdentifier("frame_" + i, "mipmap", context.getPackageName())));
            }
        }
    }

    private int bitmapIteration;
    public Bitmap getMetroidoBitmap() {
        bitmapIteration++;
        if (bitmapIteration == 7) {
            addCountFrame();
            bitmapIteration = 0;
        }
        return bitmapList.get(countFrameDraw);
    }

    private void addCountFrame() {
        countFrameDraw++;
        if (countFrameDraw >= framesLength) {
            countFrameDraw = 0;
        }
    }

    public void beginBehavior() {
        speed = 10;
        acceleration = 0.1;
        coordinates = new Point();
        coordinates.x = (surfaceWidth - size) >> 1;
        coordinates.y = (surfaceHeight - size) >> 1;

        draw();
        doBehavior();
    }

    private void doBehavior() {
        //moveStraightBehavior();
        //hoveringBehavior();
        moveForward(random.nextInt(360));
    }

    private void moveStraightBehavior() {
        coordinates.x += speed * acceleration;


        verifyWallLimits();
        Log.i("metroido", String.valueOf(acceleration));
    }

    private void changeDirection() {

    }

    private void verifyWallLimits() {
        final int padding = 20;
        if (coordinates.x < padding ||
                coordinates.x + size > surfaceWidth - padding ||
                coordinates.y <= padding ||
                coordinates.y + size >= surfaceHeight - padding) {

            speed *= -1;
        }
    }

    private Random random = new Random();
    private double angle;
    private void hoveringBehavior() {
        coordinates.x += speed * getAcceleration();
        if (acceleration - 0.1 <= 0.2) {
            if (angle == 0) {
                angle = Math.PI/random.nextInt(90);
                //Point point =
            }
            //change direction
        }
    }

    private void attackBehavior() {

    }

    private void moveForward(float angle) {
        coordinates.x += speed * Math.sin(angle);
        coordinates.y += speed * Math.cos(angle);
    }

    private final double MAX_ACCERLERATION = 2;
    private double accelerationValue = 0.1;
    private double getAcceleration() {
        acceleration += accelerationValue;
        if (acceleration >= 2 || acceleration <= 0.1) {
            accelerationValue *= -1;
        }

        return acceleration;
    }

    public void draw() {
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            canvas.save();
            Bitmap bitmap = getMetroidoBitmap();

            doBehavior();
            int cx = coordinates.x;
            int cy = coordinates.y;

            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(bitmap, null, new Rect(cx, cy, cx + size, cy + size), null);
            canvas.restore();
            holder.unlockCanvasAndPost(canvas);

            postDraw();
        }
    }

    private void postDraw() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                draw();
            }
        }, frameMoveDuration);
    }
}
