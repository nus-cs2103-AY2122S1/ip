package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * FindCommand class encapsulates command to help find tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword Keyword to find a task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = 1;
        List<Task> filtered = tasks.stream().filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        System.out.println("Here are the matching tasks in your list:");
        for (Task task : filtered) {
            System.out.println(index + ". " + task);
            index++;
        }
    }
}


