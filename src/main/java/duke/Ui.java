package duke;

import java.util.Scanner;

public class Ui {

    private Scanner myScanner;

    /**
     * initialises the Ui, and creates the scanner object.
     */
    public Ui() {
        myScanner = new Scanner(System.in);
    }

    /**
     * get the user's next input
     * @return String
     */
    public String getInput() {
        return myScanner.nextLine();
    }
}
