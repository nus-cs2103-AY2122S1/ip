package duke;

import java.util.List;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        if (savedTasks.size() == 0) {
            System.out.println("No tasks in your list! Add one using todo, deadline or event!");
        }
        else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < savedTasks.size(); i++) {
                Task currTask = savedTasks.get(i);
                int index = i + 1;
                System.out.println(index + ". " + currTask);
            }
        }
    }
}
