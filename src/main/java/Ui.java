import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * The Ui class deals with interactions with the user.
 * It deals with error messages and printing texts.
 */
public class Ui {
    // constants declaration
    private static final String DIVIDER = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=\n";
    private static final String LOGO = " (`-')  _   _               (`-').->           \n" +
            "(OO ).-/  (_)              ( OO)_       .->\n" +
            "/ ,---.   ,-(`-') (`-')   (_)--\\_) ,--.(,--.\n" +
            "| \\ /`.\\  | ( OO) ( OO).->/    _ / |  | |(`-')\n" +
            "'-'|_.' | |  |  )(,------.\\_..`--. |  | |(OO )\n" +
            "(|  .-. |(|  |_/  `------'.-._)   \\|  | | |  \\ \n" +
            "|  | |  | |  |'->         \\      / \\  '-'(_ .'\n" +
            "`--' `--' `--'             `-----'  `-----'   \n";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void getInput() {
        String input = this.in.nextLine();
    }

    public void showDivider() {
        this.out.println(DIVIDER);
    }

    public void showWelcomeMessage() {
        this.out.println(LOGO + DIVIDER + " Hello, I'm Ai-su! How may I help you today?\n" + DIVIDER);
    }

    public void showGoodbyeMessage() {
        this.out.println(LOGO + DIVIDER + " See you next time! :D\n" + DIVIDER);
    }

    public void showWrongFormatError(String type) {

    }

    public void showOutOfBoundsError() {

    }

    /** Shows message(s) to the user, called depending on context */
    public void showToUser(String... message) {
        for (String m : message) {
            this.out.println(m);
        }
    }


}
