package duke;

import java.util.Scanner;
import java.io.File;

/**
 * This class is used to simulate an automatic list creator which saves its data and changes it
 * when the user interacts with it.
 */
public class Duke {
    /** The storage class used to store files for Duke. **/
    private static Storage storage;

    /** The UI Duke will use when processing data and user input. **/
    private static Ui ui;

    /** The list of tasks given to Duke (if any). **/
    private final TaskList tasks;

    /**
     * Constructor for Duke
     *
     * @param filePath the path of the file to be used when
     *                 starting Duke.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.load());
    }

    /**
     * Method used to begin processing of Duke.
     */
    public void run() {
        ui.welcomeMessage();
        String filePath = "data/duke.txt";
        File dukeFile = new File(filePath);

        File parentDir = dukeFile.getParentFile();
        if(!parentDir.exists()) {
            parentDir.mkdirs();
        }

        Ui.importantMessage();
        storage.printStartingFileContents();

        Scanner newScan = new Scanner(System.in);
        Parser.evaluateUserInput(newScan);
        Storage.writeFile();
        Ui.successfulWriteFileMessage();
        tasks.markTasksSaved();
        Ui.goodbyeMessage();

        System.exit(0);
    }

    /**
     * Main method to prompt Duke to run.
     *
     * @param args arguments to be used in the method
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}