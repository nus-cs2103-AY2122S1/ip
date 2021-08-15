import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final static ArrayList<Task> list = new ArrayList<>(100);

    private enum TaskType {
        TASK,
        TODO,
        DEADLINE,
        EVENT
    }

    private static void chat(String content) {
        System.out.println(
                "____________________________________________________________\n"
                + content
                + "\n____________________________________________________________\n"
        );
    }

    private static void displayList() {
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String line = i + 1 + "." + task.toString();
            listString.append(line);
            if (i != list.size() - 1) {
                listString.append("\n");
            }
        }
        chat(listString.toString());
    }

    private static void addTask(String input, TaskType type) {
        int descriptionEnd;
        String description;
        String when;
        Task task;
        switch (type) {
            case TASK:
                task = new Task(input);
                break;
            case TODO:
                task = new ToDo(input.substring(5));
                break;
            case DEADLINE:
                descriptionEnd = input.indexOf(" /by ");
                description = input.substring(9, descriptionEnd);
                when = input.substring(descriptionEnd + 5);
                task = new Deadline(description, when);
                break;
            case EVENT:
                descriptionEnd = input.indexOf(" /at ");
                description = input.substring(6, descriptionEnd);
                when = input.substring(descriptionEnd + 5);
                task = new Event(description, when);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        list.add(task);
        chat("Got it. I've added this task:\n "
                + task.toString() + "\n"
                + "Now you have " + list.size() + " tasks in the list"
        );
    }

    private static void markDone(String input) {
        Task task = list.get(Integer.parseInt(input.substring(5)) - 1);
        task.markDone();
        chat("Nice! I've marked this task as done: \n"
                + "  "
                + task.toString());
    }

    private static void runDuke() {
        chat("Hello I'm Duke\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                displayList();
            } else if (input.startsWith("done ")) {
                markDone(input);
            } else if (input.startsWith("todo ")) {
                addTask(input, TaskType.TODO);
            } else if (input.startsWith("deadline ")) {
                addTask(input, TaskType.DEADLINE);
            } else if (input.startsWith("event ")) {
                addTask(input, TaskType.EVENT);
            } else {
                addTask(input, TaskType.TASK);
            }
        }
        chat("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        runDuke();
    }


}
