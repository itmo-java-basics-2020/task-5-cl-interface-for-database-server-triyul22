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

    class DatabaseCommandResultImpl implements DatabaseCommandResult {
        private boolean success;
        private String errorMessage;
        private Optional<String> result;

        public DatabaseCommandResultImpl(boolean success, String errorMessage) {
            this.success = success;
            this.errorMessage = errorMessage;
        }

        @Override
        public Optional<String> getResult() {
            switch (getStatus()) {
                case SUCCESS:
                    result = Optional.of("Successfully executed!");
                case FAILED:
                    result = Optional.ofNullable(errorMessage);
            }
            return result;
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return isSuccess() ? DatabaseCommandStatus.SUCCESS : DatabaseCommandStatus.FAILED;
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