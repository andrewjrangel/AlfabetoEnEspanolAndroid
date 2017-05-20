package com.AntifragileDev.alfabetoenespaol.app;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import com.AntifragileDev.alfabetoenespaol.app.ArrayHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arangel on 5/17/14.
 */


public class AlphabetActivity extends Activity {
    private static final String TAG = "AlphabetActivity";
    public static List<String> letter;
    public static Integer index;
    public static MediaPlayer mediaPlayer;
    public static ArrayHandler arrayHandler;
    public static Typeface notoFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphabet_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView letterView = (TextView) findViewById(R.id.letterTextView);
        TextView descriptionView = (TextView) findViewById(R.id.descriptionTextView);
        notoFont = Typeface.createFromAsset(getAssets(),"fonts/NotoSans-Regular.ttf" );
        
        letterView.setTypeface(notoFont);
        descriptionView.setTypeface(notoFont);


        index = 0;
        int resId = getResources().getIdentifier("aclip", "raw", getPackageName());
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
        if (letter == null) {
             letter = new ArrayList<String>();
        }

        if (isFinished == Boolean.FALSE){
            letter.add(Float.toString(xCoordinate));
            //Log.d("this is getting called", Integer.toString(letter.size()));
        } else {

            if (letter.size() > 0){
                String lastObject = letter.get(letter.size() - 1);

                //Log.d("First Object " + letter.get(0), "Last Object " + lastObject);
                displayChosenLetter(letter.get(0), lastObject);
                letter = new ArrayList<String>();
            }
        }

    }

    public void displayChosenLetter(String firstObject, String lastObject){
        mediaPlayer.release();
        Float firstFloat = Float.parseFloat(firstObject);
        Float lastFloat = Float.parseFloat(lastObject);


        String[] letterArray = {"A", "B", "C", "Ch", "D", "E", "F", "G", "H", "I", "J", "K", "L", "Ll", "M", "N", "Ñ", "O", "P", "Q", "R","S", "T", "U", "V", "W", "X", "Y", "Z" };
        String[] descriptionArray = {"Agua", "Bolsa", "Cebra", "Chocolate", "Deportes", "Elafonte", "Flor", "Gato", "Helado", "Isla", "Juego", "Koala", "León", "Llave", "Manzana", "Noche", "Ñame",
        "Ojo","Perro", "Queso", "Ratón", "Sol", "Tiempo", "Uva", "Vestido", "Wok", "Xilofono", "Yac", "Zapatos"};
        String[] audioClipArray = {"aclip", "bclip", "cclip", "chclip", "dclip", "eclip", "fclip", "gclip", "hclip", "iclip", "jclip", "kclip", "lclip", "llclip", "mclip", "nclip", "enyeclip",
                "oclip", "pclip", "qclip", "rclip", "sclip", "tclip", "uclip", "vclip", "wclip", "xclip", "yclip", "zclip"};


        TextView letterView = (TextView) findViewById(R.id.letterTextView);
        TextView descriptionView = (TextView) findViewById(R.id.descriptionTextView);

        if (firstFloat - lastFloat > 0){

            Integer indexIn = index;
            index = arrayHandler.getArrayIndex("next", indexIn, letterArray.length);
            letterView.setText(letterArray[index]);
            descriptionView.setText(descriptionArray[index]);

            int resId = getResources().getIdentifier(audioClipArray[index], "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(this, resId);
            mediaPlayer.start();

        } else {

            Integer indexIn = index;
            index = arrayHandler.getArrayIndex("previous", indexIn, letterArray.length);
            letterView.setText(letterArray[index]);
            descriptionView.setText(descriptionArray[index]);

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

