package duke;

import duke.main.DukeException;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;

import java.io.File;

/**
 * The entry point to the Duke chatbot.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Default constructor for GUI Launcher.
     */
    public Duke() {
        String filePath = System.getProperty("user.dir") + File.separator + "tasks.txt";
        storage = new Storage(filePath);
//        try {
//            taskList = storage.load();
//            ui.greetWithFamiliarity(taskList);
//        } catch (DukeException e) {
//            ui.showDukeException(e.getMessage());
//            storage.resetTasks();
//
//            if (taskList != null) {
//                taskList.clearTasks();
//            }
//        } finally {
//            parser = new Parser(storage, ui, taskList);
//        }
    }

//    /**
//     * Main method to start Duke
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        String filePath = System.getProperty("user.dir") + File.separator + "tasks.txt";
//        new Duke(filePath).run();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Starts the assistant.
     */
    public void run() {
        String input = ui.getNextInput();

        while (true) {
            try {
                boolean keepParsing = parser.parse(input);
                if (!keepParsing) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = ui.getNextInput();
        }
        ui.closeInput();
    }
}
