public class DukeException extends Exception {
    private String message;

    // -1 default// 0: WhiteSpace // 1: Empty description
    private int type = -1;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }
    public DukeException(String errorMessage, int type) {
        super(errorMessage);
        this.message = errorMessage;
        this.type = type;
    }

    @Override
    public String toString() {
        if (type == 0) {
            return "The " + message + " Command and Description must be separated by whitespace!";
        } else if (type == 1) {
            return "The description of " + message + " cannot be empty!";
        }
        return message;
    }
}
