import java.util.Scanner;

public class Duke {
    private static String DEFAULT_ERROR_MSG = "im sorry I is no understand.";
    private static String BLANK_DESCRIPTION_ERROR_MSG = "is no leave description blank okay.";
    private static String UNSPECIFIED_TASK_ERROR_MSG = "please is specify task please,";
    private static String EXIT_MSG = "okay is bye!!";
    private static String NO_TASKS_MSG = "is no tasks today.";
    private static String TOO_MANY_TASKS_MSG = "memory is full please is try later.";
    private static String NOT_ENOUGH_TASKS_MSG = "we is dont have that many tasks yet.";
    private static String INVALID_TASK_NUMBER_MSG = "what kind of number is (||❛︵❛.)";
    private static String TASK_DONE_MSG = "is done!";

    private static Scanner sc = new Scanner(System.in);
    private static boolean running = true;

    private static Task[] tasks = new Task[100];
    private static int taskIndex = 0;

    private static void listTasks() throws DukeException {
        if (taskIndex == 0) {
            throw new DukeException(NO_TASKS_MSG);
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

    private static void completeTask(String input) throws DukeException {
        try {
            if (input.length() < 1) {
                throw new DukeException(UNSPECIFIED_TASK_ERROR_MSG);
            } else {
                int i = Integer.parseInt(input.substring(1));
                if (i > taskIndex) {
                    throw new DukeException(NOT_ENOUGH_TASKS_MSG);
                } else if (i <= 0) {
                    throw new DukeException(INVALID_TASK_NUMBER_MSG);
                } else {
                    Task task = tasks[i - 1];
                    task.markAsDone();
                    System.out.println(TASK_DONE_MSG);
                    System.out.println(task.toString());
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException(DEFAULT_ERROR_MSG);
        }
    }

    private static void exit() {
        System.out.println(EXIT_MSG);
        running = false;
    }

    private static void addToDo(String description) throws DukeException {
        if (description.length() <= 1) {
            throw new DukeException(BLANK_DESCRIPTION_ERROR_MSG);
        } else {
            addTask(new ToDo(description.substring(1)));
        }
    }

    private static void addDeadline(String description) throws DukeException {
        if (description.length() <= 1) {
            throw new DukeException(BLANK_DESCRIPTION_ERROR_MSG);
        } else {
            int i = description.indexOf(" /by ");
            if (i < 0) {
                addTask(new Deadline(description));
            } else {
                addTask(new Deadline(description.substring(0, i), description.substring(i + 5)));
            }
        }
    }

    private static void addEvent(String description) throws DukeException {
        if (description.length() <= 1) {
            throw new DukeException(BLANK_DESCRIPTION_ERROR_MSG);
        } else {
            int i = description.indexOf(" /at ");
            if (i < 0) {
                addTask(new Event(description));
            } else {
                addTask(new Event(description.substring(0, i), description.substring(i + 5)));
            }
        }
    }

    private static void parseInput(String input) {
        try {
            if (input.equals("bye")) {
                exit();
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("done")) {
                completeTask(input.substring(4));
            } else if (input.startsWith("todo")) {
                addToDo(input.substring(4));
            } else if (input.startsWith("deadline")) {
                addDeadline(input.substring(8));
            } else if (input.startsWith("event")) {
                addEvent(input.substring(5));
            } else {
                throw new DukeException(DEFAULT_ERROR_MSG);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
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
