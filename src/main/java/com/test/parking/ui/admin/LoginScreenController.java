/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.ui.admin;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.services.ParkingLotService;
import com.test.parking.ui.MainScreenController;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author 986056
 */
@Component
public class LoginScreenController {

    @Autowired
    private ConfigurableApplicationContext springContext;
    @Autowired
    private ParkingLotService parkingLotService;

    @FXML protected void handleLoginButtonAction(ActionEvent event) throws Exception {
        List<ParkingLot> parkingLots = parkingLotService.getParkingLots();

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
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/parking_lot_admin_screen.fxml"));
                    fxmlLoader.setControllerFactory(springContext::getBean);
                    loginFrame = fxmlLoader.load();
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
