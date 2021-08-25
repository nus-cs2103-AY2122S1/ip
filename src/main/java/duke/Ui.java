package duke;
import java.util.Scanner;


/**
 * Acts as the interface for users to input their commands into Duke
 */

public class Ui {

    public Scanner input;

    public Ui() {
    }

    public String readInput() {
        return input.nextLine();
    }
}

