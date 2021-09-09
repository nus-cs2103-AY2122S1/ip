package duke;

import java.util.Scanner;

// TODO bye does not actually exit the program

/**
 * Main class. Starts the program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    /**
     * Constructor.
     * @param filePath filepath to load tasks from
     */
    public Duke(String filePath) {
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Takes in a user input and returns duke's response
     * @param input user input
     * @return duke's response
     */
    String getResponse(String input) {
        Scanner scan = new Scanner(input);
        String returnStr = parser.scanInputs(scan, tasks);
        scan.close();
        if (returnStr.equals("Bye. Hope to see you again soon!")) {
            storage.saveToFile(tasks.getTasks());
            return returnStr + "\n" + Ui.getTasksSaved();
        }
        return returnStr;
    }
}
