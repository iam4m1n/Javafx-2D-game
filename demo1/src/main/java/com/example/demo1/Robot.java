package com.example.demo1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Robot extends Image {
    public Robot(String s){
        super(s);
    }

    static Image enemy1 = new Image("enemy_423.png");
    static Image enemy2 = new Image("enemy_422.png");
    static Image enemy3 = new Image("enemy_424.png");
    static Image enemy4 = new Image("enemy_425.png");
    static Image enemy5 = new Image("enemy_426.png");



    static public void changeEnemy(int Score, ArrayList<ImageView> enemies){
        if (Score < 5) {
            for (ImageView r :
                    enemies) {
                r.setImage(enemy1);
            }
        }else if(Score < 10){
            for (ImageView r :
                    enemies) {
                r.setImage(enemy2);
            }
        } else if (Score < 15) {
            for (ImageView r :
                    enemies) {
                r.setImage(enemy3);
            }
        } else if (Score < 20) {
            for (ImageView r :
                    enemies) {
                r.setImage(enemy4);
            }
        } else{
            for (ImageView r :
                    enemies) {
                r.setImage(enemy5);
            }
        }
    }

    static Image whichEnemy(int Score){
        if (Score < 5)
            return enemy1;
        else if(Score < 10)
            return enemy2;
        else if (Score < 15)
            return enemy3;
        else if (Score < 20)
            return enemy4;
        else
            return enemy5;

    }


}
