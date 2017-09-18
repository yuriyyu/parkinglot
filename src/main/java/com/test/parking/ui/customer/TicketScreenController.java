/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.ui.customer;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.test.parking.core.models.reservations.Registration;
import com.test.parking.core.models.tickets.NormalTicket;
import com.test.parking.core.services.TicketService;
import com.test.parking.ui.MainScreenController;
import javafx.embed.swing.SwingFXUtils;
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
    @Autowired
    private ConfigurableApplicationContext springContext;

    @Autowired
    private MainScreenController mainScreenController;

    private NormalTicket ticket;

    @Autowired
    private TicketService ticketService;

    @FXML
    private ImageView imageQRCode;
    
    @FXML
    private Button noButton;
    
    @FXML
    private Button yesButton;

    @FXML
    private TextField registrationIdText;
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

    public void setRegistration(Registration registration) {
        ticket = ticketService.createNormalTicket(registration);
        mainScreenController.setOvertimeRegistration(registration);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        registrationIdText.setText(String.valueOf(ticket.getRegistrationId()));
        vehicleNumberText.setText(ticket.getVehicleNumber());
        slotNumberText.setText(ticket.getSlotNumber());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm");

        fromDateText.setText(ticket.getFromDate().format(formatter));
        toDateText.setText(ticket.getToDate().format(formatter));
        timeText.setText(ticket.getOccupiedTime() + " minutes");
        totalCostText.setText(ticket.getTotalCost() + " $");
        imageQRCode.setImage(SwingFXUtils.toFXImage(ticket.getQrCodeImage(), null));
    }
    
    @FXML
    protected void handleYesButtonAction(ActionEvent event) 
            throws Exception {
        returnToMainScreen(event);

    }

    @FXML
    protected void handleNoButtonAction(ActionEvent event)
            throws Exception {
        returnToMainScreen(event);

    }

    private void returnToMainScreen(ActionEvent event)
            throws Exception {
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main_screen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Parent root = fxmlLoader.load();
        Scene mainScene = new Scene(root, 600, 400);

        primaryStage.setTitle("Payment Info");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

}
