/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.ui.admin;

import com.test.parking.core.models.GroundParkingLot;
import com.test.parking.core.models.tariffs.HolidayTariff;
import com.test.parking.core.models.tariffs.NormalTariff;
import com.test.parking.core.models.tariffs.Tariff;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author 986056
 */
public class ParkingLotAdminScreenController {
    
    @FXML protected void handleChangeTariffsButtonAction(ActionEvent event) throws Exception {
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        String title = stage.getTitle();
        
        Group root = new Group();
        Scene scene = new Scene(root, 400, 250, Color.WHITE);

        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-tab-min-width: 180px;");

        BorderPane borderPane = new BorderPane();
        
        Tab normalTab = createTab(new NormalTariff(), stage, title);
        Tab holidayTab = createTab(new HolidayTariff(), stage, title);
        
        tabPane.getTabs().add(normalTab);
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
    
    @FXML
    protected void handleBackButtonAction(ActionEvent event) throws Exception {
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
    
    private Tab createTab(Tariff tariff, Stage stage, String title) {
        //price will be recieved from tariff
        String[] price = {"10", "20", "30", "40"};
        //type of tariff
        String type;
        
        if(tariff instanceof NormalTariff) {
            type = "Normal";
        } else {
            type = "Holiday";
            price[0] = "20"; price[1] = "40"; price[2] = "60"; price[3] = "80";
        }
        
        Label lCar = new Label("Cars");
        lCar.setFont(new Font("Tahoma", 18));
        Label lTruck = new Label("Trucks");
        lTruck.setFont(new Font("Tahoma", 18));
        Label lMoto = new Label("Moto");
        lMoto.setFont(new Font("Tahoma", 18));
        Label lDisabled = new Label("Disabled");
        lDisabled.setFont(new Font("Tahoma", 18));
        
        TextField carsField = new TextField(price[0]);
        TextField trucksField = new TextField(price[1]);
        TextField motoField = new TextField(price[2]);
        TextField disabledField = new TextField(price[3]);
        

        Tab tab = new Tab();
        tab.setText(type);
        HBox content = new HBox();
        VBox vehicleNames = new VBox(5);
        vehicleNames.setPadding(new Insets(48, 40, 48, 40));
        vehicleNames.setSpacing(10);
        VBox cost = new VBox(5);
        cost.setPadding(new Insets(40, 40, 40, 40));
        cost.setSpacing(10);
        
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.BOTTOM_RIGHT);
        hbBack.getChildren().add(back);

        //back to selected parking lot
        back.setOnAction(evt -> {
            Parent loginFrame;
            try {
                loginFrame = FXMLLoader.load(getClass().getClassLoader().getResource("parking_lot_admin_screen.fxml"));
                stage.setTitle(title);
                stage.setScene(new Scene(loginFrame, 600, 400));
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button submit = new Button("Submit");
        HBox hbSubmit = new HBox(10);
        hbSubmit.setAlignment(Pos.BOTTOM_RIGHT);
        hbSubmit.getChildren().add(submit);

        //back to selected parking lot
        submit.setOnAction(evt -> {
            //implement later
        });
        
        vehicleNames.getChildren().addAll(lCar, lTruck, lMoto, lDisabled, back);
        cost.getChildren().addAll(carsField, trucksField, motoField, disabledField, submit);
        content.getChildren().addAll(vehicleNames, cost);
        tab.setClosable(false);
        tab.setContent(content);
        
        return tab;
    }
}
