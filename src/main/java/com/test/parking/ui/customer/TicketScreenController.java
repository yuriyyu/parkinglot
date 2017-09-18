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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author 986026
 */
@Component
public class TicketScreenController 
        implements Initializable {

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
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        System.exit(0);
    }
    
    @FXML
    protected void handleNoButtonAction(ActionEvent event) 
            throws Exception {
        
        System.exit(0);
    }
}
