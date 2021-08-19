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
    public void handle(String command) throws DukeException {
        String[] arr = command.split(" ");
        String firstWord = arr[0];

        switch (firstWord) {
            case "done":
                if (arr.length < 2) {
                    throw new MissingTaskNumberException("Missing task number");
                }
                int doneTaskNumber = Integer.parseInt(arr[1]);
                try {
                    taskArrayList.get(doneTaskNumber - 1).setDone(true);
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidTaskNumberException("Task does not exist");
                }
                System.out.printf("Solid work! I've marked task %d as done.%n", doneTaskNumber);
                list();
                break;
            case "todo":
                if (arr.length < 2) {
                    throw new MissingTaskNameException("Missing task name");
                }
                String remaining = command.substring(5);
                add(new ToDo(remaining));
                System.out.println("Great! I've added your todo. Enter 'list' to see your tasks!");
                System.out.printf("You currently have %d tasks.%n", taskArrayList.size());
                break;
            case "deadline":
                if (arr.length < 2) {
                    throw new MissingTaskNameException("Missing task name");
                }
                int byIndex = command.indexOf("/by");
                if (byIndex == -1) {
                    throw new MissingDeadlineException("Missing deadline");
                }
                String deadlineName = command.substring(9, byIndex - 1);
                String deadlineBy = command.substring(byIndex + 4);
                add(new Deadline(deadlineName, deadlineBy));
                System.out.println("Great! I've added your deadline. Enter 'list' to see your tasks!");
                System.out.printf("You currently have %d tasks.%n", taskArrayList.size());
                break;
            case "event":
                if (arr.length < 2) {
                    throw new MissingTaskNameException("Missing task name");
                }
                int atIndex = command.indexOf("/at");
                if (atIndex == -1) {
                    throw new MissingEventTimeException("Missing event time");
                }
                String eventName = command.substring(6, atIndex - 1);
                String eventAt = command.substring(atIndex + 4);
                add(new Event(eventName, eventAt));
                System.out.println("Great! I've added your event. Enter 'list' to see your tasks!");
                System.out.printf("You currently have %d tasks.%n", taskArrayList.size());
                break;
            case "delete":
                if (arr.length < 2) {
                    throw new MissingTaskNumberException("Missing task number");
                }
                int deleteTaskNumber = Integer.parseInt(arr[1]);
                try {
                    taskArrayList.remove(deleteTaskNumber - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidTaskNumberException("Task does not exist");
                }
                System.out.printf("Got it! I've removed task %d.%n", deleteTaskNumber);
                System.out.printf("You currently have %d tasks.%n", taskArrayList.size());
                list();
                break;
            default:
                throw new InvalidCommandException("Invalid command");
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
