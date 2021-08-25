package duke;

public class InputParser {
    public InputParser() {

    }

    /**
     * Returns the command keyword from the user's input.
     *
     * @param input Command line input from the user.
     * @return The command given by the user in string format.
     */
    public String getCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Returns the String literal of the user's input describing the task to be added.
     *
     * @param input Command line input from the user.
     * @return The subsequent string of words after the keyword.
     */
    public String getDescription(String input) {
        return input.split(" ", 2)[1];
    }
}
