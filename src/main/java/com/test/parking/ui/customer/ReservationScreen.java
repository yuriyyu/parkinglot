package com.test.parking.ui.customer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Yuriy Yugay on 9/11/2017.
 *
 * @author Yuriy Yugay
 */
public class ReservationScreen
        extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("reservation_screen.fxml"));

        Scene mainScene = new Scene(root, 600, 400);

        primaryStage.setTitle("Parking Lot");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(ReservationScreen.class, args);
    }
}
