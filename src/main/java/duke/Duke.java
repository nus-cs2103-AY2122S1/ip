package duke;

import duke.command.Command;
import duke.notes.NotesList;
import duke.tasks.TaskList;

/**
 * Represents the task manager application Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private NotesList notes;
    private Ui ui;

    /**
     * Creates an instance of the Duke class.
     *
     * @param filePath Path of the file storing all the tasks on hard disk.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        notes = new NotesList(storage.loadNotes());
    }

    public String getResponse(String inputFromUser) {
        Command c = Parser.parse(inputFromUser);
        if(c.isTaskRelatedCommand()) {
            return c.execute(tasks, ui, storage);
        } else {
            return c.execute(notes, ui, storage);
        }

    }

    /**
     * Runs the Duke application and prompts users for input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            ui.showLine();

        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
