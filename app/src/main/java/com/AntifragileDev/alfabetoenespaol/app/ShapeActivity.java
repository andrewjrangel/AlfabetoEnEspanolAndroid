package com.AntifragileDev.alfabetoenespaol.app;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arangel on 5/19/14.
 */
public class ShapeActivity extends Activity {
    private static final String TAG = "ShapeActivity";
    public static List<String> shape;
    public static Integer index;
    public static MediaPlayer mediaPlayer;
    public static ArrayHandler arrayHandler;
    public static Typeface notoFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shape_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView shapeView = (TextView) findViewById(R.id.shapeTextView);
        TextView shapeDescriptionView = (TextView) findViewById(R.id.shapeDescriptionTextView);
        notoFont = Typeface.createFromAsset(getAssets(),"fonts/NotoSans-Regular.ttf" );

        shapeView.setTypeface(notoFont);
        shapeDescriptionView.setTypeface(notoFont);

        index = 0;
        int resId = getResources().getIdentifier("circle", "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, resId);
        mediaPlayer.start();

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
        if (shape == null) {
            shape = new ArrayList<String>();
        }

        if (isFinished == Boolean.FALSE){
            shape.add(Float.toString(xCoordinate));
            //Log.d("this is getting called", Integer.toString(letter.size()));
        } else {

            if (shape.size() > 0){
                String lastObject = shape.get(shape.size() - 1);

                //Log.d("First Object " + letter.get(0), "Last Object " + lastObject);
                displayChosenLetter(shape.get(0), lastObject);
                shape = new ArrayList<String>();
            }
        }

    }

    public void displayChosenLetter(String firstObject, String lastObject){
        mediaPlayer.release();
        Float firstFloat = Float.parseFloat(firstObject);
        Float lastFloat = Float.parseFloat(lastObject);

        String circle = getString(R.string.circle);
        String square = getString(R.string.square);
        String star = getString(R.string.star);
        String diamond = getString(R.string.diamond);
        String triangle = getString(R.string.triangle);

        String[] shapeArray = {circle, star, square, triangle, diamond};
        String[] descriptionArray = {"Círculo", "Estrella", "Cuadrada", "Triángulo", "Rombo"};
        String[] audioClipArray = {"circle", "star", "square", "triangle", "diamond"};


        TextView shapeView = (TextView) findViewById(R.id.shapeTextView);
        TextView shapeDescriptionView = (TextView) findViewById(R.id.shapeDescriptionTextView);

        if (firstFloat - lastFloat > 0){

            Integer indexIn = index;
            index = arrayHandler.getArrayIndex("next", indexIn, shapeArray.length);
            shapeView.setText(shapeArray[index]);
            shapeDescriptionView.setText(descriptionArray[index]);

            int resId = getResources().getIdentifier(audioClipArray[index], "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(this, resId);
            mediaPlayer.start();

        } else {

            Integer indexIn = index;
            index = arrayHandler.getArrayIndex("previous", indexIn, shapeArray.length);
            shapeView.setText(shapeArray[index]);
            shapeDescriptionView.setText(descriptionArray[index]);

            int resId = getResources().getIdentifier(audioClipArray[index], "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(this, resId);
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}
