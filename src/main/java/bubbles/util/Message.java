package bubbles.util;

/**
 * Stores the messages which UI uses to print, currently only includes
 * Greeting and Departure messages.
 */
public enum Message {
    FORMAT_LINE("--------------------------------------------------"),
    LOGO("Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"),
    WELCOME("You should do what you want to do!\n"
            + "Hello I'm Bubbles from the Powerpuff Girls, what are you up to?"),
    EXIT("Bye-bye! Hope to see you again soon!");

    private String message;

    Message(String message) {
        this.message = message;
    }

    public static String separateMessage(String message) {
        return FORMAT_LINE.toString() + "\n" + message + "\n" + FORMAT_LINE.toString();
    }

    /**
     * Returns a string representation of the message.
     *
     * @return A string representation of the message.
     */
    @Override
    public String toString() {
        return this.message;
    }
}
