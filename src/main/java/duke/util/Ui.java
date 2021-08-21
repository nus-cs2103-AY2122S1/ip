package duke.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Represents the user interface that handles user's input and program output. */
public class Ui {
    /** The reader that reads from system input. */
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    /** The dividing line. */
    private static final String LINE = "\t************************************************************";

    /** The Duke logo. */
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Prints the dividing line. */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints the error message.
     *
     * @param errorMessage The error message from a caught exception.
     */
    public void showError(String errorMessage) {
        System.out.println("\t" + errorMessage);
    }

    /** Indicates the program found a save and is loading the data from storage. */
    public void showLoadSuccess() {
        System.out.println("Found a save, loading......");
    }

    /** Indicates the program could not load data from storage. */
    public void showLoadingError() {
        System.out.println("No saves found!");
    }

    /** The text to be displayed when program starts. */
    public void showWelcome() {
        System.out.println("Hello from");
        System.out.println(LOGO);
        showLine();
        print("\033[3m*booting up......*\033[0m", "I'm the Hui Zhuan Bot v2.0!", "What do you want me to do?");
        showLine();
    }

    /**
     * Reads the system input and returns the input as a string.
     *
     * @return String representation of the system input.
     * @throws IOException If an input exception occurred.
     */
    public String readCommand() throws IOException {
        return READER.readLine();
    }

    /**
     * Closes the reader.
     *
     * @throws IOException If an input exception occurred.
     */
    public void cleanup() throws IOException {
        READER.close();
    }

    /**
     * Prints the provided variable length argument of strings.
     *
     * @param input A variable length argument of strings.
     */
    public void print(String...input) {
        for (String out : input) {
            System.out.println("\t" + out);
        }
    }
}
