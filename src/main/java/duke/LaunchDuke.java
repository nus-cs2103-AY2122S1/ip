package duke;
import java.io.FileNotFoundException;


public class LaunchDuke {
    private static final String path = "./data/duke.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Duke duke = new Duke(path);
        duke.run();
    }
}