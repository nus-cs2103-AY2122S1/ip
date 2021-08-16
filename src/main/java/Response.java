/**
 * Response class contains the logic for processing the commands from Duke.
 * At level-1, it supports (i) the bye command,
 * (ii) as well as echoing the user input
 */
public class Response {
    private final String input;

    /**
     * Constructor to initialise the Response class
     * @param input The input from the user to be echoed
     */
    Response(String input) {
        this.input = input;
    }

    /**
     * Echos the user input or says bye to the user depending on
     * the user input
     * @return a string that is either the user input,
     * or a farewell statement
     */
    String Echo() {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        }
        return input + "\n";
    }
}
