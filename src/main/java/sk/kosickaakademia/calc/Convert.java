package sk.kosickaakademia.calc;

import sk.kosickaakademia.api.ApiRequest;

import java.util.Map;

public class Convert {
    private final String[] myRates = {"EUR","USD","CZK","CAD"};

    public void convert(double value){
        if (value <= 0) {
            System.out.println("Input has to be positive value.");
            return;
        }

        if (myRates.length == 0){
            System.out.println("Select currencies first.");
            return;
        }

        ApiRequest apiRequest = new ApiRequest();
        Map<String, Double> rates = apiRequest.getExchangeRates(myRates);
        if (rates.isEmpty()){
            System.out.println("Selected currency rates not found.");
            return;
        }

        for (Map.Entry<String, Double> entry: rates.entrySet()){
            double result = value * entry.getValue();
            printConversion("EUR", entry.getKey(),value,result,entry.getValue());
        }
    }

    private void printConversion(String from, String to, double eur, double result, double rate){
        System.out.println("- From "+from+"to "+to+" : "+eur+" => "+result+" at rate of "+rate);
    }
}
