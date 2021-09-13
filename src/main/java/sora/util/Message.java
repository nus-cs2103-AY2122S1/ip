package sora.util;

/**
 * Stores the messages which UI uses to print.
 *
 * @author Zhang Shi Chen
 */
public enum Message {
    HORIZONTAL_LINE("\t____________________________________________________________\n"),
    WELCOME("     ____________________________  ______\n"
            + "    /  _______/  _____   / ____  \\/ _   |\n"
            + "   /  /______   /    /  / /____/ / /_|  |\n"
            + "  /______   /  /    /  /  _   __/  __   |\n"
            + " _______/  /  /____/  /  / \\  \\/  /  |  |\n"
            + "/_________/__________/__/   \\__\\_/   |__|\n"
            + "Hi! I'm Sora. How can I help you?"),
    EXIT("Have a nice day! Good bye XD\nJust a second, saving all your hard works :)"),
    DONE("Congrats! You have accomplished the following task:"),
    ADD("Alright, new task added to the list:"),
    DELETE("Sure, I've deleted this task:"),
    LIST("Here are the tasks in your list:"),
    HELP("Name: Sora\n"
            + "Version: The creator lost count...\n"
            + "Supported Features: help, bye, list, todo, deadline, event, done, delete, find, sort");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    /**
     * Returns a string representation of the message
     *
     * @return A string representation of the message
     */
    @Override
    public String toString() {
        return message;
    }
}
