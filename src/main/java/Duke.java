import java.util.Scanner;

public class Duke {
    private static int taskCount = 0;
    private static final int MAX = 100;
    private static Task[] tasks = new Task[MAX];

    public static void main(String[] args) {
        System.out.println("Hello...\nWhat do you want?\n");
        Scanner myScanner = new Scanner(System.in);
        String answer = myScanner.nextLine();

        while (!answer.equals("bye")) {
            String[] parts = answer.split(" ");
            String taskType = parts[0];
            try {
                if (taskType.equals("done")) {
                    String taskNo = answer.substring(answer.indexOf(" ") + 1);
                    markTaskAsDone(taskNo);
                } else if (taskType.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                } else {
                    addNewTask(taskType, answer);
                }
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
            answer = myScanner.nextLine();
        }
        myScanner.close();
        System.out.println("Whatever...");
    }

    private static void addNewTask(String taskType, String answer) throws DukeException {
        String taskDetails = answer.substring(answer.indexOf(" ") + 1);
        if (!answer.contains(" ") || taskDetails.isEmpty()) {
            throw new DukeException("Description of " + taskType + " cannot be empty");
        } else if (taskType.equals("todo")) {
            tasks[taskCount] = new Todo(taskDetails);
        } else if (taskType.equals("event")) {
            String[] parts = taskDetails.split(" /at ");
            if (parts.length < 2) {
                throw new DukeException("Event descriptions must contain /at [time]");
            }
            String description = parts[0];
            String at = parts[1]; 
            tasks[taskCount] = new Event(description, at);
        } else if (taskType.equals("deadline")) {
            String[] parts = taskDetails.split(" /by ");
            if (parts.length < 2) {
                throw new DukeException("Deadline descriptions must contain /by [time]");
            }
            String description = parts[0];
            String by = parts[1];
            tasks[taskCount] = new Deadline(description, by);
        } else {
            throw new DukeException("-_-+ Invalid command.");
        }
        System.out.println("Got it. I've added this task:\n\t" + tasks[taskCount]);
        taskCount += 1;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void markTaskAsDone(String taskNo) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(taskNo) - 1;
            if (taskCount == 0) {
                throw new DukeException("There are no tasks to mark as done.");
            } else if (taskIndex < 0 || taskIndex >= taskCount) {
                throw new DukeException("Invalid task number to mark as done. Task number should be between 1 and "
                                        + taskCount + " inclusive.");
            }
            tasks[taskIndex].markAsDone();
            System.out.println("I've marked this task as done:");
            System.out.println("\t" + tasks[taskIndex]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number to mark as done. Sample input with correct format: 'done 2'");
        }
    }
}
