package duke;

import java.util.Scanner;

public class Ui {

    private Scanner myScanner;

    public Ui() {
        myScanner = new Scanner(System.in);
    }

    public String getInput() {
        return myScanner.nextLine();
    }
}
