import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<String> storageLoad) {
        this.tasks = new ArrayList<>();

        for (String taskString : storageLoad) {
            // Extract task details into three parts
            String[] taskDetails = taskString.split(" \\| ", 4);
            Duke.TaskType type = Duke.TaskType.valueOf(taskDetails[0]);
            boolean isDone = taskDetails[1].equals("1");
            String description = taskDetails[2];

            // Create task based on the extracted details
            Task task = null;
            switch (type) {
            case TODO:
                task = new Todo(description);
                break;
            case EVENT:
                LocalDate at = LocalDate.parse(taskDetails[3]);
                task = new Event(description, at);
                break;
            case DEADLINE:
                LocalDate by = LocalDate.parse(taskDetails[3]);
                task = new Deadline(description, by);
                break;
            }

            // Add to the task list if and only if it is valid in data file
            if (task != null) {
                if (isDone)
                    task.markAsDone();
                tasks.add(task);
            }
        }
    }
}
