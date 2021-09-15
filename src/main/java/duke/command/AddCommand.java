package duke.command;

import duke.exceptions.DeadlineFormatException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EventFormatException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * Represents a command class that adds a task.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class AddCommand extends Command {

    /**
     * A constructor for AddCommand.
     *
     * @param tasks   A list of current Tasks.
     * @param parser  Parser to interpret user input.
     * @param storage Storage to store data
     * @param ui      Ui responsible for user interaction.
     */
    public AddCommand(TaskList tasks, Parser parser, Storage storage, Ui ui) {
        super(tasks, parser, storage, ui);
    }

    /**
     * Adds a to-do task to the current list of tasks.
     *
     * @return String representation of the added task.
     * @throws EmptyDescriptionException If user input an empty description.
     */
    public String addTodo() throws EmptyDescriptionException {
        String taskDescription = parser.getTodoDescription();
        Todo todo = new Todo(taskDescription, false);
        String addedTask = tasks.addTask(todo);
        storage.addTask(todo);
        return ui.showAddTodo(addedTask, String.valueOf(tasks.getSize()));
    }

    /**
     * Adds a deadline task to the current list of tasks.
     *
     * @return String representation of the added task.
     * @throws EmptyDescriptionException If user input an empty description.
     * @throws DeadlineFormatException   If user input description in the wrong format.
     */
    public String addDeadline() throws EmptyDescriptionException, DeadlineFormatException {
        String taskDescription = parser.getDeadlineDescription();
        String date = parser.getDeadlineDate();
        Deadline deadline = new Deadline(taskDescription, date, false);
        String addedTask = tasks.addTask(deadline);
        storage.addTask(deadline);
        return ui.showAddDeadline(addedTask, String.valueOf(tasks.getSize()));
    }

    /**
     * Adds an event task to the current list of tasks.
     *
     * @return String representation of the added task.
     * @throws EmptyDescriptionException If user input an empty description.
     * @throws EventFormatException      If user input description in the wrong format.
     */
    public String addEvent() throws EmptyDescriptionException, EventFormatException {
        String taskDescription = parser.getEventDescription();
        String date = parser.getEventDate();
        Event event = new Event(taskDescription, date, false);
        String addedTask = tasks.addTask(event);
        storage.addTask(event);
        return ui.showAddEvent(addedTask, String.valueOf(tasks.getSize()));
    }
}
