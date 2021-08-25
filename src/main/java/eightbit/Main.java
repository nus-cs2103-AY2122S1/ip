package eightbit;

/**
 * Main driver class.
 */
public class Main {

    private static final String FILEPATH = "data/eightBit.txt";

    /**
     * Main driver method.
     */
    public static void main(String[] args) {
        EightBit eightBit = new EightBit(FILEPATH);
        eightBit.run();
    }
}
