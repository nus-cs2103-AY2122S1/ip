import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    private static final String SEPARATOR = "__________________________________________\n";
    private static final String GREETING = "Hello! I'm Talky McTalkFace\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";
    private static final String PROMPT = "What would you like to do?";
    private static final String TASK_DONE = "Nice! I've marked this task as done:\n";
    private static final String PRINT_LIST = "Here are the tasks in your list:";
    private static final String ADD_TASK_PREFIX = "Got it. I've added this task:\n";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done ";
    private static final String TODO_COMMAND = "todo ";
    private static final String DEADLINE_COMMAND = "deadline ";
    private static final String EVENT_COMMAND = "event ";
    private static final String DELETE_COMMAND = "delete ";

    public static void prompt() {
        String input = "";

        while (!input.equals(EXIT_COMMAND)) {
            System.out.println(PROMPT);
            input = sc.nextLine();

            try {
                if (input.equals(EXIT_COMMAND)) {
                    exit();
                } else if (input.equals(LIST_COMMAND)) {
                    printList(taskList);
                } else if (input.startsWith(DONE_COMMAND)) {
                    String taskNumber = input.substring(5);
                    taskDone(taskNumber);
                } else if (input.startsWith(TODO_COMMAND) || input.startsWith(DEADLINE_COMMAND) || input.startsWith(EVENT_COMMAND)) {
                    switch (input) {
                    case "deadline ":
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    case "event ":
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    case "todo ":
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    addTask(input);
                } else if (input.startsWith(DELETE_COMMAND)) {
                    deleteTask(input);
                } else if (input.startsWith("occurring on")) {
                    findTasksOnDate(LocalDate.parse(input.substring(13)));
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void exit() {
        System.out.print(SEPARATOR + EXIT + SEPARATOR);
    }

    public static void printList(List<Task> taskList) {
        System.out.print(SEPARATOR);
        System.out.println(PRINT_LIST);
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i - 1).toString());
        }
        System.out.print(SEPARATOR);
    }

    public static void taskDone(String taskNumber) {
        String invalid = "OOPS!!! Please enter a valid task number.";

        try {
            int taskNo = Integer.parseInt(taskNumber);
            Task task = taskList.get(taskNo - 1);
            task.setDone();
            System.out.print(SEPARATOR + TASK_DONE + task.toString() + "\n" + SEPARATOR);
        } catch (Exception e) {
            System.out.println(invalid);
            prompt();
        }
    }

    public static void addTaskSuffix(Task task) {
        int taskNo = taskList.size();
        String t = taskNo == 1 ? " task " : " tasks ";
        System.out.print(SEPARATOR
                + ADD_TASK_PREFIX
                + task.toString()
                + "\n"
                + "Now you have "
                + taskNo
                + t
                + "in the list.\n"
                + SEPARATOR);
    }

    public static void addTask(String input) throws DukeException {
        Task newTask;

        if (input.startsWith(TODO_COMMAND)) {
            newTask = new Todo(input);
        } else if (input.startsWith(DEADLINE_COMMAND) && input.contains("/by ")) {
            newTask = new Deadline(input);
        } else if (input.startsWith(EVENT_COMMAND) && input.contains("/at ")) {
            newTask = new Event(input);
        } else {
            throw new DukeException("OOPS!!! Invalid task description");
        }

        taskList.add(newTask);
        addTaskSuffix(newTask);

    }

    public static void deleteTask(String input) {

        try {
            int taskNum = Integer.parseInt(input.substring(7));
            Task removedTask = taskList.remove(taskNum - 1);
            int tasksLeft = taskList.size();
            String t = tasksLeft == 1 ? " task " : " tasks ";

            System.out.print(SEPARATOR +
                    "Noted. I've removed this task:\n"
                    + removedTask.toString()
                    + "\n"
                    + "Now you have "
                    + tasksLeft
                    + t
                    + "in the list.\n"
                    + SEPARATOR);

        } catch (Exception e) {
            System.out.println("OOPS!!! Please input a valid task number.");
        }
    }

    public static void findTasksOnDate(LocalDate date) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.dueDate.isEqual(date)) {
                    tasks.add(d);
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                if (e.eventDate.isEqual(date)) {
                    tasks.add(e);
                }
            }
        }

        int num = tasks.size();
        String d = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String s = num == 0
                ? "You have 0 tasks occurring on " + d + "."
                : num == 1
                ? "You have 1 task occurring on " + d + ":"
                : "You have " + num + " tasks occurring on " + d + ":";

        System.out.println(s);
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public static void main(String[] args) {
        System.out.print(SEPARATOR + GREETING + SEPARATOR);
        prompt();
    }
}
