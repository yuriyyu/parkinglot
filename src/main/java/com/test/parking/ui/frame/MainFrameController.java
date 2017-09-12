/*
 * Copyright (c) 2011, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.test.parking.ui.frame;
 
import java.util.ArrayList;

import com.test.parking.core.GroundParkingLot;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class MainFrameController {
    
    @FXML
    protected void handleReserveSpotButtonAction(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("reservation_screen.fxml"));
        Scene mainScene = new Scene(root, 600, 400);
    	
        primaryStage.setTitle("Parking Lot");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    
    @FXML protected void handleSignInButtonAction(ActionEvent event) throws Exception {
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	Parent loginFrame = FXMLLoader.load(getClass().getClassLoader().getResource("loginFrame.fxml"));
    	
    	stage.setTitle("Login");
        stage.setScene(new Scene(loginFrame, 300, 275));
        
    }
    
    @FXML protected void handleLoginButtonAction(ActionEvent event) throws Exception {
        ArrayList<GroundParkingLot> parkingLots = new ArrayList<GroundParkingLot>();
    	parkingLots.add(new GroundParkingLot(0, "Washington, First st.", 1, 2));
    	parkingLots.add(new GroundParkingLot(1, "Washington, Second st.", 3, 4));
    	parkingLots.add(new GroundParkingLot(2, "Washington, Third st.", 5, 6));
        
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

    	stage.setTitle("Parking Lots");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(parkingLots.size());
        grid.setVgap(3);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text idLabel = new Text("ID");
        idLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        grid.add(idLabel, 0, 0);

        Text addressLabel = new Text("Address");
        addressLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        grid.add(addressLabel, 1, 0);

        for(int i = 0; i < parkingLots.size(); i++) {
            String id = Integer.toString(parkingLots.get(i).getId());
            Text idText = new Text(id);
            idText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
            grid.add(idText, 0, i + 1);

            Text addressText = new Text(parkingLots.get(i).getAddress());
            addressText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
            grid.add(addressText, 1, i + 1);

            Button btn = new Button("View");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn, 2, i + 1);
            
            btn.setOnAction(evt -> {
                Parent loginFrame;
                try {
                    loginFrame = FXMLLoader.load(getClass().getClassLoader().getResource("parking_lot_admin.fxml"));
                    stage.setTitle("Parking Lot " + id);
                    stage.setScene(new Scene(loginFrame, 600, 400));
                } catch (IOException ex) {
                    Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
        }

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(100);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPrefWidth(100);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPrefWidth(100);
        grid.getColumnConstraints().addAll(col1, col2, col3);

        Scene scene = new Scene(grid, 400, 500);
    	stage.setTitle("Parking Lots");
        stage.setScene(scene);
        
    }
    
    @FXML protected void handleChangeTariffsButtonAction(ActionEvent event) throws Exception {
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Group root = new Group();
        Scene scene = new Scene(root, 400, 250, Color.WHITE);

        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-tab-min-width: 180px;");

        BorderPane borderPane = new BorderPane();
        
        Label lCar = new Label("Cars");
        Label lTruck = new Label("Trucks");
        Label lMoto = new Label("Moto");
        Label lDisabled = new Label("Disabled");
        
        TextField carsField = new TextField("10");
        TextField trucksField = new TextField("20");
        TextField motoField = new TextField("30");
        TextField disabledField = new TextField("40");

        
        Tab normalTab = new Tab();
        normalTab.setText("Normal");
        HBox hbox1 = new HBox();
        VBox vbox1 = new VBox(5);
        VBox vbox2 = new VBox(5);
        //vbox1.setPadding(new Insets(1));
        vbox1.getChildren().addAll(lCar, lTruck, lMoto, lDisabled);
        vbox2.getChildren().addAll(carsField, trucksField, motoField, disabledField);
        hbox1.getChildren().addAll(vbox1, vbox2);
        normalTab.setClosable(false);
        normalTab.setContent(hbox1);
        tabPane.getTabs().add(normalTab);
        
        Label lCar2 = new Label("Cars");
        lCar2.setFont(new Font("Tahoma", 18));
        Label lTruck2 = new Label("Trucks");
        lTruck2.setFont(new Font("Tahoma", 18));
        Label lMoto2 = new Label("Moto");
        lMoto2.setFont(new Font("Tahoma", 18));
        Label lDisabled2 = new Label("Disabled");
        lDisabled2.setFont(new Font("Tahoma", 18));
        
        TextField carsField2 = new TextField("20");
        TextField trucksField2 = new TextField("40");
        TextField motoField2 = new TextField("60");
        TextField disabledField2 = new TextField("80");
        

        Tab holidayTab = new Tab();
        holidayTab.setText("Holiday");
        HBox hbox2 = new HBox();
        VBox vbox3 = new VBox(5);
        vbox3.setPadding(new Insets(40, 40, 40, 40));
        vbox3.setSpacing(10);
        VBox vbox4 = new VBox(5);
        vbox4.setPadding(new Insets(40, 40, 40, 40));
        vbox4.setSpacing(10);
        //vbox1.setPadding(new Insets(1));
        vbox3.getChildren().addAll(lCar2, lTruck2, lMoto2, lDisabled2);
        vbox4.getChildren().addAll(carsField2, trucksField2, motoField2, disabledField2);
        hbox2.getChildren().addAll(vbox3, vbox4);
        holidayTab.setClosable(false);
        holidayTab.setContent(hbox2);
        tabPane.getTabs().add(holidayTab);
        
        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        stage.setScene(scene);
        
    	stage.setTitle("Change Tariffs");
        stage.setScene(scene);
        
    }

}
