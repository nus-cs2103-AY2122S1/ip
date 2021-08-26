package duke;

import java.io.IOException;

/**
 * Represents the entry point of the Duke application.
 *
 * @author botr99
 */
public class Duke {
    private static final String STORAGE_LOCATION = "data/duke.txt";
    private TaskList tasks;

    /**
     * Constructs a Duke that reads in tasks from the user's hard disk.
     *
     * @param fileLocation The string of the file path to access the tasks.
     * @throws IOException When an error occurs when reading or writing to the file.
     * @throws FileParseException When the file is not of the right format.
     */
    public Duke(String fileLocation) throws IOException, FileParseException {
        Storage storage = new Storage(fileLocation);
        tasks = new TaskList(storage);
    }

    /**
     * Starts the Duke application.
     */
    public void run() {
        new Ui(tasks).start();
    }

    public static void main(String[] args) {
        try {
            new Duke(STORAGE_LOCATION).run();
        } catch (IOException e) {
            System.out.println("Something went wrong with accessing " + STORAGE_LOCATION);
        } catch (FileParseException e) {
            System.out.println(STORAGE_LOCATION + " is not of the proper format. "
                    + "Either modify it to the right format or delete the file.");
        }
    }
}
