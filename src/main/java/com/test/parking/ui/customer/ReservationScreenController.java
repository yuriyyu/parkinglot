package com.test.parking.ui.customer;

import java.net.URL;
import java.util.ResourceBundle;
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


/**
 * Created by Yuriy Yugay on 9/11/2017.
 *
 * @author Yuriy Yugay
 */
public class ReservationScreenController  
        implements Initializable {

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
        
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("payment_screen.fxml"));
        Scene mainScene = new Scene(root, 600, 400);
    	
        primaryStage.setTitle("Payment Info");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
