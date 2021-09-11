package duke.utils;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyTaskDescriptionException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTaskIdException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

/**
 * The Parser class is responsible for handling the user input and to perform
 * the necessary operations on the TaskList.
 */
public class Parser {

    /**
     * Reads the user input and performs the corresponding action to the TaskList.
     *
     * @param taskList A TaskList object storing tasks.
     * @param commandInput Input provided by the user.
     * @throws DukeException If the user input is an invalid command.
     */
    public static TaskList parseInput(TaskList taskList, String commandInput) throws DukeException {
        if (commandInput.equals("list")) {
            return taskList;
        } else if (commandInput.matches("done\\s[0-9][0-9]?")) {
            int taskToComplete = Integer.valueOf(commandInput.split(" ")[1]);
            if (taskToComplete < 0 || taskToComplete >= taskList.getSize()) {
                throw new InvalidTaskIdException();
            }
            taskList.markAsCompleted(taskToComplete - 1);
            return taskList;
        } else if (commandInput.matches("delete\\s[0-9][0-9]?")) {
            int taskToComplete = Integer.valueOf(commandInput.split(" ")[1]);
            if (taskToComplete < 0 || taskToComplete >= taskList.getSize()) {
                throw new InvalidTaskIdException();
            }
            taskList.delete(taskToComplete - 1);
            return taskList;
        } else if (commandInput.matches("todo(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            }
            String taskname = commandInput.split(" ", 2)[1];
            ToDo todo = new ToDo(taskname);
            taskList.add(todo);
            return taskList;
        } else if (commandInput.matches("deadline(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            }
            String firstCommand = commandInput.split("/by", 2)[0];
            String taskname = firstCommand.split(" ", 2)[1];
            String dueDate = commandInput.split("/by", 2)[1];
            Deadline deadline = new Deadline(taskname.trim(), dueDate.trim());
            taskList.add(deadline);
            return taskList;
        } else if (commandInput.matches("event(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            }
            String firstCommand = commandInput.split("/at", 2)[0];
            String taskname = firstCommand.split(" ", 2)[1];
            String duration = commandInput.split("/at", 2)[1];
            Event event = new Event(taskname.trim(), duration.trim());
            taskList.add(event);
            return taskList;
        } else if (commandInput.matches("find\\s(.*?)")) {
            return taskList.search(commandInput.split(" ")[1]);
        } else {
            throw new InvalidCommandException();
        }
    }
}
