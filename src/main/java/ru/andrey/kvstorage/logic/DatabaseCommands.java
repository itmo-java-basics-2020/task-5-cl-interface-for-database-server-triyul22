package ru.andrey.kvstorage.logic;

import ru.andrey.kvstorage.console.*;
import ru.andrey.kvstorage.exception.DatabaseException;

interface Commands {
    DatabaseCommand getCommand(ExecutionEnvironment env, String[] args);
}

public enum DatabaseCommands implements Commands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String[] parameters) {
            return new CreateDatabaseCommand(env, parameters[1]);
        }
    },
    CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String[] parameters) {
            return new CreateTableCommand(parameters[1], parameters[2], env);
        }
    },
    UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String[] parameters) {
            return new UpdateKeyCommand(env, parameters[1], parameters[2], parameters[3], parameters[4]);
        }
    },
    READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String[] parameters) {
            return new ReadKeyCommand(parameters[1], parameters[2], parameters[3], env);
        }
    };

    public static DatabaseCommandResult commandRun(String args, ExecutionEnvironment env) throws DatabaseException {
        String[] parameters = args.split(" ");
        var command = DatabaseCommands.valueOf(parameters[0]).getCommand(env, parameters);
        return command.execute();
    }
}

