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
    ADD("Received order! I've added this task:\n"),
    DELETE("Noted! I've removed this task:\n"),
    DONE("Good job! I've marked this task as done:\n"),
    REWARD("Bubbles will reward you with a piece of candy!"),
    REMIND("Need a reminder? Bubbles' got you!\n"),
    WELCOME("You should do what you want to do!\n"
            + "Hello I'm Bubbles from the Powerpuff Girls, what are you up to?"),
    HELP("Don't worry, Bubbles is here to help!\n\n"
            + "Below is a list of supported commands and format:\n"
            + "help       /help\n"
            + "todo       /todo [description]\n"
            + "deadline   /deadline [description] /by yyyy-mm-dd\n"
            + "event      /event [description] /at yyyy-mm-dd\n"
            + "list       /list\n"
            + "done       /done [index]\n"
            + "delete     /delete [index]\n"
            + "find       /find [word]\n"
            + "remind     /remind\n"
            + "bye        /bye\n\n"
            + "I will not understand your command if it is not in the right format. ☹"),
    EXIT("Bye-bye! Hope to see you again soon!");

    private String message;

    Message(String message) {
        this.message = message;
    }

    /**
     * Separates each chunk of message using the separator line.
     *
     * @param message The message to be kept within the separator lines.
     * @return A String representing the message and two separator lines around it.
     */
    public static String separateMessage(String message) {
        return FORMAT_LINE + "\n" + message + "\n" + FORMAT_LINE;
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
