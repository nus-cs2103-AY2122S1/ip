import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public String add(Task task) {
        tasks.add(task);
        return "Got it. I've added this task:\n  " + task + '\n' + "You have " + numTasks() + " tasks in the list\n";
    }

    public String markTaskDone(String userInput) throws MalformedCommandException {
        try {
            String userProvidedIndex = userInput.split(" ", 2)[1];
            int taskIndex = Integer.valueOf(userProvidedIndex) - 1;
            Task task = tasks.get(taskIndex);
            task.markDone();
            return "Nice! this task has been marked done:\n  " + task + "\n";
        } catch(NumberFormatException | IndexOutOfBoundsException e) {
            throw new MalformedCommandException(
                "Please provide a valid integer index for the task you want to mark as done like so: " +
                    "done [taskIndex in integer form]");
        }
    }

    public String list() {
        String taskStringRepresentation = "";
        for(int i = 0; i < numTasks(); i++) {
           taskStringRepresentation += (i+1) + ". " + tasks.get(i) + "\n";
       }
       return "Here are the tasks in your list:\n" + taskStringRepresentation;
    }

    private int numTasks() {
        return tasks.size();
    }
}
