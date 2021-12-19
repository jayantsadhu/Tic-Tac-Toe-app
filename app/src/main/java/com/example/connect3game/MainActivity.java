package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    //0: yellow, 1: red, 2: empty
    int clicked = 0;
    int activePlayer = 0;
    int[] clickedArr ={0,0,0,0,0,0,0,0,0};
    int[] status = {2,2,2,2,2,2,2,2,2};
    int[][] winner = {{0,1,2},{0,3,6},{0,4,8},{3,4,5},{6,7,8},{2,5,8},{2,4,6},{1,4,7}};
    int tag;

    public void undoClick(View view)
    {
        int id = getResources().getIdentifier("imageView"+(tag+1), "id",getPackageName());
        ((ImageView) findViewById(id)).setImageResource(0);
        //clicked--;
        clickedArr[tag] = 0;
        status[tag] = 2;
        activePlayer = (activePlayer==0) ? 1: 0;
    }


    public void newGame(View view)
    {
        for(int i=1;i<=9;i++)
        {
            int id = getResources().getIdentifier("imageView"+i, "id",getPackageName());
            ((ImageView) findViewById(id)).setImageResource(0);
            clickedArr[i-1] =0;
            status[i-1] = 2;
        }
        clicked = 0;
        activePlayer = 0;
    }


    public void dropin(View view) {
        clicked++;
        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(counter.getTag().toString());

        if(clickedArr[tag]==1)
            return;

        this.tag = tag;
        clickedArr[tag] = 1;
        status[tag] = activePlayer;

        if(activePlayer==0)
        {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        }
        else
        {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }

        counter.setTranslationY(-1000);
        counter.animate().translationYBy(1000).setDuration(100);
        if(clicked>=5)
        {
            for(int i=0;i<8;i++)
            {
                if(status[winner[i][0]]!=2 && status[winner[i][0]]==status[winner[i][1]] && status[winner[i][1]]==status[winner[i][2]])
                {
                    String message;
                    if(status[winner[i][0]]==0)
                        message = "Yellow Won";
                    else
                        message = "Red Won";
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                    //Arrays.setAll(clickedArr,index->1);
                    for(int j=0;j<9;j++)
                        clickedArr[j] = 1;
                    break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}