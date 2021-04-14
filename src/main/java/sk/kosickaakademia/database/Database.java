package sk.kosickaakademia.database;

import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {
    public static final MongoClient client = new MongoClient();
    public static final MongoDatabase database = client.getDatabase("exchangeRate");

    public static void insertApiRequest(String baseCurrency, String to, double rate, double amount){
        MongoCollection<Document> collection = database.getCollection("apiRequest");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("baseCurrency", baseCurrency);
        jsonObject.addProperty("to", to);
        jsonObject.addProperty("rate", rate);
        jsonObject.addProperty("amount", amount);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        String date = sdf.format(new Date().getTime());
        jsonObject.addProperty("date", date);

        Document document = Document.parse(jsonObject.toString());

        collection.insertOne(document);
    }
}
