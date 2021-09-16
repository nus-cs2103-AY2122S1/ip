package duke;

import java.util.List;

import javafx.application.Platform;

/**
 * Class that interacts with the user.
 */
public class Ui {
    private static final String INDENT = "    ";
    /**
     * Constructor for duke's UI.
     */
    public Ui() {
    }

    /**
     * Returns String representation of an error.
     *
     * @param e Error to be shown.
     * @return String representation of the error.
     */
    public String showError(DukeException e) {
        return echo(e.toString());
    }

    /**
     * Closes duke's UI.
     */
    public void exit() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Returns String representation of a list of objects.
     *
     * @param items List of items to be printed.
     * @param heading Heading to precede the list of items.
     * @return String representation of all the items.
     */
    public String printAll(List<?> items, String heading) {
        String s = "";
        s += INDENT + heading + INDENT + "\n";
        for (int i = 1; i <= items.size(); i++) {
            s += (INDENT + i + " " + items.get(i - 1) + INDENT + "\n");
        }
        return s;
    }

    /**
     * Returns String representation of Duke's response to user.
     *
     * @param input Duke's response.
     * @return String representation of Duke's response.
     */
    public String echo(String input) {
        assert input.length() > 0;
        return input;
    }
}

