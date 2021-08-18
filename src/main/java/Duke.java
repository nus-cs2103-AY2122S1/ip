import java.util.Scanner;

public class Duke {
    protected static final String HORIZONTAL_LINE = "  -----------------------";
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
            System.out.println(INDENTATION + "Now you have " + size + " tasks in the list.");
        } else {
            System.out.println(INDENTATION + "Now you have " + size + " task in the list.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showRemoveTaskMessage(Task task, int size) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Got it. I've removed this task:");
        System.out.println(INDENTATION + task.toString());
        if (size > 1) {
            System.out.println(INDENTATION + "Now you have " + size + " tasks in the list.");
        } else {
            System.out.println(INDENTATION + "Now you have " + size + " task in the list.");
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
            String rest = input.substring(firstWordIndex + 1);
            try {
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
                        try {
                            int taskNumber = Integer.parseInt(rest);
                            taskManagement.markTaskAsDone(taskNumber - 1);
                            break;
                        } catch (NumberFormatException e) {
                            throw new DukeException("A number must be given to specified the task.");
                        }
                    }
                    case "todo": {
                        if (firstWordIndex == -1) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        Todo temp = new Todo(rest);
                        taskManagement.addTask(temp);
                        showAddTaskMessage(temp, taskManagement.getSize());
                        break;
                    }
                    case "deadline": {
                        if (firstWordIndex == -1) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        String[] arr = rest.split("/by ");
                        if (arr.length == 1) {
                            throw new DukeException("/by is needed to specified the time.");
                        }
                        String description = arr[0];
                        String detail = arr[1];
                        Deadline temp = new Deadline(description, detail);
                        taskManagement.addTask(temp);
                        showAddTaskMessage(temp, taskManagement.getSize());
                        break;
                    }
                    case "event": {
                        if (firstWordIndex == -1) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                        String[] arr = rest.split("/at ");
                        if (arr.length == 1) {
                            throw new DukeException("/at is needed to specified the time.");
                        }
                        String description = arr[0];
                        String detail = arr[1];
                        Event temp = new Event(description, detail);
                        taskManagement.addTask(temp);
                        showAddTaskMessage(temp, taskManagement.getSize());
                        break;
                    }
                    case "delete": {
                        try {
                            int taskNumber = Integer.parseInt(rest);
                            Task temp = taskManagement.removeTask(taskNumber - 1);
                            showRemoveTaskMessage(temp, taskManagement.getSize());
                            break;
                        } catch (NumberFormatException e) {
                            throw new DukeException("A number must be given to specified the task.");
                        }
                    }
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                showMessage(e.getMessage());
            }
        }
        sc.close();
    }
}
