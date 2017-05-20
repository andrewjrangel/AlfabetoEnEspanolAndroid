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
 * Created by arangel on 5/20/14.
 */
public class NumberActivity extends Activity {
    private static final String TAG = "NumberActivity";
    public static List<String> number;
    public static Integer index;
    public static MediaPlayer mediaPlayer;
    public static ArrayHandler arrayHandler;
    public static Typeface notoFont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView numberView = (TextView) findViewById(R.id.numberTextView);
        TextView numberDescriptionView = (TextView) findViewById(R.id.numberDescriptionTextView);
        notoFont = Typeface.createFromAsset(getAssets(),"fonts/NotoSans-Regular.ttf" );

        numberView.setTypeface(notoFont);
        numberDescriptionView.setTypeface(notoFont);


        index = 0;
        int resId = getResources().getIdentifier("one", "raw", getPackageName());
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
        if (number == null) {
            number = new ArrayList<String>();
        }

        if (isFinished == Boolean.FALSE){
            number.add(Float.toString(xCoordinate));
            //Log.d("this is getting called", Integer.toString(letter.size()));
        } else {

            if (number.size() > 0){
                String lastObject = number.get(number.size() - 1);

                //Log.d("First Object " + letter.get(0), "Last Object " + lastObject);
                displayChosenLetter(number.get(0), lastObject);
                number = new ArrayList<String>();
            }
        }

    }

    public void displayChosenLetter(String firstObject, String lastObject){
        mediaPlayer.release();
        Float firstFloat = Float.parseFloat(firstObject);
        Float lastFloat = Float.parseFloat(lastObject);

        String[] numberArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "30",
                                "40", "50", "60", "70", "80", "90", "100"};

        String[] descriptionArray = {"Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "Once", "Doce", "Trece", "Catorce", "Quínce", "Dieciséis",
                                     "Diecisiete", "Dieciocho", "Diecinueve", "Veinte", "Treinta", "Cuarenta", "Cincuenta", "Sesenta", "Setenta", "Ochenta", "Noventa", "Cien"};
        String[] audioClipArray = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
                                    "seventeen", "eightteen", "nineteen", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninty", "hundred"};


        TextView numberView = (TextView) findViewById(R.id.numberTextView);
        TextView numberDescriptionView = (TextView) findViewById(R.id.numberDescriptionTextView);

        if (firstFloat - lastFloat > 0){

            Integer indexIn = index;
            index = arrayHandler.getArrayIndex("next", indexIn, numberArray.length);
            numberView.setText(numberArray[index]);
            numberDescriptionView.setText(descriptionArray[index]);

            int resId = getResources().getIdentifier(audioClipArray[index], "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(this, resId);
            mediaPlayer.start();

        } else {

            Integer indexIn = index;
            index = arrayHandler.getArrayIndex("previous", indexIn, numberArray.length);
            numberView.setText(numberArray[index]);
            numberDescriptionView.setText(descriptionArray[index]);

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
