package duke.ui;

import java.util.Scanner;

/**
 * Class containing methods for showing text to the user.
 */
public class Ui {
    private static final Scanner in = new Scanner(System.in);

    /**
     * Prints output to console.
     *
     * @param output Output message.
     */
    public static void printToConsole(String output) {
        System.out.println(output);
    }
}
