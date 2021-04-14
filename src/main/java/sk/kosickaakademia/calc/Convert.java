package sk.kosickaakademia.calc;

import sk.kosickaakademia.api.ApiRequest;

import java.util.Map;

public class Convert {
    private static String baseCurrency = "EUR";
    private final String[] myRates = {"EUR","USD","CZK","CAD"};

    public Convert(String baseCurrency) {
        Convert.baseCurrency = baseCurrency;
    }

    public Convert(){}

    public static String getBaseCurrency() {
        return baseCurrency;
    }

    public static void setBaseCurrency(String baseCurrency) {
        Convert.baseCurrency = baseCurrency;
    }


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

    public double convert(double value, String toCurrency, Map<String, Double> rates){
        if(value <= 0
                || toCurrency == null
                || toCurrency.isBlank()
                || rates == null
                || rates.isEmpty())
            return 0;

        Double atRate = rates.get(toCurrency);
        return atRate != null ? value * atRate : 0;
    }

    private void printConversion(String from, String to, double eur, double result, double rate){
        System.out.println("- From "+from+"to "+to+" : "+eur+" => "+result+" at rate of "+rate);
    }
}
