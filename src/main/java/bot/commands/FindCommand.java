package bot.commands;

import java.util.List;

import bot.tasks.Task;
import bot.utility.TaskList;

public class FindCommand extends Command {
    private final String keyWord;
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }
    @Override
    public String execute() {
        message = new StringBuilder();
        message.append("\n\t Here are the matching tasks in your list:");
        List<Task> tasks = TaskList.showTasks();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyWord)) {
                message.append("\n\t ").append(i + 1).append(". ").append(tasks.get(i));
            }
        }
        return message.toString();
    }
}
