package com.example.demo1;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;

public class Controller implements Initializable {

    Image jump1 = new Image("jump1.png");
    Image jump2 = new Image("jump2.png");
    Image jump3 = new Image("jump3.png");
    Image jump4 = new Image("jump4.png");
    Image onGround = new Image("player_01.png");



    public Controller(ImageView iv){
        imageView = iv;
    }
    @FXML
    private ImageView imageView;

    Image Attack1 = new Image("Attack_01.png");
    Image Attack2 = new Image("Attack_02.png");
    Image Attack3 = new Image("Attack_03.png");
    Image Attack4 = new Image("Attack_04.png");
    Image Attack5 = new Image("Attack_05.png");



    public int Attack(ImageView player, ArrayList<ImageView> enemies, int Score){

        player.setImage(Attack1);
        player.setFitWidth(200);
        player.setFitHeight(300);
        int xplusplus = 50;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> {
                    imageView.setImage(Attack2);
                    imageView.setX(315 + xplusplus);
                    imageView.setY(600 - 184);
                    imageView.setFitWidth(189);
                    imageView.setFitHeight(189);
                }),
                new KeyFrame(Duration.seconds(0.2), e -> {
                    imageView.setImage(Attack3);
                    imageView.setX(315 + xplusplus);
                    imageView.setY(600 - 184);
                    imageView.setFitWidth(189);
                    imageView.setFitHeight(189);
                }),
                new KeyFrame(Duration.seconds(0.4), e -> {
                    imageView.setImage(Attack4);
                    imageView.setX(315 + xplusplus);
                    imageView.setY(600 - 184);
                    imageView.setFitWidth(189);
                    imageView.setFitHeight(189);
                }),
                new KeyFrame(Duration.seconds(0.6), e -> {
                    imageView.setImage(Attack5);
                    imageView.setX(315 + xplusplus);
                    imageView.setY(600 - 184);
                    imageView.setFitWidth(189);
                    imageView.setFitHeight(189);

                    if (enemies.get(0).getX() < 500 && enemies.get(0).getX() > 400) {
                        enemies.get(0).setY(600 - 165 + 151);
                        enemies.get(0).setFitHeight(20);
                    }
                }),
                new KeyFrame(Duration.seconds(0.8), e -> {
                    imageView.setImage(onGround);
                    imageView.setX(315);
                    imageView.setY(600 - 184);
                    imageView.setFitWidth(156);
                    imageView.setFitHeight(189);

                    if (enemies.get(0).getX() < 500 && enemies.get(0).getX() > 400) {


                        enemies.get(0).setX(-400);
                        enemies.remove(0);

                    }
                })
        );
        timeline.play();

//        System.out.println(enemies.get(0).getX());

        for (ImageView enemy:
             enemies) {
            if(enemy.getX() < 500 && enemy.getX() > 400 ) {
                return ++Score;
            }
            else
                return Score;

        }



//
//        if(enemies.get(0).getX() < 500 && enemies.get(0).getX() > 400 ) {
//            return ++Score;
//        }
//        else
//            return Score;

        return 1;
    }

    public void up(ImageView player, ArrayList<ImageView> blocks, ImageView scoreBoard){
        player.setImage(jump1);
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(imageView);
        translate.setDuration(Duration.millis(200));
        translate.setCycleCount(2);
        translate.setInterpolator(Interpolator.LINEAR);
        translate.setAutoReverse(true);
        translate.setByY(-150);
        translate.play();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> imageView.setImage(jump2)),
                new KeyFrame(Duration.seconds(0.2), e -> imageView.setImage(jump3)),
                new KeyFrame(Duration.seconds(0.4), e -> imageView.setImage(jump4)),
                new KeyFrame(Duration.seconds(0.6), e -> imageView.setImage(onGround)),
                new KeyFrame(Duration.seconds(0.6), e -> Player.checkForDamage(blocks, player, scoreBoard))

        );
        timeline.play();

    }


    public void shiftToLeft(ArrayList<ImageView> enemy){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}


