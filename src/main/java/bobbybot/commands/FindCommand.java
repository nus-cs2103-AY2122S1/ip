package bobbybot.commands;

import bobbybot.util.PersonList;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class FindCommand extends Command {
    private final String keyword;
    /**
     * Finds all tasks with a keyword
     * @param keyword keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes command
     *  @param tasks   task list
     * @param ui      ui
     * @param storage storage
     * @param contacts
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, PersonList contacts) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks you're looking for sir!");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            String sentence = tasks.getTask(i).toString();
            if (sentence.toLowerCase().contains(keyword.toLowerCase())) {
                response.append("\n").append(i + 1).append(".").append(sentence);
            }
        }
        this.response = response.toString();
    }
}
