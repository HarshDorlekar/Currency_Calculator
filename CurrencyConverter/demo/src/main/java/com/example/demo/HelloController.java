package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class HelloController {
    @FXML
    private Button convertButton;
    @FXML
    private TextField amountField;
    @FXML
    private TextField convertedAmountField;
    @FXML
    private ComboBox<String> sourceCurrencyComboBox;
    @FXML
    private ComboBox<String> targetCurrencyComboBox;

    @FXML
    private void initialize() {
        sourceCurrencyComboBox.getItems().addAll("USD", "EUR", "CAD", "INR", "AUD", "JPY");
        targetCurrencyComboBox.getItems().addAll("USD", "EUR", "CAD", "INR", "AUD", "JPY");
    }


    private static final Map<String, Double> conversionRates = new HashMap<>();
    static {
        // Add your desired conversion rates to the map
        conversionRates.put("USD_EUR", 0.91);
        conversionRates.put("EUR_USD", 1.10);
        conversionRates.put("CAD_USD", 0.74);
        conversionRates.put("USD_CAD", 1.35);
        conversionRates.put("INR_USD", 0.012);
        conversionRates.put("USD_INR", 82.45);
        conversionRates.put("AUD_USD", 0.67);
        conversionRates.put("USD_AUD", 1.50);
        conversionRates.put("JPY_USD", 0.0073);
        conversionRates.put("USD_JPY", 137.60);
        conversionRates.put("INR_CAD", 0.016);
        conversionRates.put("CAD_INR", 61.28);



        // Add more conversion rates as needed
    }

    @FXML
    protected void handleConvertButtonAction(ActionEvent event) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String sourceCurrency = sourceCurrencyComboBox.getValue();
            String targetCurrency = targetCurrencyComboBox.getValue();

            double convertedAmount = convertCurrency(amount, sourceCurrency, targetCurrency);
            convertedAmountField.setText(String.format("%.2f", convertedAmount));
        } catch (NumberFormatException e) {
            convertedAmountField.setText("Invalid input");
        }
    }

    private double convertCurrency(double amount, String sourceCurrency, String targetCurrency) {
        // Generate a key for the conversion rate lookup
        String conversionKey = sourceCurrency + "_" + targetCurrency;
        double conversionRate = conversionRates.getOrDefault(conversionKey, 1.0);
        return amount * conversionRate;
    }
}
