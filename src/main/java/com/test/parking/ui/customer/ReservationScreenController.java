package com.test.parking.ui.customer;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.VehicleType;
import com.test.parking.core.models.reservations.Registration;
import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.models.tariffs.Tariff;
import com.test.parking.core.services.ParkingLotService;
import com.test.parking.core.services.ParkingSlotService;
import com.test.parking.core.services.RegistrationService;
import com.test.parking.core.services.TariffService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    @FXML
    private Label labelParkingLotId;


    @Autowired
    private ParkingLotService parkingLotService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private TariffService tariffService;

    @Autowired
    private TicketScreenController ticketScreenController;

    private int parkingLotId;
    private ParkingLot parkingLot;
    private Map<String, ParkingSlot> slotMap;
    private Map<VehicleType, Tariff> currentTariffMap;
    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    private ParkingSlot freeParkingSlot;
    private double slotFeeAmount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bottomPane.setVisible(false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");

        String formatDateTime = LocalDateTime.now().format(formatter);
        dateLabel.setText(formatDateTime);

        ObservableList<String> elements = FXCollections.observableArrayList(
                new String("30 minutes"),
                new String("60 minutes"),
                new String("90 minutes"),
                new String("120 minutes")
        );

        timePicker.setItems(elements);
        timePicker.getSelectionModel().selectFirst();
        timePicker.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String [] values = newValue.split(" ");
                double selectedTime = Double.valueOf(values[0]);
                Tariff tariff = currentTariffMap.get(freeParkingSlot.getType());
                slotFeeAmount = selectedTime / 60f * tariff.getPrice();
                priceText.setText(slotFeeAmount + " $");
            }
        });

        ParkingLot parkingLot = parkingLotService.getParkingLot(parkingLotId);
        labelParkingLotId.setText(String.valueOf(parkingLotId));

        currentTariffMap = tariffService.getCurrentParkingLotTariffs(parkingLotId);

        List<ParkingSlot> parkingSlots = parkingLot.getParkingSlots();
        slotMap = new HashMap<>();
        for (Node node : gridSlots.getChildren()) {
            if(node instanceof Pane) {
                Pane pane = (Pane) node;
                Integer nodeRowIndex = GridPane.getRowIndex(pane);
                if(nodeRowIndex == null) {
                    nodeRowIndex = 0;
                }
                Integer nodeColumnIndex = GridPane.getColumnIndex(pane);
                if(nodeColumnIndex == null) {
                    nodeColumnIndex = 0;
                }

                ParkingSlot slot = findSlot(parkingSlots, nodeRowIndex, nodeColumnIndex);
                if(slot != null) {
                    slotMap.put(slot.getSlotName(), slot);

                    // slot click event listener
                    pane.setOnMouseClicked(event -> {
                        Pane p = (Pane)event.getSource();
                        Label label = (Label) p.getChildren().get(0);
                        ParkingSlot sSlot = slotMap.get(label.getText());
                        if(sSlot != null){
                            VehicleType type = sSlot.getType();
                            Tariff tar = currentTariffMap.get(type);
                            String [] values = timePicker.getSelectionModel().getSelectedItem().split(" ");

                            double fee = (Double.valueOf(values[0]) / 60f * tar.getPrice());
                            priceText.setText(fee + " $");
                            typeText.setText(type.toValue());
                            vehicleNumberText.setText(sSlot.getOccupiedVehicleNumber());

                            if(!sSlot.isOccupied()) {
                                freeParkingSlot = sSlot;
                                slotFeeAmount = fee;
                                bottomPane.setVisible(true);
                                priceText.setEditable(true);
                                vehicleNumberText.setEditable(true);
                                vehicleNumberText.setDisable(false);
                                timePicker.setDisable(false);
                                cancelButton.setVisible(true);
                                continueButton.setVisible(true);

                            } else {
                                freeParkingSlot = null;
                                bottomPane.setVisible(true);
                                priceText.setEditable(false);
                                vehicleNumberText.setEditable(false);
                                vehicleNumberText.setDisable(true);
                                timePicker.setDisable(true);
                                cancelButton.setVisible(false);
                                continueButton.setVisible(false);
                            }
                        }
                    });

                    Color color = Color.GREEN;
                    if(slot.isOccupied()) {
                        color = Color.RED;
                    }
                    pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }
    }
    private ParkingSlot findSlot(List<ParkingSlot> parkingSlots, int nodeRowIndex, int nodeColumnIndex) {
        if(parkingSlots == null) {
            return null;
        }

        for(ParkingSlot slot : parkingSlots) {
            int slotRowIndex = slot.getRowIndex();
            int slotColumnIndex = slot.getColumnIndex();
            if(nodeRowIndex == slotRowIndex && nodeColumnIndex == slotColumnIndex) {
                return slot;
            }
        }
        return null;
    }
    @FXML
    protected void handleCancelButtonAction(ActionEvent event) 
            throws Exception {
        System.exit(0);
    }
    
    @FXML
    protected void handleContinueButtonAction(ActionEvent event) 
            throws Exception {
        if(vehicleNumberText.getText() == null || vehicleNumberText.getText().isEmpty() || freeParkingSlot == null) {
            return;
        }
        String[] splitted = timePicker.getValue().split(" ");
        int time = Integer.valueOf(splitted[0]);

        Registration registration = registrationService.createRegistration(
                vehicleNumberText.getText(), freeParkingSlot.getId(), time, slotFeeAmount);

        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/payment_screen.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Parent root = fxmlLoader.load();
        ticketScreenController.setRegistration(registration);
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("payment_screen.fxml"));
        Scene mainScene = new Scene(root, 600, 400);
    	
        primaryStage.setTitle("Payment Info");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
