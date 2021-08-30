package duke;

/**
 * 
 * Command to list existing tasks
 */
public class ListCommand extends Command{
    ListCommand() {
        
    }

    @Override
    public void execute(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            System.out.println(j + ". " + tasks.get(i));
        }
    }
}
