package duke;

import java.util.List;

public class FindCommand extends Command {
    private final String sequence;

    public FindCommand(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        String message = "Here are the matching tasks in your list:\n";
        int counter = 1;
        for (Task i : savedTasks) {
            if (i.getBody().contains(this.sequence)) {
                message += counter + ". " + i + "\n";
                counter++;
            }
        }
        return message;
    }
}
