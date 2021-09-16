package duke.command;

import duke.DukeException;
import duke.ResponsePair;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * A Command class representing the 'Add' command.
 */
public class AddCommand implements Command {

    private Task taskToAdd;

    /**
     * Create a new Command indicating a task is add.
     * @param fullCommand Unedited user command.
     * @throws DukeException If parseTask has an error.
     */
    public AddCommand(String fullCommand) throws DukeException {
        parseTask(fullCommand);
    }

    /**
     * Differentate which tasks user is adding.
     * @param fullCommand Unedited user command.
     * @throws DukeException If an invalid command format is written.
     */
    private void parseTask(String fullCommand) throws DukeException {
        String type = fullCommand.split(" ")[0];
        switch (type) {
        case "todo":
            parseTodo(fullCommand);
            break;
        case "deadline":
            parseDeadline(fullCommand);
            break;
        case "event":
            parseEvent(fullCommand);
            break;
        default:
            throw new DukeException("Invalid Command!");
        }
    }

    private void parseTodo(String fullCommand) throws DukeException {
        String description = fullCommand.replace("todo", "").trim();
        if (description.isEmpty()) {
            throw new DukeException("Empty Todo Command!");
        }
        this.taskToAdd = new TodoTask(description);
    }

    private void parseDeadline(String fullCommand) throws DukeException {
        String[] descriptions = fullCommand.replace("deadline", "").split("/by");
        try {
            String description = descriptions[0].trim();
            String time = descriptions[1].trim();
            if (description.isEmpty() || time.isEmpty()) {
                throw new DukeException("Empty Deadline Command!");
            }
            this.taskToAdd = new DeadlineTask(description, time);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Wrong Format!");
        }
    }

    private void parseEvent(String fullCommand) throws DukeException {
        String[] descriptions = fullCommand.replace("event", "").split("/at");
        try {
            String description = descriptions[0].trim();
            String time = descriptions[1].trim();
            if (description.isEmpty() || time.isEmpty()) {
                throw new DukeException("Empty Event Command!");
            }
            this.taskToAdd = new EventTask(description, time);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Wrong Format!");
        }
    }

    /**
     * Execute user command.
     * @param tasks List of tasks.
     * @param ui UI of Duke Chatbot.
     * @param storage Storage of Duke Chatbot.
     * @return Duke's Response.
     * @throws DukeException If execution fails.
     */
    public ResponsePair execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        addTask(tasks);
        storage.save(tasks);
        return new ResponsePair(String.format("Task Added!\n %s", taskToAdd), isExit());
    }

    private void addTask(TaskList tasks) throws DukeException {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = (Task) tasks.get(i);
            if (task.equals(this.taskToAdd)) {
                throw new DukeException("Duplicate Found!");
            }
        }
        tasks.add(taskToAdd);
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
        return false;
    }

}
