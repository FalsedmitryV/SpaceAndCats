package com.example.spaceandcats;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.nfc.Tag;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable
{
    public static int maxX = 20;
    public static int maxY = 28;
    public static float unitW = 0;
    public static float unitH = 0;

    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Cat cat;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private ArrayList<EvilCat> evilCats = new ArrayList<>();
    private final int EVILCAT_INTERVAL = 50;
    private int currentTime = 0;


    public GameView(Context context)
    {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();

        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        while (gameRunning)
        {
            update();
            draw();
            checkCollision();
            checkIfNewEvilCat();
            control(17);
        }
    }

    private void update()
    {
        if(!firstTime)
        {
            cat.update();

            for (EvilCat evilCat : evilCats)
            {
                evilCat.update();
            }
        }
    }

    private void draw()
    {
        if (surfaceHolder.getSurface().isValid())
        {
            if(firstTime)
            {
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX;
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;

                cat = new Cat(getContext());
            }

            canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(Color.BLACK); // заполняем фон чёрным

            cat.drow(paint, canvas); // рисуем Кота

            for (EvilCat evilCat : evilCats)
            {
                evilCat.drow(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void control(int millis) // пауза на 17 миллисекунд
    {
        try
        {
            gameThread.sleep(millis);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void checkCollision()
    {
        for (EvilCat evilCat : evilCats)
        {
            if(evilCat.isCollision(cat.x, cat.y, cat.size))
            {
                gameRunning = false;
            }
            if(cat.getHeals() == 0)
            {


            }
            String TAG = "FDE";
            Log.d(TAG, String.valueOf(cat.getHeals()));
        }
    }

    private void checkIfNewEvilCat()    // каждые 50 итераций добавляем нового кота
    {
        if(currentTime >= EVILCAT_INTERVAL)
        {
            EvilCat asteroid = new EvilCat(getContext());
            evilCats.add(asteroid);
            currentTime = 0;
        }else
        {
            currentTime++;
        }
    }
}
