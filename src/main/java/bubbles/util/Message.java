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
            + "Supported commands: todo, deadline, event, list, done, delete, find, remind, help, bye;\n\n"
            + "Note: You must include `/by` for deadlines, and `/at` for events"
            + "after your task description! The date must be entered in the form"
            + "of yyyy-mm-dd, or else I won't be able to understand your command. ☹"),
    EXIT("Bye-bye! Hope to see you again soon!");

    private String message;

    Message(String message) {
        this.message = message;
    }

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
