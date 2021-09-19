package duke;

import java.util.Scanner;

/**
 * Represents user interface
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructs an instance of <code>Ui</code>
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints loading error
     */
    public void showLoadingError() {
        System.out.println("Error loading save file :(");
    }

}
