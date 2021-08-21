package aisu;

import java.io.PrintStream;

import java.util.Scanner;

/**
 * A User Interface for Aisu.
 *
 * @author Liaw Xin Yan
 */
public class Ui {
    private static final String DIVIDER = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=\n";
    private static final String LOGO = " (`-')  _   _               (`-').->           \n" +
            "(OO ).-/  (_)              ( OO)_       .->\n" +
            "/ ,---.   ,-(`-') (`-')   (_)--\\_) ,--.(,--.\n" +
            "| \\ /`.\\  | ( OO) ( OO).->/    _ / |  | |(`-')\n" +
            "'-'|_.' | |  |  )(,------.\\_..`--. |  | |(OO )\n" +
            "(|  .-. |(|  |_/  `------'.-._)   \\|  | | |  \\ \n" +
            "|  | |  | |  |'->         \\      / \\  '-'(_ .'\n" +
            "`--' `--' `--'             `-----'  `-----'   \n";
    private static final String INSTRUCTIONS = " * You can:\n" +
            " * 1) Type \"todo (task)\" - Add tasks without any date/time attached to it\n" +
            " * 2) Type \"list\" - Show list\n" +
            " * 3) Type \"done (taskNumber)\" - Mark task as done\n" +
            " * 4) Type \"deadline (task) /by (yyyy-mm-dd)\" - Add tasks that need to be done before a specific date/time\n" +
            " * 5) Type \"event (task) /at (date)\" - Add tasks that start at a specific time and ends at a specific time\n" +
            " * 6) Type \"bye\" - Exit program";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public String getInput() {
        return this.in.nextLine();
    }

    public void showDivider() {
        this.out.println(DIVIDER);
    }

    public void showWelcomeMessage() {
        this.out.println("\n" + LOGO + DIVIDER + " Hello, I'm Ai-su! How may I help you today?\n" + DIVIDER);
        this.out.println(INSTRUCTIONS + "\n\n" + DIVIDER + "\n");
    }

    public void showGoodbyeMessage() {
        this.out.println(LOGO + DIVIDER + " See you next time! :D\n" + DIVIDER);
    }

    public void showError(String message) {
        this.out.println(message);
    }

    /**
     * Shows message(s) to the user.
     *
     * @param message Message to be shown.
     */
    public void showToUser(String... message) {
        for (String m : message) {
            this.out.println(m);
        }
    }
}
