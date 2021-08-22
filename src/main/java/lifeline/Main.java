package lifeline;

import java.io.File;

/**
 * The Main class starts the program
 */
public class Main {

    /**
     * Starts the program
     *
     * @param args Does not accept any arguments
     */
    public static void main(String[] args) {
        Lifeline lifeline = new Lifeline("save" + File.separator + "tasks.json");
        lifeline.start();
    }
}
