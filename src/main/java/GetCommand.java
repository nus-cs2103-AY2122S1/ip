import java.time.LocalDate;
import java.util.List;

public class GetCommand extends Command {
    private final LocalDate date;

    public GetCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        int counter = 0;
        for (Task i : savedTasks) {
            if (i instanceof Event) {
                if (((Event) i).getDate().equals(this.date)) {
                    System.out.println(i);
                    counter++;
                }
            }
            if (i instanceof Deadline) {
                if (((Deadline) i).getDeadline().equals(this.date)) {
                    System.out.println(i);
                    counter++;
                }
            }
        }
        if (counter == 0) {
            System.out.println("No tasks found!");
        }
    }
}
