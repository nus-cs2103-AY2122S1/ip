import duke.Duke;
/**
 * Main class for starting a Duke program.
 */
public class Main {

    /**
     * Create and run a duke program.
     *
     * @param args Will not be used.
     */
    public static void main(String[] args) {
        new Duke("data/cache.txt").run();
    }
}
