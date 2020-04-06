package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {
    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    class DatabaseCommandResultInnerClass implements DatabaseCommandResult {
        private boolean success;
        private String errorMessage;

        public DatabaseCommandResultInnerClass(boolean success, String errorMessage) {
            this.success = success;
            this.errorMessage = errorMessage;
        }

        @Override
        public Optional<String> getResult() {
            switch (getStatus()) {
                case SUCCESS:
                    return Optional.of("Command successfully executed!");
                case FAILED:
                    return Optional.of("Execution of the command has failed." + getErrorMessage());
            }
            return Optional.empty();
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            if (isSuccess()) {
                return DatabaseCommandStatus.SUCCESS;
            }
            return DatabaseCommandStatus.FAILED;
        }

        @Override
        public boolean isSuccess() {
            return success;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }

}