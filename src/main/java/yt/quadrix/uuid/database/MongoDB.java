package yt.quadrix.uuid.database;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import yt.quadrix.uuid.UUIDApi;

import java.util.UUID;

public class MongoDB implements Database {

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDB() {
        connect();
        create();
    }

    @Override
    public void connect() {
        // Verbindung zur MongoDB herstellen
        ConnectionString connectionString = new ConnectionString(UUIDApi.getConnectionString());
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase("minecraft"); // Deine MongoDB-Datenbank
        System.out.println("Verbindung zur MongoDB hergestellt.");
    }

    @Override
    public void disconnect() {
        // Verbindung zur MongoDB trennen
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Verbindung zur MongoDB getrennt.");
        }
    }

    @Override
    public void create() {
        // Tabelle für UUIDs erstellen, wenn sie nicht existiert
        MongoCollection<Document> collection = database.getCollection("player_uuids");
        collection.createIndex(new Document("player", 1));
        collection.createIndex(new Document("uuid", 1));
    }

    @Override
    public String getByUUID(String uuid) {
        try {
            // Spielername anhand der UUID aus der Datenbank abfragen
            MongoCollection<Document> collection = database.getCollection("player_uuids");
            Document document = collection.find(new Document("uuid", uuid)).first();
            if (document != null) {
                return document.getString("player");
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public String getUUIDStringByPlayer(String player) {
        try {
            // UUID anhand des Spielernamens aus der Datenbank abfragen
            MongoCollection<Document> collection = database.getCollection("player_uuids");
            Document document = collection.find(new Document("player", player)).first();
            if (document != null) {
                return document.getString("uuid");
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public UUID getUUIDByPlayer(String player) {
        try {
            // UUID anhand des Spielernamens aus der Datenbank abfragen
            MongoCollection<Document> collection = database.getCollection("player_uuids");
            Document document = collection.find(new Document("player", player)).first();
            if (document != null) {
                return UUID.fromString(document.getString("uuid"));
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public void insert(String uuid, String playerName) {
        MongoCollection<Document> collection = database.getCollection("player_uuids");
        if (collection.find(new Document("player", playerName)).first() != null) {
            return;
        }
        Document document = new Document("player", playerName).append("uuid", uuid);
        collection.insertOne(document);
    }
}
