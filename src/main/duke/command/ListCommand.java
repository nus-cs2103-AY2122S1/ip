package duke.command;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

public class ListCommand extends Command {

    private LocalDateTime time = null;
    
    public ListCommand(String fullCommand) throws DukeException {
        String timeString = fullCommand.replace("list", "").trim();
        if (!timeString.isEmpty()) {
            try {
                this.time = LocalDateTime.parse(timeString);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid Date Time Format");
            }
        }
    }

    /**
     * Execute user commands.
     * @param tasks List of tasks.
     * @param ui Ui of Duke chatbot.
     * @param storage Storage of Duke chatbot.
     * @throws DukeException If execution fails.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the tasks in your list:";
        int listIndex = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = (Task) tasks.get(i);
            LocalDateTime taskTime = t.getTime();
            if (time != null && (taskTime == null || !taskTime.equals(time))) {
                continue;
            }
            response += String.format("\n%d. %s", listIndex++, t);
        }
        System.out.println(response);
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
        return false;
    }

}
