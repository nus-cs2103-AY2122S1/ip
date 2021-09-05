package abyss.command;

import java.io.IOException;

import abyss.Abyss;
import abyss.Ui;
import abyss.task.Task;

public class EditDescriptionCommand extends EditCommand {
    private String description;
    private int index;

    protected EditDescriptionCommand(int index, String description) {
        this.index = index - 1;
        this.description = description;
    }

    /**
     * Executes the 'edit description' command.
     *
     * @return Response from executing the 'edit description' command.
     * @throws IOException If there is error saving to file.
     */
    @Override
    public String execute() throws IOException {
        Task task = Abyss.getTaskManager().editDescription(index, description);
        String response = Ui.replyTaskEdited(task);
        Abyss.getStorage().saveTasks(Abyss.getTaskManager());
        return response;
    }
}
