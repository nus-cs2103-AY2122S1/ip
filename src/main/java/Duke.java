import java.util.Scanner;

public class Duke {
    static String DEFAULT_ERROR_MSG = "im sorry I is no understand.";
    static String EXIT_MSG = "okay is bye!!";
    static String NO_TASKS_MSG = "is no tasks today.";
    static String TOO_MANY_TASKS_MSG = "memory is full please is try later.";
    static String NOT_ENOUGH_TASKS_MSG = "we is dont have that many tasks yet.";
    static String INVALID_TASK_NUMBER_MSG = "what kind of number is (||❛︵❛.)";
    static String TASK_DONE_MSG = "is done!";

    static Scanner sc = new Scanner(System.in);
    static boolean running = true;

    static Task[] tasks = new Task[100];
    static int taskIndex = 0;

    private static void listTasks() {
        if (taskIndex == 0) {
            System.out.println(NO_TASKS_MSG);
        } else {
            for (int i = 1; i <= taskIndex; i++) {
                Task currTask = tasks[i - 1];
                System.out.println(i + "." + currTask.toString() + ".");
            }
        }
    }

    private static void addTask(Task newTask) {
        if (taskIndex >= 100) {
            System.out.println(TOO_MANY_TASKS_MSG);
        } else {
            tasks[taskIndex] = newTask;
            taskIndex++;
            System.out.println("is added.\n" + newTask.toString());
            System.out.println("now is have " + taskIndex + " tasks.");
        }
    }

    private static void completeTask(String input) {
        try {
            int i = Integer.parseInt(input.substring(5));
            if (i > taskIndex) {
                System.out.println(NOT_ENOUGH_TASKS_MSG);
            } else if (i <= 0) {
                System.out.println(INVALID_TASK_NUMBER_MSG);
            } else {
                Task task = tasks[i - 1];
                task.markAsDone();
                System.out.println(TASK_DONE_MSG);
                System.out.println(task.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println(DEFAULT_ERROR_MSG);
        }
    }

    private static void exit() {
        System.out.println(EXIT_MSG);
        running = false;
    }

    private static void parseInput(String input) {
        if (input.equals("bye")) {
            exit();
        } else if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("done ")) {
            completeTask(input);
        } else if (input.startsWith("todo ")) {
            addTask(new ToDo(input.substring(5)));
        } else if (input.startsWith("deadline ")) {
            String description = input.substring(9);
            int i = description.indexOf(" /by ");
            if (i < 0) {
                addTask(new Deadline(description));
            } else {
                addTask(new Deadline(description.substring(0, i), description.substring(i + 5)));
            }
        } else if (input.startsWith("event ")) {
            String description = input.substring(6);
            int i = description.indexOf(" /at ");
            if (i < 0) {
                addTask(new Event(description));
            } else {
                addTask(new Event(description.substring(0, i), description.substring(i + 5)));
            }
        } else {
            System.out.println(DEFAULT_ERROR_MSG);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("hello name is duke");
        System.out.println("how is help today; （´・｀ ）♡");

        while (running) {
            parseInput(sc.nextLine());
        }
    }
}
