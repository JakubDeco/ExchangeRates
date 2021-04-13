package sk.kosickaakademia.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.kosickaakademia.api.ApiRequest;

import java.util.Map;

public class FxController {
    public Button btnConvert;
    public ComboBox<String> comboBoxFrom;
    public ComboBox<String> comboBoxTo;
    public TextField TextFieldAmount;
    public Label LblRate;

    private Map<String, Double> rates;

    public FxController(){
        rates = new ApiRequest().getExchangeRates(null);
    }

    @FXML
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(rates.keySet());
        FXCollections.sort(list);

        comboBoxFrom.setItems(list);
        comboBoxTo.setItems(list);
    }
}
