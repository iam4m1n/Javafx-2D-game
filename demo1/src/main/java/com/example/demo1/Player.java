package com.example.demo1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class Player extends Image {

    public Player(String s) {
        super(s);
    }



    static Image damage1 = new Image("player_angry_01.png");


    static Image fullHealth = new Image("triple_heart.png");
    static Image halfeHealth = new Image("triple_heart_02.png");
    static Image lowHealth = new Image("triple_heart_03.png");

    static Image noHealth = new Image("triple_heart_04.png");

    static int currentHealth = 0;
    static Image[] health = {fullHealth, halfeHealth, lowHealth, noHealth};

    public static void checkForDamage(ArrayList<ImageView> blocks, ImageView player, ImageView ScoreBoard){

        for (ImageView block:
             blocks) {

            if (block.getX() == 330.0) {
//                System.out.println("static :" + blocks.get(0).getX());
                player.setImage(new Image("player_angry_01.png"));
                if (currentHealth < 3) {
                    currentHealth++;
                    ScoreBoard.setImage(health[currentHealth]);

                }
            }
        }


    }

    public static int checkForDamage() {
        return currentHealth;
    }



    public String set_skin(int mode) {
        if (mode == 1) {


        }

        return "";
    }
}
