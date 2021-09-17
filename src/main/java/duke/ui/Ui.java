package duke.ui;
import java.util.Scanner;

public class Ui {
    /**
     * Scanner that will be used for scannign user's input
     */
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints welcome message when Duke is run.
     */
    public void showWelcome() {
        String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo + "\nHow can I help?");
    }

    /**
     * @return command inputted by user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the output as a result of a command.
     *
     * @param output output string to be printed.
     */
    public void printOutput(String output) {
        System.out.println(output);
    }

    /**
     * Prints error message
     *
     * @param error error message to be printed.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints a line to indicate the start and end of the output.
     */
    public void showLine() {
        System.out.println("-------------------------------------------------------------------------------");
    }

    /**
     * Prints error message if the filepath could not be found.
     */
    public void showLoadingError() {
        System.out.println("☹ OOPS!!! File couldn't be loaded");
    }

}
