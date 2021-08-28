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
    public static void parseInput(TaskList taskList, String commandInput) throws DukeException {
        Ui ui = new Ui();
        if (commandInput.equals("list")) {
            ui.printTasks(taskList);
        } else if (commandInput.matches("done\\s[0-9][0-9]?")) {
            int taskToComplete = Integer.valueOf(commandInput.split(" ")[1]);
            if (taskToComplete - 1 >= 0 && taskToComplete - 1 < taskList.getSize()) {
                taskList.markAsCompleted(taskToComplete - 1);
                ui.printTasks(taskList);
            } else {
                throw new InvalidTaskIdException();
            }
        } else if (commandInput.matches("delete\\s[0-9][0-9]?")) {
            int taskToComplete = Integer.valueOf(commandInput.split(" ")[1]);
            if (taskToComplete - 1 >= 0 && taskToComplete - 1 < taskList.getSize()) {
                taskList.delete(taskToComplete - 1);
                ui.printTasks(taskList);
            } else {
                throw new InvalidTaskIdException();
            }
        } else if (commandInput.matches("todo(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            } else {
                String taskname = commandInput.split(" ", 2)[1];
                ToDo todo = new ToDo(taskname);
                taskList.add(todo);
                ui.printTasks(taskList);
            }
        } else if (commandInput.matches("deadline(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            } else {
                String firstCommand = commandInput.split("/by", 2)[0];
                String taskname = firstCommand.split(" ", 2)[1];
                String dueDate = commandInput.split("/by", 2)[1];
                Deadline deadline = new Deadline(taskname.trim(), dueDate.trim());
                taskList.add(deadline);
                ui.printTasks(taskList);
            }
        } else if (commandInput.matches("event(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            } else {
                String firstCommand = commandInput.split("/at", 2)[0];
                String taskname = firstCommand.split(" ", 2)[1];
                String duration = commandInput.split("/at", 2)[1];
                Event event = new Event(taskname.trim(), duration.trim());
                taskList.add(event);
                ui.printTasks(taskList);
            }
        } else if (commandInput.matches("find\\s(.*?)")) {
            taskList.search(commandInput.split(" ")[1]);
        } else {
            throw new InvalidCommandException();
        }
    }
}
