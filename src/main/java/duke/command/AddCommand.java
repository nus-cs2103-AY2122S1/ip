package duke.command;

import duke.exceptions.DeadlineFormatException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EventFormatException;
import duke.parser.Parser;
import duke.taskList.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

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
     * @param tasks A list of current Tasks.
     * @param input User input.
     */
    public AddCommand(TaskList tasks, String input) {
        super(tasks, input);
    }

    /**
     * Adds a to-do task to the current list of tasks.
     *
     * @return String representation of the added task.
     * @throws EmptyDescriptionException If user input an empty description.
     */
    public String addTodo() throws EmptyDescriptionException {
        Parser parser = new Parser(input);
        String taskDescription = parser.getTodoDescription();
        Todo todo = new Todo(taskDescription, false);
        String addedTask = tasks.addTask(todo);
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have "
                + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Adds a deadline task to the current list of tasks.
     *
     * @return String representation of the added task.
     * @throws EmptyDescriptionException If user input an empty description.
     * @throws DeadlineFormatException   If user input description in the wrong format.
     */
    public String addDeadline() throws EmptyDescriptionException, DeadlineFormatException {
        Parser parser = new Parser(input);
        String taskDescription = parser.getDeadlineDescription();
        String date = parser.getDeadlineDate();
        Deadline deadline = new Deadline(taskDescription, date, false);
        String addedTask = tasks.addTask(deadline);
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have "
                + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Adds an event task to the current list of tasks.
     *
     * @return String representation of the added task.
     * @throws EmptyDescriptionException If user input an empty description.
     * @throws EventFormatException      If user input description in the wrong format.
     */
    public String addEvent() throws EmptyDescriptionException, EventFormatException {
        Parser parser = new Parser(input);
        String taskDescription = parser.getEventDescription();
        String date = parser.getEventDate();
        Event event = new Event(taskDescription, date, false);
        String addedTask = tasks.addTask(event);
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have "
                + tasks.getSize() + " tasks in the list.";
    }
}
