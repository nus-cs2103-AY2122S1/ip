package main.java;

/**
 * A class that handles exceptions for the Chatbot.
 */
public class DukeException extends Exception {

    /**
     * Constructor for the DukeException class
     */
    DukeException(String errorMessage) {
        super("☹ OOPS!!! It looks like was an error handling your request!\nThe error is as follows:\n\n" + errorMessage);
    }
}
