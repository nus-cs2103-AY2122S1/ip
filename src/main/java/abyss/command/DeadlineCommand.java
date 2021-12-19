package abyss.command;

import java.io.IOException;

import abyss.Abyss;
import abyss.Ui;
import abyss.exception.InvalidDeadlineException;
import abyss.task.Task;

/**
 * Represents a command to add a deadline to the list of tasks.
 */
public class DeadlineCommand implements Command {
    private static final String DEADLINE_REGEX = "^\\S[ -~]*\\/d[ ]+\\S[ -~]*$";

    private String description;
    private String date;

    protected DeadlineCommand(String content) throws InvalidDeadlineException {
        if (!content.matches(DEADLINE_REGEX)) {
            throw new InvalidDeadlineException();
        }
        String[] parts = content.split("\\/d[ ]+", 2);
        this.description = parts[0];
        this.date = parts[1];
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Executes the add deadline command.
     *
     * @return Response from executing the add deadline command.
     * @throws IOException If there is error saving to file.
     */
    @Override
    public String execute() throws IOException {
        Task task = Abyss.getTaskManager().addDeadline(description, date);
        String response = Ui.replyTaskAdded(task);
        Abyss.getStorage().saveTasks(Abyss.getTaskManager());
        return response;
    }
}
