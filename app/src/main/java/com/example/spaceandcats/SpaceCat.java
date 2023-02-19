package com.example.spaceandcats;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class SpaceCat
{
    protected float x;
    protected float y;
    protected float size;
    protected float speed;
    protected int heals;
    protected int bitmapId;
    protected Bitmap bitmap;

    void init(Context context) // сжимаем картинку до нужных размеров
    {
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(cBitmap, (int)(size * GameView.unitW), (int)(size * GameView.unitH), false);
        cBitmap.recycle();
    }

    void update(){}// тут будут вычисляться новые координаты


    void drow(Paint paint, Canvas canvas)
    {
        canvas.drawBitmap(bitmap, x*GameView.unitW, y*GameView.unitH, paint);
    }

    public int getHeals(){
        return heals;
    }

    public void setHeals(int x){
        heals = x;
    }
}
