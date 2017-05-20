package com.AntifragileDev.alfabetoenespaol.app;

import android.util.Log;

/**
 * Created by arangel on 5/18/14.
 */
public class ArrayHandler {


    public Integer getArrayIndex(String nextOrPrevious, Integer index, Integer arrayLength){
        if (nextOrPrevious == "next"){
            if (index == 0){
                return 1;

            } else if (index == arrayLength -1){
                return 0;

            } else if (index >=1 && index <= arrayLength - 2){
                index++;
                return index;

            } else if (index < 0){
                index = 0;
                return index;
            }
        } else {
            if (index == 1){
                index--;
                return 0;


            } else if (index == 0) {
                return arrayLength-1;

            } else if (index >=2 && index <= arrayLength -2){
                index--;
                return index;


            } else if (index <= arrayLength -1){
                return arrayLength-2;
            }
        }

        return 1;
    }
}
