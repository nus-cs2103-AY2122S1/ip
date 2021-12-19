package abyss.command;

import java.io.IOException;

import abyss.Abyss;
import abyss.Ui;
import abyss.exception.InvalidCommandException;
import abyss.task.Task;

/**
 * Represents a command to edit an existing to-do task.
 */
public class EditDateCommand extends EditCommand {
    private String date;
    private int index;

    protected EditDateCommand(int index, String date) {
        this.index = index - 1;
        this.date = date;
    }

    /**
     * Executes the 'edit to-do' command.
     *
     * @return Response from executing the 'edit to-do' command.
     * @throws IOException If there is error saving to file.
     */
    @Override
    public String execute() throws IOException, InvalidCommandException {
        Task task = Abyss.getTaskManager().editDate(index, date);
        String response = Ui.replyTaskEdited(task);
        Abyss.getStorage().saveTasks(Abyss.getTaskManager());
        return response;
    }
}
