public class Message {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static final String LOGO = "\t____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";

    private static final Message GREETING_TEXT = new Message(
            "\tHello from \n"
            + LOGO
            + "\tHow can I help you?"
    );

    private static final Message FAREWELL_TEXT = new Message(
            "\t☹☹☹ Why do you choose to leave me!"
    );

    private final String message;

    public Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "\t" + HORIZONTAL_LINE + "\n" + this.message + "\n\t" + HORIZONTAL_LINE;
    }

    public void printMessage() {
        System.out.println(this);
    }

    public static String greet() {
        return GREETING_TEXT.toString();
    }

    public static String farewell() {
        return FAREWELL_TEXT.toString();
    }
}
