package duke;

import java.io.IOException;

public class Duke {
    private static final String STORAGE_LOCATION = "data/duke.txt";
    private TaskList tasks;

    public Duke(String fileLocation) throws IOException, FileParseException {
        Storage storage = new Storage(fileLocation);
        tasks = new TaskList(storage);
    }

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
