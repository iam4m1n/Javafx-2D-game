package com.example.demo1;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class Ice_ground extends Image {
    public Ice_ground(String s){
        super(s);
    }


    public static void moveGround(ArrayList<ImageView> gr){
        for (ImageView ice:
                gr) {
            TranslateTransition translate_ground = new TranslateTransition();
            translate_ground.setNode(ice);
            translate_ground.setDuration(Duration.millis(400));
            translate_ground.setAutoReverse(true);
            translate_ground.setInterpolator(Interpolator.LINEAR);
            translate_ground.setCycleCount(2);
            translate_ground.setByX(-160);
            translate_ground.play();
        }
    };


}

