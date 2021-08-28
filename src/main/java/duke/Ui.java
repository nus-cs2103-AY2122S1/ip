package duke;

import java.util.Scanner;

/**
 * Represents a user interface that the user can input commands to and read from
 */
class Ui {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Returns next line of input from user
     *
     * @return Next line
     */
    static String nextLine() {
        return sc.nextLine();
    }

    /**
     * Shows output to user
     *
     * @param lines lines to show user
     */
    static void show(String... lines) {
        System.out.println("\t____________________________________");
        for (String line : lines) {
            System.out.println("\t " + line);
        }
        System.out.println("\t____________________________________");
    }
}
