import java.io.PrintStream;
import java.util.Scanner;

class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETINGS = "Hello! What can I do for you?\n";
    private static final String GOODBYE = "See you later.";

    private Scanner in;
    private PrintStream out;

    public Ui() {
        in = new Scanner(System.in);
        out = System.out;
    }

    public void showWelcome() {
        out.println(LOGO);
        out.println(GREETINGS);
    }

    public void showGoodbye() {
        out.println(GOODBYE);
    }

    public void showLoadingError() {
        out.println("Could not load your saved tasks. Creating a new task list...");
    }

    public void showSavingError(String msg) {
        out.println("Error while saving tasks: \n" + msg);
    }

    public void showHorizontalLine() {
        out.println("-------------------------------------------------------------");
    }

    public void showBlankLine() {
        out.println();
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    public void showResultToUser(CommandResult result) {
        out.println(result.getMessage());
    }

    public void close() {
        in.close();
        out.close();
    }
}
