package duke;

import javafx.application.Application;
/**
 * This is Duke, a program that serves as a ToDo-List.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke.
     * Used to initialise Duke.
     *
     * @param filePath String representation of the path where Duke will be run from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Returns the start-up message.
     * If there is saved tasks, display the tasks. Otherwise, start a new ToDo List.
     *
     * @return the start-up message.
     */
    public String startUp() {
        if (taskList.isEmpty()) {
            return "No previous data found.\nLet's start a new To-Do List!";
        } else {
            String str = ui.greetExistingUser();
            str += ui.displayListUi(taskList);
            return str;
        }
    }

    public String getResponse(String input) {
        return run(input);
    }

    /**
     * Runs the program.
     * Will keep running until an exit command is given.
     */
    public String run(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.displayDukeExceptionMessage(e);
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
