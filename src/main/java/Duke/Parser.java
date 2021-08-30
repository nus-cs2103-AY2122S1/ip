package duke;

import duke.exception.DukeException;
import duke.task.Task;

public class Parser {
    private final TaskList tasks;
    private boolean isExit;

    /**
     * Constructs a parser class that parsers user input.
     *
     * @param tasks list of tasks
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.isExit = false;
    }
    /**
     * Adds user inputted String to list and prints the user added tasks and the current number of tasks, else
     * print error message for the error
     *
     * @param userInput user inputted String
     */
    public String getResponse(String userInput) {
        Task newTask;
        String[] tokens = userInput.split(" ");
        try {
            switch (tokens[0]) {
            case "done":
                return tasks.completeTask(userInput);
            case "delete":
                return tasks.deleteTask(userInput);
            case "todo":
                newTask = tasks.makeToDoTask(userInput);
                break;
            case "deadline":
                newTask = tasks.makeDeadlineTask(userInput);
                break;
            case "event":
                newTask = tasks.makeEventTask(userInput);
                break;
            case "bye":
                if (tokens.length > 1) {
                    throw new DukeException("Invalid command! Try again.");
                }
                this.isExit = true;
                return UI.bye();
            case "list":
                if (tokens.length > 1) {
                    throw new DukeException("Invalid command! Try again.");
                }
                return tasks.listToString();

            case "find":
                return tasks.findTasks(userInput);
            default:
                throw new DukeException("Invalid command! Try again.");
            }
        } catch (DukeException err) {
            return err.getMessage();
        }
        tasks.addTask(newTask);
        return "You have added this following task to the list: \n" + newTask.toString() + "\n"
                + "You have " + tasks.noOfTasks() + " tasks now.";
    }
    /**
     * Return true if parser read user input of "bye".
     *
     * @return true if parser has exited else false
     */
    public boolean isExit() {
        return this.isExit;
    }
}
