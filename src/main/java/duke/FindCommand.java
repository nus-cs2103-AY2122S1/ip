package duke;

import java.util.List;

public class FindCommand extends Command {
    private final String sequence;

    public FindCommand(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        int counter = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task i : savedTasks) {
            if (i.getBody().contains(this.sequence)) {
                System.out.println(counter + ". " + i);
                counter++;
            }
        }
    }
}
