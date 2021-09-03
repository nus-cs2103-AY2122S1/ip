package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        boolean hasMatch = false;
        int matchCount = 0;
        for (int i = 0; i < tasks.getSize(); i++) {
            Task t = tasks.get(i);
            if (t.containsKeyword(this.keyword)) {
                matchCount++;
                response.append(String.format("%d. %s\n", matchCount, t));
                hasMatch = true;
            }
        }
        if (!hasMatch) {
            return "No matches so far.";
        }
        return response.toString();
    }

    public boolean isExit() {
        return false;
    }
}
