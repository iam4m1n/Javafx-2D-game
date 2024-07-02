package com.example.demo1;

import java.lang.Thread;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Main extends Application {



    public static void main(String[] args) {

        launch(args);


    }


    // Game
    @Override
    public void start(Stage primaryStage){

        Group root = new Group();
        Scene firstScene =new Scene(root, 600, 600,Color.DARKSLATEBLUE);
        Image bg = new Image("bg_01.jpg");
        ImageView bgView = new ImageView(bg);
        bgView.setX(0);
        bgView.setY(0);
        bgView.setFitHeight(720);
        bgView.setFitWidth(1280);
        root.getChildren().add(bgView);



        //adding Line top of the screen so it's looks like an android device
        Line line = new Line();
        line.setStartX(-1);
        line.setEndX(1281);
        line.setStartY(0);
        line.setEndY(0);
        line.setStrokeWidth(80);
        line.setStroke(Color.BLACK);
        line.setOpacity(0.5);
        line.setRotate(0);
        root.getChildren().add(line);


//
////        add Rectangle
//        ArrayList<Box> ground = new ArrayList<>();
//
//        for (int i = 0; i < 11; i++) {
//            ground.add(new Box());
//            ground.get(i).setX(i*125);
//            ground.get(i).setY(600);
//            ground.get(i).setWidth(150);
//            ground.get(i).setHeight(90);
//            ground.get(i).setFill(Color.CYAN);
//            ground.get(i).setStrokeWidth(5);
//            ground.get(i).setStroke(Color.LIGHTCYAN);
//            root.getChildren().add(ground.get(i));
//
//        }


//     stay and moving grounds
        ArrayList<ImageView> ice_grounds= new ArrayList<>();
        Ice_ground iceGround = new Ice_ground("ice-ground_02.png");
        for (int i = 0; i < 21; i++) {
            ice_grounds.add(new ImageView(iceGround));
            ice_grounds.get(i).setX(i*160);
            ice_grounds.get(i).setY(600);
            ice_grounds.get(i).setFitWidth(160);
            ice_grounds.get(i).setFitHeight(90);
            root.getChildren().add(ice_grounds.get(i));

        }

        ArrayList<ImageView> ice_grounds_reset= new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ice_grounds_reset.add(new ImageView(iceGround));
            ice_grounds_reset.get(i).setX(i*160);
            ice_grounds_reset.get(i).setY(600);
            ice_grounds_reset.get(i).setFitWidth(160);
            ice_grounds_reset.get(i).setFitHeight(90);
            root.getChildren().add(ice_grounds_reset.get(i));

        }



        //add player
        Player Am1n = new Player("player_01.png");
        ImageView playerView = new ImageView(Am1n);
        playerView.setX(315);
        playerView.setY(600 - 185);
        playerView.setFitWidth(156);
        playerView.setFitHeight(189);
        root.getChildren().add(playerView);



        // add first two enemies

        Robot midRobot = new Robot("enemy_423.png");
        ImageView midRobotView = new ImageView(midRobot);
        midRobotView.setX(8 * 160 + 10 );
        midRobotView.setY(600 - 165);
        midRobotView.setFitWidth(122);
        midRobotView.setFitHeight(170);
        midRobotView.setScaleX(-1);
        root.getChildren().add(midRobotView);

        ImageView midRobotView2 = new ImageView(midRobot);
        midRobotView2.setX(4 * 160 + 10 );
        midRobotView2.setY(600 - 165);
        midRobotView2.setFitWidth(122);
        midRobotView2.setFitHeight(170);
        midRobotView2.setScaleX(-1);
        root.getChildren().add(midRobotView2);

        ArrayList<ImageView> blocks = new ArrayList<>();

        blocks.add(midRobotView2);
        blocks.add(midRobotView);

        // ScoreBoard
        final int[] Score = {0};
        Text ScoreBoard = new Text("" + Score[0]);
        ScoreBoard.setY(100);
        ScoreBoard.setX(120);
        ScoreBoard.setFont(new Font(20));

        root.getChildren().add(ScoreBoard);

        Image Score_bot = new Image("Score_bot2.png");
        ImageView Score_bot_View = new ImageView(Score_bot);
        Score_bot_View.setY(50);
        Score_bot_View.setX(10);
        Score_bot_View.setOpacity(0.8);


        Score_bot_View.setFitHeight(102);
        Score_bot_View.setFitWidth(102);


        root.getChildren().add(Score_bot_View);





        ImageView healthBar = new ImageView(Player.fullHealth);
        healthBar.setX(1280 - 200);
        healthBar.setFitWidth(180);
        healthBar.setFitHeight(80);
        healthBar.setY(30);


        root.getChildren().add(healthBar);




        // make fake player for get the y position while jumping and avoid the double and triple jump
        Player fake = new Player("player_fake.png");
        ImageView fakeView = new ImageView(fake);
        fakeView.setX(playerView.getX());
        fakeView.setY(playerView.getY());
        root.getChildren().add(fakeView);


        // avoiding multibtn press at the same time
//        final int[] xLimit = {0};
        final boolean[] pressedKeys = {false};
        final boolean[] releasedKeys = {true};

        final int DEBOUNCE_DELAY = 900;
        final long[] lastPressTime = {0};



        root.setOnKeyPressed((new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                //avoid multi key press
                long currentTime = System.currentTimeMillis();
                if(currentTime - lastPressTime[0] > DEBOUNCE_DELAY) {
                    lastPressTime[0] = currentTime;


                    if(Player.checkForDamage() > 2){

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(
                                "Game Over"
                        );

                        alert.setHeaderText("You Got " + Score[0] + " score and you died !!!!");

                        alert.setContentText("press ok to end the game");

                        alert.showAndWait();

                        Platform.exit();

                    }


                    // handling key events
                    switch (keyEvent.getCode()) {

                        case A:

                            Controller att = new Controller(playerView);
                            Score[0] = att.Attack(playerView, blocks, Score[0]);


                            Timeline sleep = new Timeline(new KeyFrame(Duration.seconds(0.8), e -> {
                                ScoreBoard.setText("" + Score[0]);
                            }));
                            sleep.play();

                            break;

                        case W:

                            if (ice_grounds_reset.get(0).isVisible()){

                            Timeline tl_chEnemies = new Timeline(
                                    new KeyFrame(Duration.millis(2000), e -> {

                                        Robot.changeEnemy(Score[0], blocks);

                                    }));
                            tl_chEnemies.play();


                            for (int i = 0; i < blocks.size() - 1; i++) {
                                if (blocks.get(i).getX() == blocks.get(i + 1).getX() ){
                                    blocks.get(i+1).setX(blocks.get(i).getX() + 160);
                                }
                            }


                            for (int i = 0; i < 9; i++) {
//                                ground.get(i).setVisible(false);
                                ice_grounds_reset.get(i).setVisible(false);
                            }


                            if (fakeView.getY() > 400 ) {
                                Controller movU = new Controller(playerView);
                                movU.up(playerView, blocks, healthBar);


                                Ice_ground.moveGround(ice_grounds);

                                for (ImageView enemy :
                                        blocks) {
                                    enemy.setX(enemy.getX() - 160);

                                }


                                if (blocks.size() < 3) {
                                    Random randomX = new Random();
                                    int randomXX = 160 * randomX.nextInt((5) + 3);
                                    int index = blocks.size() - 1;

                                    blocks.add(new ImageView(midRobot));
                                    blocks.get(index).setImage(Robot.whichEnemy(Score[0]));
                                    if (blocks.get(blocks.size() - 2).getX() == randomXX)
                                        randomXX += 160;

                                    blocks.get(blocks.size() - 1).setX(randomXX + blocks.get(blocks.size() - 2).getX());
                                    blocks.get(blocks.size() - 1).setY(600 - 165);
                                    blocks.get(blocks.size() - 1).setFitWidth(122);
                                    blocks.get(blocks.size() - 1).setFitHeight(171);
                                    blocks.get(blocks.size() - 1).setScaleX(-1);
                                    root.getChildren().add(blocks.get(blocks.size() - 1));
                                }


                                if (blocks.get(0).getX() < -100) {
                                    blocks.remove(0);
                                }

                                System.out.println(blocks.size());

                                Timeline tl = new Timeline(
                                        new KeyFrame(Duration.millis(400), e -> {

                                            for (int i = 0; i < 9; i++) {
                                                ice_grounds_reset.get(i).setVisible(true);
                                            }

                                        }));
                                tl.play();


                                }

                            }
                            break;


                        default:
                            System.out.println(keyEvent.getCode());
                    }

                }

            }
        }));


        root.setOnKeyReleased(keyEvent -> {
            if (pressedKeys[0]) {
                pressedKeys[0] = false;
                releasedKeys[0] = true;
            }
        });





        Button btnRight = new Button("Right");
//        EventHandler<ActionEvent> eventR = new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//
//            }
//
//        };
//        btnRight.setOnAction(eventR);
        btnRight.setLayoutX(-400);
        root.getChildren().add(btnRight);



//      add the fav icon
        Image fav_icon = new Image("fav-icon.png");
        primaryStage.getIcons().add(fav_icon);


        // Stage setting
        primaryStage.setTitle("Vikings vs Robots");
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setResizable(false);




        primaryStage.setScene(firstScene);
        primaryStage.show();

    }



}
