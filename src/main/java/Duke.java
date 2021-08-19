import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private enum Command {
        TODO("todo"),
        EVENT("event"),
        DEADLINE("deadline"),
        DONE("done"),
        DELETE("delete"),
        LIST("list"),
        BYE("bye");

        private final String name;

        Command(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
        public Boolean equalsInput(String input) {
            return name.equals(input);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello...\nWhat do you want?\n");
        Scanner myScanner = new Scanner(System.in);
        String answer = myScanner.nextLine();

        while (!answer.equals("bye")) {
            String[] parts = answer.split(" ");
            String taskType = parts[0];
            try {
                handleUserCommand(taskType, answer);
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
            answer = myScanner.nextLine();
        }
        myScanner.close();
        System.out.println("Whatever...");
    }

    private static void handleUserCommand(String taskType, String answer) throws DukeException {
        Command command = checkValidTaskType(taskType);
        switch (command) {
            case DONE:
                markTaskAsDone(answer);
                break;
            case DELETE:
                deleteTask(answer);
                break;
            case LIST:
                printTaskList();
                break;
            default:
                addNewTask(command, answer);
        }
    }

    private static void printTaskList() {
        if (tasks.size() == 0) {
            System.out.println("There are no tasks in the list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void addNewTask(Command command, String answer) throws DukeException {
        String taskDetails = answer.substring(answer.indexOf(" ") + 1);
        if (!answer.contains(" ") || taskDetails.isEmpty()) {
            throw new DukeException("Description of " + command.getName() + " cannot be empty");
        }
        switch (command) {
            case TODO:
                addTodo(taskDetails);
                break;
            case EVENT:
                addEvent(taskDetails);
                break;
            case DEADLINE:
                addDeadline(taskDetails);
                break;
            default:
                throw new DukeException("-_-+ Invalid command.");
        }
        System.out.println("Got it. I've added this task:\n\t" + tasks.get(tasks.size() - 1));
        printTasksCount();
    }

    private static void addTodo(String taskDetails) {
        tasks.add(new Todo(taskDetails));
    }

    private static void addEvent(String taskDetails) throws DukeException {
        String[] parts = taskDetails.split(" /at ");
        if (parts.length < 2) {
            throw new DukeException("Event descriptions must contain /at [time]");
        }
        String description = parts[0];
        String at = parts[1];
        tasks.add(new Event(description, at));
    }

    private static void addDeadline(String taskDetails) throws DukeException {
        String[] parts = taskDetails.split(" /by ");
        if (parts.length < 2) {
            throw new DukeException("Deadline descriptions must contain /by [time]");
        }
        String description = parts[0];
        String by = parts[1];
        tasks.add(new Deadline(description, by));
    }

    private static void markTaskAsDone(String answer) throws DukeException {
        int taskIndex = getValidTaskIndex(answer);
        tasks.get(taskIndex).markAsDone();
        System.out.println("I've marked this task as done:");
        System.out.println("\t" + tasks.get(taskIndex));
    }

    private static void deleteTask(String answer) throws DukeException {
        int taskIndex = getValidTaskIndex(answer);
        Task deletedTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        System.out.println("Noted. I've removed this task:\n\t" + deletedTask);
        printTasksCount();
    }

    private static int getValidTaskIndex(String answer) throws DukeException {
        String taskNo = answer.substring(answer.indexOf(" ") + 1);
        try {
            int taskIndex = Integer.parseInt(taskNo) - 1;
            int taskCount = tasks.size();
            if (taskCount == 0) {
                throw new DukeException("There are no tasks in the list.");
            } else if (taskIndex < 0 || taskIndex >= taskCount) {
                throw new DukeException("Invalid task number. Task number should be between 1 and " + taskCount
                                        + " inclusive.");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number. Sample input with correct format: [command] [taskNo]"
                                    + " eg. 'done 2'");
        }
    }

    private static Command checkValidTaskType(String taskType) throws DukeException {
        for (Command c : Command.values()) {
            if (c.equalsInput(taskType)) return c;
        }
        throw new DukeException("Unknown command...");
    }

    private static void printTasksCount() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
