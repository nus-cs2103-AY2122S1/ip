package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.*;
import java.time.LocalDate;

/**
 * Implements the logic for adding a Task to the users list.
 */
public class AddCommand extends Command {
    private final String type;
    private final String label;
    private LocalDate date;

    /**
     * Constructor called when adding a Todo task to the list.
     * @param type Type tells execute method the kind of Task object to create.
     * @param label Label is a description of the Task to be added.
     */
    public AddCommand(String type, String label) {
        this.type = type;
        this.label = label;
    }

    /**
     * Constructor called when adding a Deadline or Event Task to the list.
     * @param type Type tells execute method the kind of Task object to create.
     * @param label Label is a description of the Task to be added.
     * @param date Date is an added description accompanying Deadline and Event tasks.
     */
    public AddCommand(String type, String label, LocalDate date) {
        this.type = type;
        this.label = label;
        this.date = date;
    }

    /**
     * Adds a Task to the TaskList. Differentiates Task objects based on their label
     * before adding them to the TaskList.
     * @param tasklist TaskList that contains all the users current tasks.
     * @param ui Ui object for interaction with user
     * @param store Storage object that handles save and load functionality.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) {
        if (type.equals("todo")) {
            tasklist.add(new Todo(label));
        } else if (type.equals("deadline")) {
            tasklist.add(new Deadline(label, date));
        } else {
            tasklist.add(new Event(label, date));
        }
        ui.notifySuccessfulAdd(tasklist);
    }
}
