/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.ui.customer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    
}
