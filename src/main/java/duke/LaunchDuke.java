package duke;
import java.io.FileNotFoundException;

public class LaunchDuke {
    private static final String FilePath = "./data/duke.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Duke duke = new Duke(FilePath);
        duke.run();
    }
}