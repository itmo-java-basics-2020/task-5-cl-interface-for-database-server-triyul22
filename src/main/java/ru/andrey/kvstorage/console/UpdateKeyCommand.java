package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {
    private ExecutionEnvironment env;
    private String dbName;
    private String tableName;
    private String key;
    private String value;

    public UpdateKeyCommand(ExecutionEnvironment env, String dbName, String tableName, String key, String value) {
        this.env = env;
        this.dbName = dbName;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
    }

    @Override
    public DatabaseCommandResult execute() {
        if (env == null || dbName == null || tableName == null || key == null || value == null) {
            return new DatabaseCommandResult.DatabaseCommandResultInnerClass(false, "Some arguments where not given");
        }
        try {
            Optional<Database> db = env.getDatabase(dbName);
            if (db.isPresent()) {
                Database dbValue = db.get();
                dbValue.write(tableName, key, value);
                return new DatabaseCommandResult.DatabaseCommandResultInnerClass(true, null);
            }
            return new DatabaseCommandResult.DatabaseCommandResultInnerClass(false, "Database does not exists");
        } catch (DatabaseException | NullPointerException e) {
            return new DatabaseCommandResult.DatabaseCommandResultInnerClass(false, e.getMessage());
        }
    }
}
