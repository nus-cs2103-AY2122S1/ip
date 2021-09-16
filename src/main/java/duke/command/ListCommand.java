package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.ResponsePair;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A Command class representing the 'Delete' command.
 */
public class ListCommand implements Command {

    private LocalDateTime time = null;

    /**
     * Create a new Command indicating all tasks are to be listed.
     * @param fullCommand Unedited user command.
     * @throws DukeException If the input string is not of the correct time format.
     */
    public ListCommand(String fullCommand) throws DukeException {
        try {
            String timeString = fullCommand.replace("list", "").trim();
            if (!timeString.isEmpty()) {
                this.time = LocalDateTime.parse(timeString);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date Time Format");
        }
    }

    /**
     * Execute user command.
     * @param tasks List of tasks.
     * @param ui UI of Duke Chatbot.
     * @param storage Storage of Duke Chatbot.
     * @return String of Duke chatbot response.
     * @throws DukeException If execution fails.
     */
    public ResponsePair execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the tasks in your list:";
        int listIndex = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = (Task) tasks.get(i);
            LocalDateTime taskTime = task.getTime();
            if (time != null && (taskTime == null || !taskTime.equals(time))) {
                continue;
            }
            response += String.format("\n%d. %s", listIndex++, task);
        }
        return new ResponsePair(response, isExit());
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
        return false;
    }

}
