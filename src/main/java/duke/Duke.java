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
        tasks = new TaskList(storage.getTasksFromMemory());
        notes = new NotesList(storage.getNotesFromMemory());
    }

    /**
     * Gets a response to the input entered by the user.
     *
     * @param inputFromUser String input entered by the user.
     * @return Result of executing the command in response to the input entered by the user.
     */
    public String getResponse(String inputFromUser) {
        Command c = Parser.parse(inputFromUser);
        return c.execute(tasks, notes, ui, storage);
    }
}
