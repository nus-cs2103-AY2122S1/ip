package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        System.out.println("Here are the matching tasks in your list:");
        boolean hasMatch = false;
        int matchCount = 0;
        for (int i = 0; i < tasks.getSize(); i++) {
            Task t = tasks.get(i);
            if (t.containsKeyword(this.keyword)) {
                matchCount++;
                System.out.println(String.format("%d. %s", matchCount, t));
                hasMatch = true;
            }
        }
        if (!hasMatch) {
            System.out.println("No matches so far.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
