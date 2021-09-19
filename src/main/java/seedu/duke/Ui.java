package seedu.duke;

import java.util.stream.Stream;

/**
 * Represents a Ui object. A <code>Ui</code> object
 * outputs to the user based on given commands.
 */
public class Ui {

    /**
     * Dividing line for formatting Duke's replies.
     */
    public void divide() {
        StringBuilder builder = new StringBuilder(100);
        Stream.generate(() -> '-').limit(60).forEach(e -> builder.append(e));
        String line = String.format("%4s+%s+\n", " ", builder.toString());
        System.out.println(line);
    }

    /**
     * Outputs the given command to the console.
     *
     * @param message Message to be printed to user.
     */
    public void outputMessage(String message) {
        System.out.println(String.format("%4s%s", " ", message));
    }

}
