package com.example.museum_new;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application
{
    private static final double WIDTH = 1080;
    private static final double HEIGHT = 2000;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/museum_new/hello-view.fxml"));
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Virtuelles Museum");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
