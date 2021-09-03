package bobbybot.commands;

import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class ListCommand extends Command {

    /**
     * Executes command and get response
     *
     * @param tasks   task list
     * @param ui      ui
     * @param storage storage
     * @return response string
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:");

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            response.append("\n").append(i + 1).append(". ");
            response.append(tasks.getTask(i));
        }
        return response.toString();
    }
}
