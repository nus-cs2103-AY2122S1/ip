import java.util.Scanner;

public class Duke {
    protected static final String HORIZONTAL_LINE = "-----------------------";
    protected static final String INDENTATION = "    ";

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Hello, I am Duke.");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showMessage(String s) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + s);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showAddTaskMessage(Task task, int size) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + task.toString());
        if (size > 1) {
            System.out.println(INDENTATION + "Now you have "+ size + " tasks in the list.");
        } else {
            System.out.println(INDENTATION + "Now you have "+ size + " task in the list.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void bye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        TaskManagement taskManagement = new TaskManagement();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            int firstWordIndex = input.indexOf(" ");
            String action = firstWordIndex == -1 ? input : input.substring(0, firstWordIndex);
            String rest = input.substring(firstWordIndex + 1, input.length());
            switch (action) {
                case "bye": {
                    bye();
                    sc.close();
                    return;
                }
                case "list": {
                    taskManagement.showTasks();
                    break;
                }
                case "done": {
                    int taskNumber = Integer.parseInt(rest);
                    taskManagement.markTaskAsDone(taskNumber - 1);
                    break;
                }
                case "todo": {
                    Todo temp = new Todo(rest);
                    taskManagement.addTask(temp);
                    showAddTaskMessage(temp, taskManagement.getSize());
                    break;
                }
                case "deadline": {
                    String[] arr = rest.split("/by ");
                    String description = arr[0];
                    String detail = arr[1];
                    Deadline temp = new Deadline(description, detail);
                    taskManagement.addTask(temp);
                    showAddTaskMessage(temp, taskManagement.getSize());
                    break;
                }
                case "event": {
                    String[] arr = rest.split("/at ");
                    String description = arr[0];
                    String detail = arr[1];
                    Event temp = new Event(description, detail);
                    taskManagement.addTask(temp);
                    showAddTaskMessage(temp, taskManagement.getSize());
                    break;
                }
                default:
                    showMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        sc.close();
        return;
    }
}
