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
     * Shows appropriate error messages to user.
     *
     * @param e Error to be shown.
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
     * Prints a list of objects.
     *
     * @param tasks   List of items to be printed.
     * @param heading The heading to precede the list of items.
     */
    public String printAll(List<?> tasks, String heading) {
        String s = "";
        s += INDENT + heading + INDENT + "\n";
        for (int i = 1; i <= tasks.size(); i++) {
            s += (INDENT + i + " " + tasks.get(i - 1) + INDENT + "\n");
        }
        return s;
    }

    /**
     * Prints duke's response to the user.
     *
     * @param input Duke's response as a String.
     */
    public String echo(String input) {
        assert input.length() > 0;
        return input;
    }
}

