import Duke.Duke;

public class Main {
    /**
     * Main entrypoint to the Duke program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            Duke duke = new Duke();
            duke.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
