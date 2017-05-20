package com.AntifragileDev.alfabetoenespaol.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arangel on 5/20/14.
 */
public class ColorActivity extends Activity {
    private static final String TAG = "ColorActivity";
    public static List<String> color;
    public static Integer index;
    public static MediaPlayer mediaPlayer;
    public static ArrayHandler arrayHandler;
    public static Typeface notoFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        index = 0;
        int resId = getResources().getIdentifier("red", "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, resId);
        mediaPlayer.start();

        TextView colorView = (TextView) findViewById(R.id.colorTextView);
        notoFont = Typeface.createFromAsset(getAssets(),"fonts/NotoSans-Regular.ttf" );

        TextView descriptionView = (TextView) findViewById(R.id.colorDescriptionTextView);
        descriptionView.setTypeface(notoFont);

        colorView.setTypeface(notoFont);

        int colorCode = getResources().getColor(R.color.red);
        colorView.setTextColor(colorCode);

        arrayHandler = new ArrayHandler();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }


    // This example shows an Activity, but you would use the same approach if
// you were subclassing a View.
    @Override
    public boolean onTouchEvent(MotionEvent event){


        switch(event.getActionMasked()) {
            case (MotionEvent.ACTION_DOWN) :
                return true;
            case (MotionEvent.ACTION_MOVE) :
                determineDirectionOfSwipe(event.getX(), Boolean.FALSE);
                return true;
            case (MotionEvent.ACTION_UP) :
                determineDirectionOfSwipe(event.getX(), Boolean.TRUE);
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                return true;
            default :
                return super.onTouchEvent(event);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void determineDirectionOfSwipe(Float xCoordinate, Boolean isFinished) {
        if (color == null) {
            color = new ArrayList<String>();
        }

        if (isFinished == Boolean.FALSE){
            color.add(Float.toString(xCoordinate));
            //Log.d("this is getting called", Integer.toString(letter.size()));
        } else {

            if (color.size() > 0){
                String lastObject = color.get(color.size() - 1);

                //Log.d("First Object " + letter.get(0), "Last Object " + lastObject);
                displayChosenLetter(color.get(0), lastObject);
                color = new ArrayList<String>();
            }
        }

    }

    public void displayChosenLetter(String firstObject, String lastObject){
        mediaPlayer.release();
        Float firstFloat = Float.parseFloat(firstObject);
        Float lastFloat = Float.parseFloat(lastObject);


        int blue = getResources().getColor(R.color.blue);
        int red = getResources().getColor(R.color.red);
        int orange = getResources().getColor(R.color.orange);
        int yellow = getResources().getColor(R.color.yellow);
        int green = getResources().getColor(R.color.green);
        int purple = getResources().getColor(R.color.purple);
        int black = getResources().getColor(R.color.black);
        int white = getResources().getColor(R.color.white);
        int brown = getResources().getColor(R.color.brown);
        int grey = getResources().getColor(R.color.grey);

        String[] colorArray = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Black", "White", "Brown", "Grey"};
        String[] descriptionArray = {"Rojo", "Naranja", "Amarillo", "Verde", "Azul", "Morado", "Negro", "Blanco", "Marrón", "Gris"};
        String[] audioClipArray = {"red", "orange", "yellow", "green", "blue", "purple", "black", "white", "brown2", "grey"};
        //int[] colorCodeArray = {red, orange, yellow, green, blue, purple, black, white, brown, grey};




        TextView colorView = (TextView) findViewById(R.id.colorTextView);
        View view = this.getWindow().getDecorView();

        TextView descriptionView = (TextView) findViewById(R.id.colorDescriptionTextView);
        descriptionView.setTypeface(notoFont);

        if (firstFloat - lastFloat > 0){

            Integer indexIn = index;
            index = arrayHandler.getArrayIndex("next", indexIn, colorArray.length);

            descriptionView.setText(descriptionArray[index]);

            int resId = getResources().getIdentifier(audioClipArray[index], "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(this, resId);
            mediaPlayer.start();

            switch (index){
                case 0:
                    colorView.setTextColor(red);
                    break;

                case 1:
                    colorView.setTextColor(orange);
                    break;

                case 2:
                    colorView.setTextColor(yellow);
                    break;

                case 3:
                    colorView.setTextColor(green);
                    break;

                case 4:
                    colorView.setTextColor(blue);
                    break;

                case 5:
                    colorView.setTextColor(purple);
                    break;

                case 6:
                    colorView.setTextColor(black);
                    break;

                case 7:
                    colorView.setTextColor(white);
                    break;

                case 8:
                    colorView.setTextColor(brown);
                    break;

                case 9:
                    colorView.setTextColor(grey);
                    break;
                default:
                    colorView.setTextColor(blue);
                    break;
            }


        } else {
            Integer indexIn = index;
            index = arrayHandler.getArrayIndex("previous", indexIn, colorArray.length);

            descriptionView.setText(descriptionArray[index]);

            int resId = getResources().getIdentifier(audioClipArray[index], "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(this, resId);
            mediaPlayer.start();

            switch (index){
                case 0:
                    colorView.setTextColor(red);
                    break;

                case 1:
                    colorView.setTextColor(orange);
                    break;

                case 2:
                    colorView.setTextColor(yellow);
                    break;

                case 3:
                    colorView.setTextColor(green);
                    break;

                case 4:
                    colorView.setTextColor(blue);
                    break;

                case 5:
                    colorView.setTextColor(purple);
                    break;

                case 6:
                    colorView.setTextColor(black);
                    break;

                case 7:
                    colorView.setTextColor(white);
                    break;

                case 8:
                    colorView.setTextColor(brown);
                    break;

                case 9:
                    colorView.setTextColor(grey);
                    break;
                default:
                    colorView.setTextColor(blue);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}
