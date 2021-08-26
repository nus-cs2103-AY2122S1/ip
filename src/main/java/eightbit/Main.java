package eightbit;

/**
 * Main driver class.
 */
public class Main {

    private static final String FILEPATH = "data/eightBit.txt";

    /**
     * Starts the execution of the program.
     */
    public static void main(String[] args) {
        EightBit eightBit = new EightBit(FILEPATH);
        eightBit.run();
    }
}
