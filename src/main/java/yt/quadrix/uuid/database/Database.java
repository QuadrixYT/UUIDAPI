package yt.quadrix.uuid.database;

import java.util.UUID;

public interface Database {

    void connect();

    void disconnect();

    void create();

    String getByUUID(String uuid);

    String getUUIDStringByPlayer(String player);

    UUID getUUIDByPlayer(String player);

    void insert(String uuid, String playerName);

}
