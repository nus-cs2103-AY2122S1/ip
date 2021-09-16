package abyss.command;

import java.io.IOException;

import abyss.Abyss;
import abyss.Ui;
import abyss.exception.InvalidEventException;
import abyss.task.Task;

/**
 * Represents a command to add an event to the list of tasks.
 */
public class EventCommand implements Command {
    private static final String EVENT_REGEX = "^\\S[ -~]*\\/d[ ]+\\S[ -~]*$";

    private String description;
    private String date;

    protected EventCommand(String content) throws InvalidEventException {
        if (!content.matches(EVENT_REGEX)) {
            throw new InvalidEventException();
        }
        String[] parts = content.split("\\/d[ ]+", 2);
        this.description = parts[0];
        this.date = parts[1];
    }

    /**
     * Executes the add event command.
     *
     * @return Response from executing the add event command.
     * @throws IOException If there is error saving to file.
     */
    @Override
    public String execute() throws IOException {
        Task task = Abyss.getTaskManager().addEvent(description, date);
        String response = Ui.replyTaskAdded(task);
        Abyss.getStorage().saveTasks(Abyss.getTaskManager());
        return response;
    }
}
