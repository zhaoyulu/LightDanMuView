package com.example.zyl.lightdanmu;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class TestView extends SurfaceView implements Callback {
    private SurfaceHolder sfh;
    private Canvas canvas;
    private Paint paint;

    public TestView(Context context) {
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
// TODO Auto-generated method stub

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        for (int i = 0; i < 10; i++) {
            canvas = sfh.lockCanvas();
            if (canvas != null) {
                canvas.drawText("" + i, 10, 20 * i, paint);
            }
            sfh.unlockCanvasAndPost(canvas);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
// TODO Auto-generated method stub

    }

}