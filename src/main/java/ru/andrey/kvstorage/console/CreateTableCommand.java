package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTableCommand implements DatabaseCommand {
    ExecutionEnvironment env;
    String databaseName;
    String tableName;

    public CreateTableCommand(String dbName, String tableName, ExecutionEnvironment env) {
        this.databaseName = dbName;
        this.tableName = tableName;
        this.env = env;
    }

    @Override
    public DatabaseCommandResult execute() {
        try {
            Optional<Database> db = env.getDatabase(databaseName);
            if (db.isPresent()) {
                Database dbValue = db.get();
                dbValue.createTableIfNotExists(tableName);
                return new DatabaseCommandResult.DatabaseCommandResultInnerClass(true, null);
            }
            return new DatabaseCommandResult.DatabaseCommandResultInnerClass(false, "This Database does not exists!");
        } catch (DatabaseException e) {
            return new DatabaseCommandResult.DatabaseCommandResultInnerClass(false, e.getMessage());
        }
    }
}
