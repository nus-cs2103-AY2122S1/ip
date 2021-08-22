import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String logo = " ____        _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String FILE_PATH = System.getProperty("user.dir");
    private static final int MAX_TASKS = 100;
    private static final String BORDER = "____________________________________________________________";

    private static String printTaskList(Storage storage) {
        int taskListLen = storage.taskListLen();
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskListLen; i++) {
            int currTaskNum = i + 1;
            msg.append("\n").append(currTaskNum).append(". ").append(storage.getTask(i).toString());
        }
        return msg.toString();
    }

    private static String markTaskDone(int taskNum, Storage storage) {
        Task taskToMark = storage.getTask(taskNum);
        taskToMark.markDone();
        return "Nice! I've marked this task as done:" + "\n  " + taskToMark.toString();
    }

    private static String addTodo(String taskName, Storage storage) {
        int taskListLen = storage.taskListLen();
        if (taskListLen < MAX_TASKS) {
            Todo newTodo = new Todo(taskName);
<<<<<<< HEAD
            storage.addTask(newTodo);
=======
>>>>>>> branch-Level-8
            taskListLen += 1;
            return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                    newTodo.toString(), taskListLen);
        } else {
            return "Sorry! You have max number of tasks stored already.";
        }
    }

    private static String addDeadline(String taskName, String byWhen, Storage storage) {
        int taskListLen = storage.taskListLen();
        if (taskListLen < 100) {
            Deadline newDeadline = new Deadline(taskName, byWhen);
            storage.addTask(newDeadline);
            taskListLen += 1;
            return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                    newDeadline.toString(), taskListLen);
        } else {
            return "Sorry! You have max number of tasks stored already.";
        }
    }

    private static String addEvent(String taskName, String when, Storage storage) {
        int taskListLen = storage.taskListLen();
        if (taskListLen < 100) {
            Event newEvent = new Event(taskName, when);
            storage.addTask(newEvent);
            taskListLen += 1;
            return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                    newEvent.toString(), taskListLen);
        } else {
            return "Sorry! You have max number of tasks stored already.";
        }
    }

    private static String deleteTask(int taskNum, Storage storage) {
        int taskListLen = storage.taskListLen();
        if (taskNum + 1 <= taskListLen) {
            Task removedTask = storage.deleteTask(taskNum);
            taskListLen -= 1;
            return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                    removedTask.toString(), taskListLen);
        } else {
            return "There is no task at the specified index.";
        }
    }

    private static void prettyPrint(String s) {
        String indentedMsg = s.replaceAll("(?m)^", "    ");
        System.out.printf("%s\n%s\n%s%n", BORDER, indentedMsg, BORDER);
    }


    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        System.out.printf("Duke is gone. Hello, this is Duchess.\nHow can I help you?\n%s%n", BORDER);

        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage(FILE_PATH);

        while (true) {
            try {
                String user_input = sc.nextLine().trim();
                String[] input_split = user_input.split(" ", 2);
                String command = input_split[0];
                if (command.equals("bye")) {
                    prettyPrint("It has been a pleasure, goodbye!");
                    storage.saveToFile();
                    sc.close();
                    break;
                } else if (command.equals("list")) {
                    prettyPrint(printTaskList(storage));
                } else if (command.equals("todo")) {
                    if (input_split.length < 2) {
                        throw DukeException.missingInput("todo");
                    }
                    String taskName = input_split[1];
                    prettyPrint(addTodo(taskName, storage));
                } else if (command.equals("deadline")) {
                    if (input_split.length < 2) {
                        throw DukeException.missingInput("deadline");
                    }
                    String msg = input_split[1];
                    String[] msg_split = msg.split(" /by ", 2);
                    if (msg_split.length < 2) {
                        throw new DukeException("☹ OOPS!!! Please indicate when the task needs to be done by.");
                    }
                    prettyPrint(addDeadline(msg_split[0], msg_split[1], storage));
                } else if (command.equals("event")) {
                    if (input_split.length < 2) {
                        throw DukeException.missingInput("event");
                    }
                    String msg = input_split[1];
                    String[] msg_split = msg.split(" /at ", 2);
                    if (msg_split.length < 2) {
                        throw new DukeException("☹ OOPS!!! Please indicate the start and end time of the event.");
                    }
                    prettyPrint(addEvent(msg_split[0], msg_split[1], storage));
                } else if (command.equals("done")) {
                    if (input_split.length < 2) {
                        throw new DukeException("☹ OOPS!!! Please indicate which task you want to complete.");
                    }
                    String strTaskNum = input_split[1].split(" ")[0];
                    int taskNum = Integer.parseInt(strTaskNum) - 1;
                    prettyPrint(markTaskDone(taskNum, storage));
                } else if (command.equals("delete")) {
                    if (input_split.length < 2) {
                        throw new DukeException("☹ OOPS!!! Please indicate which task you want to delete.");
                    }
                    String strTaskNum = input_split[1].split(" ")[0];
                    int taskNum = Integer.parseInt(strTaskNum) - 1;
                    prettyPrint(deleteTask(taskNum, storage));
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.err.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Please enter a valid index!");
            }
        }
    }
}
