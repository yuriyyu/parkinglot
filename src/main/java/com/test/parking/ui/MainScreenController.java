/*
 * Copyright (c) 2011, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.test.parking.ui;

import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.models.tariffs.Tariff;
import com.test.parking.core.services.ParkingLotService;
import com.test.parking.core.services.ParkingSlotService;
import com.test.parking.core.services.RegistrationService;
import com.test.parking.core.services.TariffService;
import com.test.parking.ui.admin.ParkingLotAdminScreenController;
import com.test.parking.ui.customer.ReservationScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainScreenController
        implements Initializable {
    @Autowired
    private ConfigurableApplicationContext springContext;

    @Autowired
    private ParkingLotService parkingLotService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private ParkingSlotService parkingSlotService;

    @Autowired
    private ReservationScreenController reservationScreenController;
    @Autowired
    private ParkingLotAdminScreenController parkingLotAdminScreenController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ParkingLot> parkingLots = parkingLotService.getParkingLots();
        int parkingLotId = 0;
        if(parkingLots == null || parkingLots.isEmpty()) {
            // create stub parking lot
            ParkingLot parkingLot = parkingLotService.createStubParking();
            parkingLotId = parkingLot.getId();
            // create stub parking slots
            List<ParkingSlot> parkingSlots = parkingSlotService.createStubSlots(parkingLot);
            // create stub tariffs
            List<Tariff> tariffs = tariffService.createStubTariffs(parkingLot);
            // create stub registration
            registrationService.createStubRegistrations(parkingSlots, tariffs);
        } else {
            parkingLotId = parkingLots.get(0).getId();
        }
        reservationScreenController.setParkingLotId(parkingLotId);
        parkingLotAdminScreenController.setParkingLotId(parkingLotId);
    }

    @FXML
    protected void handleReserveSpotButtonAction(ActionEvent event) throws Exception {

        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/reservation_screen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Parent root = fxmlLoader.load();

        Scene mainScene = new Scene(root, 600, 400);

        primaryStage.setTitle("Parking Lot");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    
    @FXML protected void handleSignInButtonAction(ActionEvent event) throws Exception {
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//    	Parent loginFrame = FXMLLoader.load(getClass().getClassLoader().getResource("login_screen.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login_screen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Parent loginFrame = fxmlLoader.load();

    	stage.setTitle("Login");
        stage.setScene(new Scene(loginFrame, 300, 275));
        
    }

    @FXML protected void handleOvertimeButtonAction(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/overtime_screen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Parent root = fxmlLoader.load();

        Scene mainScene = new Scene(root, 600, 400);

        primaryStage.setTitle("Overtime payment");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
