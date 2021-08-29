import java.io.FileNotFoundException;
import java.io.IOException;

import duke.Duke;

public class Main {
    /**
     * Runs the main Duke ToDo Application
     * 
     * @param args
     * @throws FileNotFoundException when file is not found in the given location.
     * @throws IOException           when Scanner is not able to scan anything from
     *                               the given file path.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Duke duke = new Duke();
        duke.start();
    }
}