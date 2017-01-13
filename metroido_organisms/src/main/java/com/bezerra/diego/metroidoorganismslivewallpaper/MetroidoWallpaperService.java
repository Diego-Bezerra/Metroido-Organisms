package com.bezerra.diego.metroidoorganismslivewallpaper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Bezerra on 11/01/17.
 */

public class MetroidoWallpaperService extends WallpaperService {

    @Override
    public Engine onCreateEngine() {
        try {
            return new MetroidoWallpaperEngine();
        } catch(Exception ex) {
            return null;
        }
    }

    public class MetroidoWallpaperEngine extends Engine {

        private Metroido metroido;

        @Override
        public boolean isVisible() {
            return super.isVisible();
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            metroido = new Metroido(getBaseContext(),
                    surfaceHolder);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            metroido.surfaceWidth = width;
            metroido.surfaceHeight = height;
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            if (visible) {
                metroido.beginBehavior();
            } else {
            }
        }
    }
}
