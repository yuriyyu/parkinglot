/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.ui.admin;

import com.test.parking.core.models.GroundParkingLot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author 986056
 */
public class LoginScreenController {
    
    @FXML protected void handleLoginButtonAction(ActionEvent event) throws Exception {
        ArrayList<GroundParkingLot> parkingLots = new ArrayList<GroundParkingLot>();
    	parkingLots.add(new GroundParkingLot(0, "Washington, First st.", 20));
    	parkingLots.add(new GroundParkingLot(1, "Washington, Second st.", 20));
    	parkingLots.add(new GroundParkingLot(2, "Washington, Third st.", 20));
        
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
                    loginFrame = FXMLLoader.load(getClass().getClassLoader().getResource("parking_lot_admin_screen.fxml"));
                    stage.setTitle("Parking Lot " + id);
                    stage.setScene(new Scene(loginFrame, 600, 400));
                } catch (IOException ex) {
                    Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
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
    
}
