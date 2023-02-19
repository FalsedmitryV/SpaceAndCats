package com.example.spaceandcats;

import android.content.Context;
import android.widget.TextView;


public class Cat extends SpaceCat
{
    public Cat(Context context)
    {
        bitmapId = R.drawable.cat; // определяем начальные параметры
        size = 3;
        x=7;
        y=GameView.maxY - size - 1;
        speed = (float) 0.2;

        heals = 3;

        init(context); // инициализируем Кота
    }

    public void update()
    {
        if(MainActivity.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(MainActivity.isRightPressed && x <= GameView.maxX - 5){
            x += speed;
        }
    }
}
