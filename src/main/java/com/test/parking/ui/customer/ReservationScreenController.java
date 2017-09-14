package com.test.parking.ui.customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.services.ParkingLotService;
import com.test.parking.core.services.ParkingSlotService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


/**
 * Created by Yuriy Yugay on 9/11/2017.
 *
 * @author Yuriy Yugay
 */
@Component
public class ReservationScreenController
        implements Initializable {
    @Autowired
    private ConfigurableApplicationContext springContext;

    @FXML
    private Label dateLabel;
    @FXML
    private DatePicker datePicker;

    @FXML
    private GridPane gridSlots;

    @FXML
    private Pane bottomPane;
    @FXML
    private TextField vehicleNumberText;
    @FXML
    private TextField typeText;
    @FXML
    private TextField priceText;
    @FXML
    private ChoiceBox<String> timePicker;
    @FXML
    private Button cancelButton;
    @FXML
    private Button continueButton;

    @Autowired
    private ParkingLotService parkingLotService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateLabel.setText("Today");

        ObservableList<String> elements = FXCollections.observableArrayList(
                new String("30 minutes"),
                new String("60 minutes"),
                new String("90 minutes")
        );

        timePicker.setItems(elements);
        timePicker.getSelectionModel().selectFirst();

        ParkingLot parkingLot = parkingLotService.getParkingLot(1);
        System.out.println("ParkingLot: " + parkingLot);
    }
    
    @FXML
    protected void handleCancelButtonAction(ActionEvent event) 
            throws Exception {
        System.exit(0);
    }
    
    @FXML
    protected void handleContinueButtonAction(ActionEvent event) 
            throws Exception {
        
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/payment_screen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Parent root = fxmlLoader.load();
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("payment_screen.fxml"));
        Scene mainScene = new Scene(root, 600, 400);
    	
        primaryStage.setTitle("Payment Info");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
