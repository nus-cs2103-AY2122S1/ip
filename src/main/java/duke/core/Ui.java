package duke.core;

/**
 * Ui encapsulates the user interface of text-based Duke which the user interacts with. It can greet the player
 * and print the results return by the commands.
 *
 * @author Clifford
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String HORIZONTAL_SEPARATOR =
            "------------------------------------------------------------------------";

    /**
     * Greets user when the user starts up the program.
     */
    public void greetUser() {
        formatDisplay("Hello from\n" + LOGO
                + "Hello! I'm Duke!\nHow may I be of service to you?");
    }

    /**
     * Decorates the results returned by commands to be presented to users in text-based Duke.
     *
     * @param input the results returned by command
     */
    public void formatDisplay(String input) {
        StringBuilder sb = new StringBuilder();
        StringBuilder formattedSb = sb
                .append(HORIZONTAL_SEPARATOR)
                .append("\n")
                .append(input)
                .append("\n")
                .append(HORIZONTAL_SEPARATOR);
        System.out.println(formattedSb);
    }
}
