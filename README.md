UUID Database API

This repository provides a simple API for managing player UUIDs using different database systems. Currently, it supports MongoDB and MySQL databases. The primary goal is to facilitate the retrieval and storage of player UUIDs.
Usage
Setting up UUIDApi

To use the UUIDApi, create an instance of it by providing the database type and connection string in the constructor.

```
UUIDApi uuidApi = new UUIDApi(DatabaseType.MONGO, "mongodb://localhost:27017");
```
Retrieving Player Information
Get Player Name by UUID

```
String playerName = uuidApi.getByUUID("01234567-89ab-cdef-0123-456789abcdef");
```
Get UUID String by Player Name

```
String uuidString = uuidApi.getUUIDStringByPlayer("player123");
```
Get UUID by Player Name

```
UUID playerUUID = uuidApi.getUUIDByPlayer("player123");
```

Inserting Player Information
Insert Player UUID
```
uuidApi.insert("01234567-89ab-cdef-0123-456789abcdef", "player123");
```

Disconnecting from the Database
```
uuidApi.stop();
```
Examples
MongoDB Example

```
UUIDApi uuidApi = new UUIDApi(DatabaseType.MONGO, "mongodb://localhost:27017");

// Retrieve player information
String playerName = uuidApi.getByUUID("01234567-89ab-cdef-0123-456789abcdef");
String uuidString = uuidApi.getUUIDStringByPlayer("player123");
UUID playerUUID = uuidApi.getUUIDByPlayer("player123");

// Insert player information
uuidApi.insert("01234567-89ab-cdef-0123-456789abcdef", "player123");

uuidApi.stop();
```
MySQL Example

```
UUIDApi uuidApi = new UUIDApi(DatabaseType.MYSQL, "jdbc:mysql://localhost:3306/mydatabase");

// Retrieve player information
String playerName = uuidApi.getByUUID("01234567-89ab-cdef-0123-456789abcdef");
String uuidString = uuidApi.getUUIDStringByPlayer("player123");
UUID playerUUID = uuidApi.getUUIDByPlayer("player123");

// Insert player information
uuidApi.insert("01234567-89ab-cdef-0123-456789abcdef", "player123");

uuidApi.stop();
```

Feel free to explore and adapt the code according to your needs. For additional information, refer to the Javadoc in the source code.

Please make sure to replace the placeholder connection strings with your actual database connection details.
UUID Database API

-----------
# 
-----------

Dieses Repository bietet eine einfache API zum Verwalten von Spieler-UUIDs unter Verwendung verschiedener Datenbanksysteme. Derzeit unterstützt es MongoDB- und MySQL-Datenbanken. Das Hauptziel besteht darin, das Abrufen und Speichern von Spieler-UUIDs zu erleichtern.
Verwendung
Einrichten von UUIDApi

Um die UUIDApi zu verwenden, erstellen Sie eine Instanz davon, indem Sie den Datenbanktyp und die Verbindungszeichenfolge im Konstruktor bereitstellen.

```
UUIDApi uuidApi = new UUIDApi(DatabaseType.MONGO, "mongodb://localhost:27017");
```

Spielerinformationen abrufen
Spielername nach UUID abrufen

```
String playerName = uuidApi.getByUUID("01234567-89ab-cdef-0123-456789abcdef");
```

UUID-String nach Spielername abrufen

```
String uuidString = uuidApi.getUUIDStringByPlayer("player123");
```

UUID nach Spielername abrufen
```
UUID playerUUID = uuidApi.getUUIDByPlayer("player123");
```

Spielerinformationen einfügen
Spieler-UUID einfügen
```
uuidApi.insert("01234567-89ab-cdef-0123-456789abcdef", "player123");
```

Trennen von der Datenbank
```
uuidApi.stop();
```

Beispiele
MongoDB-Beispiel
```
UUIDApi uuidApi = new UUIDApi(DatabaseType.MONGO, "mongodb://localhost:27017");

// Spielerinformationen abrufen
String playerName = uuidApi.getByUUID("01234567-89ab-cdef-0123-456789abcdef");
String uuidString = uuidApi.getUUIDStringByPlayer("player123");
UUID playerUUID = uuidApi.getUUIDByPlayer("player123");

// Spielerinformationen einfügen
uuidApi.insert("01234567-89ab-cdef-0123-456789abcdef", "player123");

uuidApi.stop();
```

MySQL-Beispiel
```
UUIDApi uuidApi = new UUIDApi(DatabaseType.MYSQL, "jdbc:mysql://localhost:3306/meinedatenbank");

// Spielerinformationen abrufen
String playerName = uuidApi.getByUUID("01234567-89ab-cdef-0123-456789abcdef");
String uuidString = uuidApi.getUUIDStringByPlayer("player123");
UUID playerUUID = uuidApi.getUUIDByPlayer("player123");

// Spielerinformationen einfügen
uuidApi.insert("01234567-89ab-cdef-0123-456789abcdef", "player123");

uuidApi.stop();
```

Entdecken Sie den Code und passen Sie ihn nach Bedarf an. Weitere Informationen finden Sie in der Javadoc im Quellcode.

Bitte stellen Sie sicher, dass Sie die Platzhalter-Verbindungszeichenfolgen durch Ihre tatsächlichen Datenbankverbindungsdetails ersetzen.