package duke.util;

import java.util.Scanner;
import java.io.File;

/**
 * This class is used to simulate an automatic list creator which saves its data and changes it
 * when the user interacts with it.
 */
public class Duke {
    private static Storage storage;
    private static Ui ui;
    private final TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.load());
    }

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
        storage.writeFile();
        Ui.successfulWriteFile();
        tasks.markTasksSaved();
        Ui.goodbyeMessage();

        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}