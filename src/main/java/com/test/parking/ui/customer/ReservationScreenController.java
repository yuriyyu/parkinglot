package com.test.parking.ui.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


/**
 * Created by Yuriy Yugay on 9/11/2017.
 *
 * @author Yuriy Yugay
 */
public class ReservationScreenController {

    @FXML
    private Label dateLabel;
    @FXML
    private DatePicker datePicker;

    @FXML
    private GridPane gridSlots;

    @FXML
    private Pane bottomPane;
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

    public void initialize() {
        dateLabel.setText("Today");

        ObservableList<String> elements = FXCollections.observableArrayList(
                new String("30 minutes"),
                new String("60 minutes"),
                new String("90 minutes")
        );

        timePicker.setItems(elements);
        timePicker.getSelectionModel().selectFirst();

    }
}
