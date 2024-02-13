package yt.quadrix.uuid.database;

import lombok.Getter;

@Getter
public enum DatabaseType {

    MONGO(MongoDB.class),
    MYSQL(MySQLDB.class);

    private final Class<? extends Database> databaseClass;

    DatabaseType(Class<? extends Database> databaseClass) {
        this.databaseClass = databaseClass;
    }
}
