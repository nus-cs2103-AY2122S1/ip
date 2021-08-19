import java.util.ArrayList;

/**
 * TaskManager handles the management of and interaction with tasks
 */
public class TaskManager {
    /** ArrayList containing all tasks **/
    ArrayList<Task> taskArrayList = new ArrayList<>();

    /**
     * Handles user input regarding tasks
     * @param command String that users enter
     */
    public void handle(String command) {
        String[] arr = command.split(" ");
        String firstWord = arr[0];
        switch (firstWord) {
            case "done":
                int id = Integer.parseInt(arr[1]);
                taskArrayList.get(id - 1).setDone(true);
                System.out.printf("Solid work! I've marked task %d as done.%n", id);
                list();
                break;
            case "todo":
                String remaining = command.substring(5);
                add(new ToDo(remaining));
                System.out.println("Great! I've added your todo. Enter 'list' to see your tasks!");
                break;
            case "deadline":
                int byIndex = command.indexOf("/by");
                if (byIndex == -1) {
                    System.out.println("I'm afraid I don't recognise that, please try again!");
                } else {
                    String deadlineName = command.substring(9, byIndex - 1);
                    String deadlineBy = command.substring(byIndex + 4);
                    add(new Deadline(deadlineName, deadlineBy));
                    System.out.println("Great! I've added your deadline. Enter 'list' to see your tasks!");
                }
                break;
            case "event":
                int atIndex = command.indexOf("/at");
                if (atIndex == -1) {
                    System.out.println("I'm afraid I don't recognise that, please try again!");
                } else {
                    String eventName = command.substring(6, atIndex - 1);
                    String eventAt = command.substring(atIndex + 4);
                    add(new Event(eventName, eventAt));
                    System.out.println("Great! I've added your event. Enter 'list' to see your tasks!");
                }
                break;
            default:
                System.out.println("I'm afraid I don't recognise that, please try again!");
        }
    }

    /**
     * Adds a new task to taskArrayList
     * @param task Task object to be added
     */
    public void add(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Lists the current Tasks in taskArrayList with numbering
     */
    public void list() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            System.out.println(i+1 + "." + taskArrayList.get(i).toString());
        }
        System.out.println();
    }
}
