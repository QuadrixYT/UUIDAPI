package yt.quadrix.uuid.database;

import yt.quadrix.uuid.UUIDApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class MySQLDB implements Database {

    private final String connectionString = UUIDApi.getConnectionString();

    public MySQLDB() {
        create();
    }

    @Override
    public void connect() {
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void create() {
        try {
            // Tabelle für UUIDs erstellen, wenn sie nicht existiert
            String createTableQuery = "CREATE TABLE IF NOT EXISTS player_uuids (player VARCHAR(50), uuid VARCHAR(36))";

            try (Connection connection = DriverManager.getConnection(connectionString);
                 PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public String getByUUID(String uuid) {
        System.out.println("getByUUID(UUID)=" + uuid);
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT player FROM player_uuids WHERE uuid = ?")) {
            preparedStatement.setString(1, uuid);
            preparedStatement.execute();
            System.out.println("getByUUID(Name)=" + preparedStatement.getResultSet().getString("player"));
            return preparedStatement.getResultSet().getString("player");
        } catch (SQLException e) {
            e.fillInStackTrace();
            return null;
        }
    }

    @Override
    public String getUUIDStringByPlayer(String player) {
        System.out.println("getUUIDStringByPlayer(Player)=" + player);
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid FROM player_uuids WHERE player = ?")) {
            preparedStatement.setString(1, player);
            preparedStatement.execute();
            System.out.println("getUUIDStringByPlayer(UUID)=" + preparedStatement.getResultSet().getString("uuid"));
            return preparedStatement.getResultSet().getString("uuid");
        } catch (SQLException e) {
            e.fillInStackTrace();
            return null;
        }
    }

    @Override
    public UUID getUUIDByPlayer(String player) {
        System.out.println("getUUIDByPlayer(Player)=" + player);
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid FROM player_uuids WHERE player = ?")) {
            preparedStatement.setString(1, player);
            preparedStatement.execute();
            System.out.println("getUUIDByPlayer(UUID)=" + preparedStatement.getResultSet().getString("uuid"));
            return UUID.fromString(preparedStatement.getResultSet().getString("uuid"));
        } catch (SQLException e) {
            e.fillInStackTrace();
            return null;
        }
    }

    @Override
    public void insert(String uuid, String playerName) {
        System.out.println("insert(UUID)=" + uuid + ", insert(Name)=" + playerName);
        try {
            // UUID in die Datenbank einfügen
            String insertQuery = "INSERT INTO player_uuids (player, uuid) VALUES (?, ?)";
            if (getUUIDStringByPlayer(playerName) != null) return;
            try (Connection connection = DriverManager.getConnection(connectionString);
                 PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, playerName);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                System.out.println("insert(UUID)=" + uuid + ", insert(Name)=" + playerName);
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }
}
