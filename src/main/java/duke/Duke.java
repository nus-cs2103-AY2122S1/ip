package duke;

import java.io.IOException;

import duke.util.*;


/**
 * Represents the core of Duke.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Represents a new Duke project.
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        parser = new Parser(taskList, ui, storage);
    }

//    /**
//     * Starts the program
//     */
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            String fullCommand = ui.readCommand();
//            ui.showLine();
//            isExit = parser.isExit(fullCommand);
//            if (!isExit) {
//                parser.parse(fullCommand);
//            }
//        }
//        ui.showGoodBye();
//    }

    public String getResponse(String input) {
        try {
            return parser.parse(input.trim());
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("data/tasks.txt");
        String result = duke.getResponse("list");
        System.out.println(result);
    }
}
