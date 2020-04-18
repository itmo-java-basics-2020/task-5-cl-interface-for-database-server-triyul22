package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTableCommand implements DatabaseCommand {
    private ExecutionEnvironment env;
    private String databaseName;
    private String tableName;

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
                return DatabaseCommand.success();
            }
            return DatabaseCommand.fail("This Database does not exists!");
        } catch (DatabaseException e) {
            return DatabaseCommand.fail(e.getMessage());
        }
    }
}
