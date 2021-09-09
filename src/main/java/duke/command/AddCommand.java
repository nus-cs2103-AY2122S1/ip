package duke.command;

import duke.*;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

public class AddCommand extends Command {

    private Task taskToAdd;

    /**
     * Create a new Command indicating a task is add.
     * @param fullCommand Unedited user command.
     */
    public AddCommand(String fullCommand) throws DukeException {
        parseTask(fullCommand);
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
        String description = descriptions[0].trim();
        String time = descriptions[1].trim();
        if (description.isEmpty() || time.isEmpty()) {
            throw new DukeException("Empty Deadline Command!");
        }
        this.taskToAdd = new DeadlineTask(description, time);
    }

    private void parseEvent(String fullCommand) throws DukeException {
        String[] descriptions = fullCommand.replace("event", "").split("/at");
        String description = descriptions[0].trim();
        String time = descriptions[1].trim();
        if (description.isEmpty() || time.isEmpty()) {
            throw new DukeException("Empty Event Command!");
        }
        this.taskToAdd = new EventTask(description, time);
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

    /**
     * Execute user command.
     * @param tasks List of tasks.
     * @param ui UI of Duke Chatbot.
     * @param storage Storage of Duke Chatbot.
     * @throws DukeException If execution fails.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(taskToAdd);
        storage.save(tasks);
        return String.format("Task Added!\n %s", taskToAdd);
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
        return false;
    }

}
