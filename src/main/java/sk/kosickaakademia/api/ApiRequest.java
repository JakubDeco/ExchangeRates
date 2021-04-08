package sk.kosickaakademia.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApiRequest {

    /**
     * Returns Map Collection for rates passed by param. If empty array or null is passed as argument,
     * empty Map is returned.
     * @param rates an Array with selected exchange rates
     * @return Map Collection of code as key and conversion rate for value
     * */
    public Map<String, Double> getExchangeRates(String[] rates){
        Map<String, Double> map = new HashMap<>();

        if (rates != null) {

            JsonObject conversionRates = getRatesFromApi().getAsJsonObject("conversion_rates");
            for (String temp :
                    rates) {
                if (conversionRates.has(temp))
                    map.put(temp, conversionRates.get(temp).getAsDouble());
            }
        }

        return map;
    }

    /**
     * This method sends a http request to ExchangeRate-API and returns json object
     * @return JsonObject
     * */
    private JsonObject getRatesFromApi(){
        try {
            // Setting URL
            String url_str = "https://v6.exchangerate-api.com/v6/"+ getApiKey() +"/latest/USD";

            // Making Request
            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Return parsed JSON object
            return parseRequest(request);

        } catch (IOException e) {
            e.printStackTrace();
            return new JsonObject();
        }
    }

    /**
     * This method gets a api key from properties file
     * @return String api key
     * @throws IOException if an I/O Exception occurs
     * */
    private String getApiKey() throws IOException {
        Properties prop = new Properties();
        InputStream loader = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(loader);

        return prop.getProperty("apiKey");
    }

    /**
     * This method
     * @return JsonObject representation of Http request
     * @throws IOException if an I/O Exception occurs
     * */
    private JsonObject parseRequest(HttpURLConnection request) throws IOException {
        JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
        return root.getAsJsonObject();
    }
}
