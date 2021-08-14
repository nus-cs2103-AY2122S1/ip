public class Record {
    private String message;
    private boolean terminate;

    public Record (String message, boolean terminate) {
        this.message = message;
        this.terminate = terminate;
    }

    public Record (String message) {
        this(message, false);
    }

    public String msg() {
        return message;
    }

    public boolean bye() {
        return terminate;
    }

    @Override
    public String toString() {
        return message;
    }
}
