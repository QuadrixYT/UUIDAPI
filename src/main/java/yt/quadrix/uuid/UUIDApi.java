package yt.quadrix.uuid;

import lombok.Getter;
import lombok.SneakyThrows;
import yt.quadrix.uuid.database.*;

import java.util.UUID;

@Getter
public class UUIDApi {

    @Getter
    private static String connectionString = "";
    private final DatabaseType databaseType;
    private final Database database;

    @SneakyThrows
    public UUIDApi(DatabaseType databaseType, String connectionString) {
        this.databaseType = databaseType;
        UUIDApi.connectionString = connectionString;
        if (this.databaseType == DatabaseType.MONGO) {
            this.database = new MongoDB();
        } else {
            this.database = new MySQLDB();
        }
    }

    public String getByUUID(String uuid) {
        return database.getByUUID(uuid);
    }

    public String getUUIDStringByPlayer(String player) {
        return database.getUUIDStringByPlayer(player);
    }

    public UUID getUUIDByPlayer(String player) {
        return database.getUUIDByPlayer(player);
    }

    public void insert(String uuid, String playerName) {
        database.insert(uuid, playerName);
    }

    public void connect() {
        database.connect();
    }

    public void stop() {
        database.disconnect();
    }

}
