/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.ui.customer;

import java.net.URL;
import java.util.ResourceBundle;

import com.test.parking.core.models.reservations.Registration;
import com.test.parking.core.models.tickets.FineTicket;
import com.test.parking.core.services.TicketService;
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
public class OvertimeScreenController 
        implements Initializable {
    @Autowired
    private ConfigurableApplicationContext springContext;

    @FXML
    private TextField registrationIdText;
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

    private FineTicket fineTicket;

    @Autowired
    private TicketService ticketService;

    public void setOvertimeRegistration(Registration overtimeRegistration) {
        fineTicket = ticketService.createOvertimeTicket(overtimeRegistration);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        registrationIdText.setText(String.valueOf(fineTicket.getRegistrationId()));
        slotNumberText.setText(fineTicket.getSlotNumber());
        vehicleNumberText.setText(fineTicket.getVehicleNumber());
        exceededTimeText.setText(String.valueOf(fineTicket.getExceededTime()));
        fineText.setText(String.valueOf(fineTicket.getFineCost()));
    }    
    
    @FXML
    protected void handlePayLaterButtonAction(ActionEvent event) 
            throws Exception {
        System.exit(0);
    }
    
    @FXML
    protected void handlePayNowButtonAction(ActionEvent event) 
            throws Exception {
        
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/payment_screen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Parent root = fxmlLoader.load();
        PaymentScreenController paymentScreenController = fxmlLoader.getController();
        paymentScreenController.setOvertimePayment(true);

        Scene mainScene = new Scene(root, 600, 400);

        primaryStage.setTitle("Payment Info");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
