package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand {
    private ExecutionEnvironment env;
    private String dbName;
    private String tableName;
    private String key;

    public ReadKeyCommand(String dbName, String tableName, String key, ExecutionEnvironment env) {
        this.env = env;
        this.dbName = dbName;
        this.tableName = tableName;
        this.key = key;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        String errorMessage;
        if (env == null || dbName == null || tableName == null || key == null) {
            return DatabaseCommand.fail("Some arguments were not given!");
        }
        try {
            Optional<Database> db = env.getDatabase(dbName);
            if (db.isPresent()) {
                Database dbValue = db.get();
                dbValue.read(tableName, key);
                return DatabaseCommand.success();
            } else {
                return DatabaseCommand.fail("This Database does not exists!");
            }
        } catch (DatabaseException | NullPointerException e) {
            return DatabaseCommand.fail(e.getMessage());
        }
    }
}
