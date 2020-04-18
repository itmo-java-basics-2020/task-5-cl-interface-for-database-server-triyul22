package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public interface DatabaseCommand {
    DatabaseCommandResult execute() throws DatabaseException;

    static DatabaseCommandResult.DatabaseCommandResultImpl success(){
            return new DatabaseCommandResult.DatabaseCommandResultImpl(true, null);
    }

    static DatabaseCommandResult.DatabaseCommandResultImpl fail(String errorMessage){
        return new DatabaseCommandResult.DatabaseCommandResultImpl(false, errorMessage);
    }
}
