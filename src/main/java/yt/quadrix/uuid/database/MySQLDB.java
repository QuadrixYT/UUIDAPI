package yt.quadrix.uuid.database;

import yt.quadrix.uuid.UUIDApi;

import java.sql.*;
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
        String createTableQuery = "CREATE TABLE IF NOT EXISTS player_uuids (player VARCHAR(50), uuid VARCHAR(36))";
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public String getByUUID(String uuid) {
        String playerName = null;
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT player FROM player_uuids WHERE uuid = ?")) {
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                playerName = resultSet.getString("player");
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
        return playerName;
    }

    @Override
    public String getUUIDStringByPlayer(String player) {
        String uuid = null;
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid FROM player_uuids WHERE player = ?")) {
            preparedStatement.setString(1, player);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                uuid = resultSet.getString("uuid");
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
        return uuid;
    }

    @Override
    public UUID getUUIDByPlayer(String player) {
        UUID uuid = null;
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid FROM player_uuids WHERE player = ?")) {
            preparedStatement.setString(1, player);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String uuidString = resultSet.getString("uuid");
                uuid = UUID.fromString(uuidString);
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
        return uuid;
    }

    @Override
    public void insert(String uuid, String playerName) {
        String insertQuery = "INSERT INTO player_uuids (player, uuid) VALUES (?, ?)";
        if (getUUIDStringByPlayer(playerName) != null) return;
        try (Connection connection = DriverManager.getConnection(connectionString);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, playerName);
            preparedStatement.setString(2, uuid);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Player " + playerName + " with UUID " + uuid + " inserted");
            } else {
                System.out.println("Player " + playerName + " with UUID " + uuid + " not inserted");
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }
}
