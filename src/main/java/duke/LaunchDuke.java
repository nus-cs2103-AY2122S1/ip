package duke;
import java.io.FileNotFoundException;

/**
 * A Class to initialize Duke Object and run the entire program
 * @author Jia Rong
 */
public class LaunchDuke {
    private static final String path = "./data/duke.txt";

    /**
     * Main method to run the duke object
     * @param args
     * @throws FileNotFoundException if scanner in storage class 
     * cannot locate the text file to be written to or read from.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Duke duke = new Duke(path);
        duke.run();
    }
}