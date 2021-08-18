import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "───────────────────────────────────────────────────────────────────────\n";
    private static final String DOUBLE_DIVIDER = "═══════════════════════════════════════" +
            "════════════════════════════════\n";

    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void greetUser() {
        System.out.println(DOUBLE_DIVIDER + "Welcome to Duke!\n" + DOUBLE_DIVIDER);
        System.out.println("Please enter the tasks (todo/event/deadline) to be added to the list.\n" +
                "(Enter 'list' to view the list, or 'bye' to exit.)\n" + DIVIDER);
    }

    public static void byeUser() {
        System.out.println(DIVIDER + "Bye. Hope to see you again soon!\n" + DIVIDER);
    }

    public static void printList() {
        System.out.println(DIVIDER + "Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            System.out.println(i + ". " + task);
        }
        System.out.print(DIVIDER);
    }

    public static Task createTask(String taskType, String taskDesc) throws DukeException {
        switch (taskType) {
            case "todo":
                if (taskDesc.isEmpty()) {
                    throw new DukeException("Invalid Command. The description of a " + taskType + " cannot be empty.");
                }
                return new Todo(taskDesc);
            case "event":
                if (taskDesc.isEmpty()) {
                    throw new DukeException("Invalid Command. The description of a " + taskType + " cannot be empty.");
                } else if (!taskDesc.contains(" /at ")) {
                    throw new DukeException("Invalid Command. The description of a event must include a '/at'.");
                }
                String[] eventParams = taskDesc.split(" /at ");
                return new Event(eventParams[0], eventParams[1]);
            case "deadline":
                if (taskDesc.isEmpty()) {
                    throw new DukeException("Invalid Command. The description of a " + taskType + " cannot be empty.");
                } else if (!taskDesc.contains(" /by ")) {
                    throw new DukeException("Invalid Command. The description of a deadline must include a '/by'.");
                }
                String[] deadlineParams = taskDesc.split(" /by ");
                return new Deadline(deadlineParams[0], deadlineParams[1]);
            default:
                throw new DukeException("Invalid Command. Are you sure you have typed in the command properly?");
        }
    }

    public static void addTask(String taskType, String taskDesc) throws DukeException {
        Task task = createTask(taskType, taskDesc);
        taskList.add(task);
        System.out.println(DIVIDER + "Got it. I have added this task:\n  " + task +
                "\n Now you have " + taskList.size() + " tasks in the list.\n" + DIVIDER);
    }


    public static void markTaskAsDone(String taskNum) {
        int taskIdx = Integer.valueOf(taskNum) - 1;
        Task task = taskList.get(taskIdx);
        task.markAsDone();
        System.out.print(DIVIDER + "Great! I've marked this task as done:\n" + task + "\n" + DIVIDER);
    }

    public static void getInputs() {
        while (true) {
            try {
                String input = scanner.nextLine();
                String taskType, taskDesc;

                if (input.contains(" ")) {
                    taskType = input.substring(0, input.indexOf(' '));
                    taskDesc = input.substring(input.indexOf(' ') + 1);
                } else {
                    taskType = input;
                    taskDesc = "";
                }

                switch (taskType) {
                    case "bye":
                        byeUser();
                        return;
                    case "list":
                        printList();
                        break;
                    case "done":
                        markTaskAsDone(taskDesc);
                        break;
                    default:
                        addTask(taskType, taskDesc);
                        break;
                }
            } catch (Exception e) {
                System.out.print(DIVIDER + e.getMessage() + "\n" + DIVIDER);
            }
        }
    }

    public static void main(String[] args) {
        greetUser();
        getInputs();
    }
}
