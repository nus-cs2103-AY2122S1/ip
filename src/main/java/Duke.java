import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String divider = "──────────────────────────────────────────────────────────\n";
    private static final String doubleDivider = "══════════════════════════════════════════════════════════\n";

    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void greetUser() {
        System.out.println(doubleDivider + "Welcome to Duke!\n" + doubleDivider);
        System.out.println("Please enter the tasks (todo/event/deadline) to be added to the list.\n" +
                "(Enter 'list' to view the list, or 'bye' to exit.)\n" + divider);
    }

    public static void byeUser() {
        System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
    }

    public static void printList() {
        System.out.println(divider + "Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            System.out.println(i + ". " + task);
        }
        System.out.print(divider);
    }

    public static void addTodo(String taskDesc) {
        Todo todo = new Todo(taskDesc);
        taskList.add(todo);
        System.out.println(divider + "Got it. I have added this task:\n  " + todo +
                "\n Now you have " + taskList.size() + " tasks in the list.\n" + divider);
    }

    public static void addDeadline(String taskDesc) {
        String[] deadlineParams = taskDesc.split(" /by ");
        Deadline deadline = new Deadline(deadlineParams[0], deadlineParams[1]);
        taskList.add(deadline);
        System.out.println(divider + "Got it. I have added this task:\n  " + deadline +
                "\n Now you have " + taskList.size() + " tasks in the list.\n" + divider);
    }

    public static void addEvent(String taskDesc) {
        String[] eventParams = taskDesc.split(" /at ");
        Event event = new Event(eventParams[0], eventParams[1]);
        taskList.add(event);
        System.out.println(divider + "Got it. I have added this task:\n  " + event +
                "\n Now you have " + taskList.size() + " tasks in the list.\n" + divider);
    }


    public static void markTaskAsDone(String taskNum) {
        int taskIdx = Integer.valueOf(taskNum) - 1;
        Task task = taskList.get(taskIdx);
        task.markAsDone();
        System.out.print(divider + "Great! I've marked this task as done:\n" + task + "\n" + divider);
    }

    public static void getInputs() {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                byeUser();
                break;
            } else if (input.equals("list")) {
                printList();
            } else {
                String taskType = input.substring(0, input.indexOf(' '));
                String taskDesc = input.substring(input.indexOf(' ') + 1);
                if (taskType.equalsIgnoreCase("todo")) {
                    addTodo(taskDesc);
                } else if (taskType.equalsIgnoreCase("deadline")) {
                    addDeadline(taskDesc);
                } else if (taskType.equalsIgnoreCase("event")) {
                    addEvent(taskDesc);
                } else if (taskType.equalsIgnoreCase("done")) {
                    markTaskAsDone(taskDesc);
                }
            }
        }
    }

    public static void main(String[] args) {
        greetUser();
        getInputs();
    }
}
