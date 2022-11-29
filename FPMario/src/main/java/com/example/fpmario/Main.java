package com.example.fpmario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try {
            Game game = new Game();
            JFrame frame = new JFrame(game.TITLE);
            frame.add(game);
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            game.run();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    public static void main(String[] args)
    {
        launch();
    }


}