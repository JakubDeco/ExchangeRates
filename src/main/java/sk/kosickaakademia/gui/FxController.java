package sk.kosickaakademia.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.kosickaakademia.api.ApiRequest;
import sk.kosickaakademia.calc.Convert;

import java.util.Map;

public class FxController {
    public Button btnConvert;
    public ComboBox<String> comboBoxFrom;
    public ComboBox<String> comboBoxTo;
    public TextField textFieldAmount;
    public Label lblRate;
    public Label lblResult;

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

    public void convert(ActionEvent actionEvent) {
        Convert calc = new Convert();
        Convert.setBaseCurrency(comboBoxFrom.getValue());
        rates = new ApiRequest().getExchangeRates(null);
        double value = Double.parseDouble(textFieldAmount.getText());
        String toCurrency = comboBoxTo.getValue();

        double result = calc.convert(value,toCurrency,rates);

        showResult(result);
        showRate(rates.get(toCurrency));
    }

    private void showRate(double value) {
        lblRate.setText(String.valueOf(value));
        lblRate.setVisible(true);
    }

    private void showResult(double value){
        lblResult.setText(String.valueOf(value));
        lblResult.setVisible(true);
    }
}
