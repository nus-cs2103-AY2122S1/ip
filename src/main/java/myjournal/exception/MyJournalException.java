package myjournal.exception;

public class MyJournalException extends RuntimeException {
    public MyJournalException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "MyJournalException: " + getMessage();
    }
}
