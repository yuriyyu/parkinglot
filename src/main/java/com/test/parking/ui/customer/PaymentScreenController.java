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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author 986026
 */
@Component
public class PaymentScreenController 
        implements Initializable {
    @Autowired
    private ConfigurableApplicationContext springContext;

    private boolean isOvertimePayment;

    @FXML
    private Button cancelButton;
    
    @FXML
    private Button submitButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    protected void handleCancelButtonAction(ActionEvent event) 
            throws Exception {
        System.exit(0);
    }
    
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) 
            throws Exception {

        if(isOvertimePayment) {
            System.exit(0);
        } else {

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ticket_screen.fxml"));
            fxmlLoader.setControllerFactory(springContext::getBean);
            Parent root = fxmlLoader.load();
            Scene mainScene = new Scene(root, 600, 400);

            primaryStage.setTitle("Ticket Info");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        }
    }

    public void setOvertimePayment(boolean overtimePayment) {
        isOvertimePayment = overtimePayment;
    }
}
