package sk.kosickaakademia.database;

import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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

        Document document = Document.parse(jsonObject.toString());

        collection.insertOne(document);
    }
}
