import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private final List<Task> tasks;
    public TaskList(Scanner sc) {
        tasks = new ArrayList<>();
        loadTasks(sc);
    }

    public void loadTasks(Scanner sc) {
        if(sc != null){
            while(sc.hasNext()) {
                String taskString = sc.nextLine();
                String[] splitTaskString = taskString.split(Task.STORAGE_DELIMITER);
                Task task = null;

                if(splitTaskString[0].equals("T")) {
                    task = new Todo(splitTaskString[1], Boolean.valueOf(splitTaskString[2]));
                } else if(splitTaskString[0].equals("E")) {
                    task = new Event(splitTaskString[1], Boolean.valueOf(splitTaskString[2]), splitTaskString[3]);
                } else if(splitTaskString[0].equals("D")) {
                    task = new Deadline(splitTaskString[1], Boolean.valueOf(splitTaskString[2]), splitTaskString[3]);
                }

                if(task != null) {
                    tasks.add(task);
                }
            }
        }
    }

    public String add(Task task) {
        tasks.add(task);
        return "Got it. I've added this task:\n  " + task + '\n' +
            "You have " + numTasks() + " tasks in the list\n";
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

    public String save() {
        String taskStorageRepresentation = "";
        for(int i = 0; i < numTasks(); i++) {
            taskStorageRepresentation += tasks.get(i).formatForStorage() + "\n";
        }
        return taskStorageRepresentation;
    }

    public String delete(String userInput) throws MalformedCommandException {
        try {
            String userProvidedIndex = userInput.split(" ", 2)[1];
            int taskIndex = Integer.valueOf(userProvidedIndex) - 1;
            Task task = tasks.remove(taskIndex);
            return "Noted. I've removed this task:\n " + task + '\n' +
                "You have " + numTasks() + " tasks in the list\n";
        } catch(NumberFormatException | IndexOutOfBoundsException e) {
            throw new MalformedCommandException(
                "Please provide a valid integer index for the task you want to delete: " +
                    "delete [taskIndex in integer form]");
        }
    }

    private int numTasks() {
        return tasks.size();
    }
}
