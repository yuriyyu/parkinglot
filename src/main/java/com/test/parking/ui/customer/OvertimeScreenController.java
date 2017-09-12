/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.ui.customer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 986026
 */
public class OvertimeScreenController 
        implements Initializable {

    @FXML
    private TextField vehicleNumberText;
    
    @FXML
    private TextField slotNumberText;
    
    @FXML
    private TextField exceededTimeText;
    
    @FXML
    private TextField fineText;
    
    @FXML
    private Button payLaterButton;
    
    @FXML
    private Button payNowButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    protected void handlePayLaterButtonAction(ActionEvent event) 
            throws Exception {
        System.exit(100);
    }
    
    @FXML
    protected void handlePayNowButtonAction(ActionEvent event) 
            throws Exception {
        
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("payment_screen.fxml"));
        Scene mainScene = new Scene(root, 600, 400);
    	
        primaryStage.setTitle("Payment Info");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
