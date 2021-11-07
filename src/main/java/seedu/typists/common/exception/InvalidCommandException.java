package seedu.typists.common.exception;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidCommandException() {
        super();
    }
}