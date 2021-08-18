import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final int MAX_TASKS = 100;
    private static List<Task> taskList = new ArrayList<>();

    private static String addTask(String taskName) {
        if (taskList.size() < 100) {
            taskList.add(new Task(taskName));
            return "added: " + taskName;
        } else {
            return "Sorry! You have max number of tasks stored already.";
        }
    }

    private static String printTaskList() {
        int listLen = taskList.size();
        String msg = "Here are the tasks in your list:";
        for (int i = 0; i < listLen; i++) {
            int currTaskNum = i + 1;
            msg = msg + "\n" + currTaskNum + ". " + taskList.get(i).toString();
        }
        return msg;
    }

    private static String markTaskDone(int taskNum) {
        Task taskToMark = taskList.get(taskNum);
        taskToMark.markDone();
        return "Nice! I've marked this task as done: " + "\n  " + taskToMark.toString();
    }

    private static String addTodo(String taskName) {
        int taskListLen = taskList.size();
        if (taskListLen < 100) {
            ToDo newTodo = new ToDo(taskName);
            taskList.add(newTodo);
            taskListLen += 1;
            return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                    newTodo.toString(), taskListLen);
        } else {
            return "Sorry! You have max number of tasks stored already.";
        }
    }

    private static String addDeadline(String taskName, String byWhen) {
        int taskListLen = taskList.size();
        if (taskListLen < 100) {
            Deadline newDeadline = new Deadline(taskName, byWhen);
            taskList.add(newDeadline);
            taskListLen += 1;
            return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                    newDeadline.toString(), taskListLen);
        } else {
            return "Sorry! You have max number of tasks stored already.";
        }
    }

    private static String addEvent(String taskName, String when) {
        int taskListLen = taskList.size();
        if (taskListLen < 100) {
            Event newEvent = new Event(taskName, when);
            taskList.add(newEvent);
            taskListLen += 1;
            return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                    newEvent.toString(), taskListLen);
        } else {
            return "Sorry! You have max number of tasks stored already.";
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        System.out.println("Duke is gone. Hello, this is Duchess.\nHow can I help you?");

        while (true) {
            try {
                String user_input = sc.nextLine().trim();
                String[] input_split = user_input.split(" ", 2);
                String command = input_split[0];
                if (command.equals("bye")) {
                    System.out.println("It has been a pleasure, goodbye!");
                    sc.close();
                    break;
                } else if (command.equals("list")) {
                    System.out.println(printTaskList());
                } else if (command.equals("todo")) {
                    if (input_split.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String taskName = input_split[1];
                    System.out.println(addTodo(taskName));
                } else if (command.equals("deadline")) {
                    if (input_split.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String msg = input_split[1];
                    String[] msg_split = msg.split(" /by ", 2);
                    if (msg_split.length < 2) {
                        throw new DukeException("☹ OOPS!!! Please indicate when the task needs to be done by.");
                    }
                    System.out.println(addDeadline(msg_split[0], msg_split[1]));
                } else if (command.equals("event")) {
                    if (input_split.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String msg = input_split[1];
                    String[] msg_split = msg.split(" /at ", 2);
                    if (msg_split.length < 2) {
                        throw new DukeException("☹ OOPS!!! Please indicate the start and end time of the event.");
                    }
                    System.out.println(addEvent(msg_split[0], msg_split[1]));
                } else if (command.equals("done")) {
                    if (input_split.length < 2) {
                        throw new DukeException("☹ OOPS!!! Please indicate which task you want to complete.");
                    }
                    String strTaskNum = input_split[1].split(" ")[0];
                    int taskNum = Integer.parseInt(strTaskNum) - 1;
                    System.out.println(markTaskDone(taskNum));
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
