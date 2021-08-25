import java.io.*;
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

    public static void markTaskAsDone(String taskNum) throws DukeException {
        try {
            int taskIdx = Integer.valueOf(taskNum) - 1;
            if (taskIdx >= taskList.size()) {
                throw new DukeException("Invalid task number entered.");
            }
            Task task = taskList.get(taskIdx);
            task.markAsDone();
            System.out.print(DIVIDER + "Great! I've marked this task as done:\n" + task + "\n" + DIVIDER);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Command. 'done' must be followed by the task number");
        }
    }

    public static void deleteTask(String taskNum) throws DukeException {
        try {
            int taskIdx = Integer.valueOf(taskNum) - 1;
            if (taskIdx >= taskList.size()) {
                throw new DukeException("Invalid task number entered.");
            }
            Task task = taskList.get(taskIdx);
            taskList.remove(taskIdx);
            System.out.println(DIVIDER + "Noted. I have removed this task:\n  " + task +
                    "\n Now you have " + taskList.size() + " tasks in the list.\n" + DIVIDER);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Command. 'delete' must be followed by the task number");
        }
    }

    public static void readFromFile(String filename) {
        try {
            Scanner sc = new Scanner(new FileReader(filename));
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] parts = input.split(" ", 3);
                Task task;

                switch (parts[0]) {
                case "[T]":
                    task = createTask("todo", parts[2]);
                    taskList.add(task);
                    if (parts[1].equals("[X]")) {
                        task.markAsDone();
                    }
                    break;
                case "[D]":
                    task = createTask("deadline", parts[2]);
                    taskList.add(task);
                    if (parts[1].equals("[X]")) {
                        task.markAsDone();
                    }
                    break;
                case "[E]":
                    task = createTask("event", parts[2]);
                    taskList.add(task);
                    if (parts[1].equals("[X]")) {
                        task.markAsDone();
                    }
                    break;
                }

            }
        } catch (Exception e) {
            File file = new File(filename);
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                System.out.println("Error: " + ioException);
            }
        }
    }

    public static void writeToFile() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task : taskList) {
                fw.write(task + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
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
                    case "delete":
                        deleteTask(taskDesc);
                        break;
                    default:
                        addTask(taskType, taskDesc);
                        break;
                }
            } catch (Exception e) {
                System.out.print(DIVIDER + e.getMessage() + "\n" + DIVIDER);
            }
            writeToFile();
        }
    }

    public static void main(String[] args) {
        greetUser();
        readFromFile("data/duke.txt");
        getInputs();
    }
}
