package duke.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner in;

    private static final String DIVIDER = "-----------------------------------------------------------------------------------------";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads input from user.
     *
     * @return User input.
     */
    public String readFromUser() {
        return in.nextLine();
    }

    /**
     * Prints output.
     *
     * @param output Output that is to be printed.
     */
    public void print(String output) {
        System.out.println(output);
    }

    /**
     * Prints formatted output.
     *
     * @param output Output that is to be printed.
     */
    public void print(String output, Object... args) {
        System.out.printf(output, args);
    }

    /**
     * Prints divider line.
     */
    public void printDivider() {
        print(DIVIDER);
    }
}
