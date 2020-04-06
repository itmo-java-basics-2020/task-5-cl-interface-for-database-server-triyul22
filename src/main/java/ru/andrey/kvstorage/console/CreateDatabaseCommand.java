package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;
import ru.andrey.kvstorage.logic.DatabaseClass;

public class CreateDatabaseCommand implements DatabaseCommand {
    private ExecutionEnvironment env;
    private String dbName;

    public CreateDatabaseCommand(ExecutionEnvironment env, String dbName) {
        this.dbName = dbName;
        this.env = env;
    }

    @Override
    public DatabaseCommandResult execute() {
        Database db = new DatabaseClass(env, dbName);
        if (env == null || dbName == null) {
            return new DatabaseCommandResult.DatabaseCommandResultInnerClass(false, "No given arguments");
        }
        return new DatabaseCommandResult.DatabaseCommandResultInnerClass(true, null);
    }
}
