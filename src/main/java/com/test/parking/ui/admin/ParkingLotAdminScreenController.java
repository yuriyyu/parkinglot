/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.ui.admin;

import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.tariffs.BikeTariff;
import com.test.parking.core.models.tariffs.CarTariff;
import com.test.parking.core.models.tariffs.DisabledTariff;
import com.test.parking.core.models.tariffs.Tariff;
import com.test.parking.core.models.tariffs.TruckTariff;
import com.test.parking.core.services.ParkingLotService;
import com.test.parking.core.services.TariffService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.test.parking.ui.MainScreenController;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author 986056
 */
@Component
public class ParkingLotAdminScreenController {
    @Autowired
    private ConfigurableApplicationContext springContext;

    @Autowired
    private ParkingLotService parkingLotService;
    @Autowired
    private TariffService tariffService;

    private int parkingLotId;

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    @FXML protected void handleChangeTariffsButtonAction(ActionEvent event) throws Exception {
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Group root = new Group();
        Scene scene = new Scene(root, 400, 250, Color.WHITE);

        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-tab-min-width: 180px;");

        BorderPane borderPane = new BorderPane();
        
        //TariffService tariffService = new TariffService(null, null, null);
        ArrayList<Tariff> tariffs = (ArrayList) tariffService.getParkingLotTariffs(parkingLotId);
        //ArrayList<Tariff> tariffs = (ArrayList) tariffService.getParkingLotTariffs(parkingLotId);
        
        ArrayList<Tariff> normalTariffs = new ArrayList<>();
        ArrayList<Tariff> holidayTariffs = new ArrayList<>();
        for(Tariff t : tariffs) {
            if(t.isHoliday()) {
                holidayTariffs.add(t);
            } else {
                normalTariffs.add(t);
            }
        }
        
        Tab normalTab = createTab(normalTariffs, "Normal", stage, ""+parkingLotId);
        Tab holidayTab = createTab(holidayTariffs, "Holiday", stage, ""+parkingLotId);
        
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
    
    private Tab createTab(ArrayList<Tariff> tariffs, String type, Stage stage, String title) throws Exception {
        
        Tariff carsTariff = tariffs.stream()
                    .filter(tariff -> tariff instanceof CarTariff)
                    .findFirst().orElseThrow(Exception::new);
        Tariff truckTariff = tariffs.stream()
                    .filter(tariff -> tariff instanceof TruckTariff)
                    .findFirst().orElseThrow(Exception::new);
        Tariff bikeTariff = tariffs.stream()
                    .filter(tariff -> tariff instanceof BikeTariff)
                    .findFirst().orElseThrow(Exception::new);
        Tariff disabledTariff = tariffs.stream()
                    .filter(tariff -> tariff instanceof DisabledTariff)
                    .findFirst().orElseThrow(Exception::new);
        
        Label lCar = new Label("Cars");
        lCar.setFont(new Font("Tahoma", 18));
        Label lTruck = new Label("Trucks");
        lTruck.setFont(new Font("Tahoma", 18));
        Label lMoto = new Label("Moto");
        lMoto.setFont(new Font("Tahoma", 18));
        Label lDisabled = new Label("Disabled");
        lDisabled.setFont(new Font("Tahoma", 18));
        
        TextField carsField = new TextField(Double.toString(carsTariff.getPrice()));
        TextField trucksField = new TextField(Double.toString(truckTariff.getPrice()));
        TextField motoField = new TextField(Double.toString(bikeTariff.getPrice()));
        TextField disabledField = new TextField(Double.toString(disabledTariff.getPrice()));
        

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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/parking_lot_admin_screen.fxml"));
                fxmlLoader.setControllerFactory(springContext::getBean);
                loginFrame = fxmlLoader.load();
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

    private void setTariffProperties(Tariff tariff, Double price, boolean isHoliday, ParkingLot parkingLot) {
        tariff.setPrice(price);
        tariff.setHoliday(isHoliday);
        tariff.setParkingLot(parkingLot);
    }
}
