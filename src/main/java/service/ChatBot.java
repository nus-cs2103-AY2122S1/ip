package service;

import java.util.List;

/**
 * DukeChatBot class.
 *
 * This class acts as a communicator to the user on the command line.
 */
public class ChatBot {

    private final static String LINE_SPLIT =
            "___________________________________________________________________________";

    /**
     * Displays the messaged wrapped with LINE_SPLIT.
     *
     * @param message message to display within LINE_SPLIT
     */
    public void info(String message) {
        System.out.println(LINE_SPLIT);
        System.out.println('\t' + message);
        System.out.println(LINE_SPLIT + '\n');
    }

    /**
     * Displays the strings in the input list all within one wrapping of LINE_SPLIT.
     *
     * @param messageList list of string messages to display
     */
    public void list(List<String> messageList) {
        System.out.println(LINE_SPLIT);
        for (String message: messageList) {
            System.out.println('\t' + message);
        }
        System.out.println(LINE_SPLIT + '\n');
    }

    /**
     * Uses the default system's println method to output the message string.
     *
     * @param string string to print in standard format
     */
    public void print(String string) {
        System.out.println(string);
    }

    /**
     * Generates the error template which is then displayed to the user.
     *
     * @param errorMessage error message to display
     */
    public void error(String errorMessage) {
        info("☹ Error: " + errorMessage);
    }
}
