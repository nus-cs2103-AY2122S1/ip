package ponyo.ui;

import java.util.Scanner;
import java.io.InputStream;
import java.io.PrintStream;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";

    private static final String CMD_DIVIDER = "\t____________________________________________________________";

    private static final String LOGO =
            "    ____    ____    ____    __  __   ____ \n" +
            "   / __ \\  / __ \\  / __ \\  / / / /  / __ \\\n" +
            "  / /_/ / / /_/ / / / / / / /_/ /  / /_/ /\n" +
            " / .___/  \\____/ /_/ /_/  \\__, /   \\____/ \n" +
            "/_/                      /____/           \n";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcomeMessage() {
        show(LOGO,
                "Hello! I'm Ponyo.",
                "What can I do for you?",
                DIVIDER);
    }

    public void showLoadingError() {
        show("There was an error while loading your tasks.");
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        show(CMD_DIVIDER);
    }

    public void showError(String msg) {
        show(msg);
    }

    public void show(String... msg) {
        for (String m : msg) {
            out.println(m);
        }
    }
}