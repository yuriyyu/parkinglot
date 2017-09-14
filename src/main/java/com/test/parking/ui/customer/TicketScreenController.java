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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author 986026
 */
@Component
public class TicketScreenController 
        implements Initializable {


    @FXML
    private ImageView imageQRCode;
    
    @FXML
    private Button noButton;
    
    @FXML
    private Button yesButton;
    
    @FXML
    private TextField vehicleNumberText;
    @FXML
    private TextField slotNumberText;
    @FXML
    private TextField fromDateText;
    @FXML
    private TextField toDateText;
    @FXML
    private TextField timeText;
    @FXML
    private TextField totalCostText;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    protected void handleYesButtonAction(ActionEvent event) 
            throws Exception {
        System.exit(0);
    }
    
    @FXML
    protected void handleNoButtonAction(ActionEvent event) 
            throws Exception {
        
        System.exit(0);
    }
}
