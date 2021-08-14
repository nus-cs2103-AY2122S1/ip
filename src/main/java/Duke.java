import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.function.Consumer;

public class Duke implements Runnable {
    private final String BORDER = "---------------------------------------------------";
    private final String GREETING = "Hello! I'm Duke, what can I do for you?";
    private final String FAREWELL = "Bye. Hope to see you again soon!";
    private final String MSG_LISTS = "Here are the tasks in your list:";
    private final String MSG_TASK_COMPLETE = "Nice! I've marked this task as done:\n%s";
    private final String MSG_TASK_ADDED = "Got it. I've added this task:";
    private final String MSG_TASK_DELETED = "Noted. I've removed this task:";
    private final String MSG_TASK_COUNT = "Now you have %d tasks in the list.";
    private final String ERR_OUT_OF_BOUNDS = "Please enter a number between 1 and %d!";
    private final String ERR_TASK_COMPLETE = "Task %s is already complete!";
    private final String ERR_NOT_FOUND = "Sorry, I do not recognize that command.";
    private final String ERR_NO_TASKS = "No tasks available!";
    private final String ERR_TODO_FORMAT = "Error in command usage. Usage: todo <name>";
    private final String ERR_DEADLINE_FORMAT = "Error in command usage. Usage: deadline <name> /by <date>";
    private final String ERR_EVENT_FORMAT = "Error in command usage. Usage: event <name> /at <date>";
    private final String ERR_DONE_FORMAT = "Please provide a valid number! Usage: done <num>";
    private final String ERR_DELETE_FORMAT = "Please provide a valid number! Usage: delete <num>";
    private final String ERR_MAX_TASKS = "Sorry! You have reached maximum Task capacity.";
    private final int MAX_TASKS = 100;
    private final Map<String, Consumer<String>> commandsMap = Map.of(
        "list", (args) -> listTasks(),
        "done", (args) -> completeTask(args),
        "todo", (args) -> addTodo(args),
        "deadline", (args) -> addDeadline(args),
        "event", (args) -> addEvent(args),
        "delete", (args) -> deleteTask(args)
    );
    private List<Task> tasks = new ArrayList<>();

    private void printLatestTask() {
        printDuke(
            MSG_TASK_ADDED + "\n"
            + "   " + tasks.get(tasks.size() - 1).toString() + "\n"
            + String.format(MSG_TASK_COUNT, tasks.size())
        );
    }

    private void addTodo(String args) {
        if (tasks.size() > MAX_TASKS) {
            printDuke(ERR_MAX_TASKS);
            return;
        }
        if (args.equals("")) {
            printDuke(ERR_TODO_FORMAT);
            return;
        }
        tasks.add(new Todo(args));
        printLatestTask();
    }

    private void addDeadline(String args) {
        if (args.equals("") || !args.contains(" /by ")) {
            printDuke(ERR_DEADLINE_FORMAT);
            return;
        }
        String[] tmp = args.split(" /by ", 2);
        tasks.add(new Deadline(tmp[0], tmp[1]));
        printLatestTask();
    }

    private void addEvent(String args) {
        if (args.equals("") || !args.contains(" /at ")) {
            printDuke(ERR_EVENT_FORMAT);
            return;
        }
        String[] tmp = args.split(" /at ", 2);
        tasks.add(new Event(tmp[0], tmp[1]));
        printLatestTask();
    }

    private void listTasks() {
        if (tasks.size() == 0) {
            printDuke(ERR_NO_TASKS);
            return;
        }
        printDuke(MSG_LISTS + "\n"
            + String.join("\n", IntStream.range(0, tasks.size())
                .mapToObj(i -> String.format("%d.%s", i + 1, tasks.get(i)))
                .toArray(String[]::new)));
    }

    private void completeTask(String args) {
        try {
            int idx = Integer.parseInt(args);
            if (tasks.size() == 0) {
                printDuke(ERR_NO_TASKS);
                return;
            }
            if (idx < 1 || idx > tasks.size()) {
                printDuke(String.format(ERR_OUT_OF_BOUNDS, tasks.size()));
                return;
            }
            if (tasks.get(idx - 1).isComplete()) {
                printDuke(String.format(ERR_TASK_COMPLETE, tasks.get(idx - 1).getName()));
                return;
            }
            tasks.get(idx - 1).markComplete();
            printDuke(String.format(
                MSG_TASK_COMPLETE,
                String.format("   %s", tasks.get(idx - 1))));
        } catch (NumberFormatException e) {
            printDuke(ERR_DONE_FORMAT);
            return;
        }
    }

    private void deleteTask(String args) {
        try {
            int idx = Integer.parseInt(args);
            if (tasks.size() == 0) {
                printDuke(ERR_NO_TASKS);
                return;
            }
            if (idx < 1 || idx > tasks.size()) {
                printDuke(String.format(ERR_OUT_OF_BOUNDS, tasks.size()));
                return;
            }
            Task deletedTask = tasks.get(idx - 1);
            tasks.remove(idx - 1);
            printDuke(
                MSG_TASK_DELETED + "\n"
                + "   " + deletedTask.toString() + "\n"
                + String.format(MSG_TASK_COUNT, tasks.size())
            );
        } catch (NumberFormatException e) {
            printDuke(ERR_DELETE_FORMAT);
            return;
        }
    }

    private void notFound() {
        printDuke(ERR_NOT_FOUND);
    }

    private void printDuke(String str) {
        System.out.printf(String.format("%s\n%s\n%s\n", BORDER, str, BORDER)
            .replaceAll("(?m)^", "\t"));
    }

    private void execute(String input) {
        String[] tmp =  input.split(" ", 2);
        String command = tmp[0];
        String args = tmp.length > 1 ? tmp[1] : "";
        commandsMap.getOrDefault(command, (str) -> notFound()).accept(args);
    }

    public void run() {
        printDuke(GREETING);

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!(input = sc.nextLine().trim()).equals("bye")) {
            execute(input);
        }

        printDuke(FAREWELL);
        sc.close();
    }

    public static void main(String[] args) {
       new Duke().run();
    }
}
