import java.time.LocalDate;

/**
 * A class for making sense of the user command
 * Bridges communication between the user and the tasks
 */
public class Parser {
    /**
     * Handles user input regarding tasks
     * @param command String that users enter
     */
    public static void handle(String command, TaskList tasklist) throws DukeException {
        String[] arr = command.split(" ");
        String firstWord = arr[0];

        switch (firstWord) {
            case "done":
                if (arr.length < 2) {
                    throw new MissingTaskNumberException("Missing task number");
                }
                int doneTaskNumber = Integer.parseInt(arr[1]);
                try {
                    tasklist.get(doneTaskNumber).setDone(true);
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidTaskNumberException("Task does not exist");
                }
                System.out.printf("Solid work! I've marked task %d as done.%n", doneTaskNumber);
                tasklist.list();
                break;
            case "todo":
                if (arr.length < 2) {
                    throw new MissingTaskNameException("Missing task name");
                }
                String remaining = command.substring(5);
                tasklist.add(new ToDo(remaining));
                System.out.println("Great! I've added your todo. Enter 'list' to see your tasks!");
                System.out.printf("You currently have %d tasks.%n", tasklist.size());
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
                String deadlineByString = command.substring(byIndex + 4);
                LocalDate deadlineBy = LocalDate.parse(deadlineByString);
                tasklist.add(new Deadline(deadlineName, deadlineBy));
                System.out.println("Great! I've added your deadline. Enter 'list' to see your tasks!");
                System.out.printf("You currently have %d tasks.%n", tasklist.size());
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
                String eventAtString = command.substring(atIndex + 4);
                LocalDate eventAt = LocalDate.parse(eventAtString);
                tasklist.add(new Event(eventName, eventAt));
                System.out.println("Great! I've added your event. Enter 'list' to see your tasks!");
                System.out.printf("You currently have %d tasks.%n", tasklist.size());
                break;
            case "delete":
                if (arr.length < 2) {
                    throw new MissingTaskNumberException("Missing task number");
                }
                int deleteTaskNumber = Integer.parseInt(arr[1]);
                try {
                    tasklist.remove(deleteTaskNumber);
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidTaskNumberException("Task does not exist");
                }
                System.out.printf("Got it! I've removed task %d.%n", deleteTaskNumber);
                System.out.printf("You currently have %d tasks.%n", tasklist.size());
                tasklist.list();
                break;
            default:
                throw new InvalidCommandException("Invalid command");
        }
    }
}
