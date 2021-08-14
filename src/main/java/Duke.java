import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.function.Consumer;

public class Duke implements Runnable {
    private final String BORDER = "-------------------------------------------------";
    private final String GREETING = "Hello! I'm Duke, what can I do for you?";
    private final String FAREWELL = "Bye. Hope to see you again soon!";
    private final String MSG_LISTS = "Here are the tasks in your list:";
    private final String MSG_TASK_COMPLETE = "Nice! I've marked this task as done:\n%s";
    private final String ERR_OUT_OF_BOUNDS = "Please enter a number between 1 and %d!";
    private final String ERR_TASK_COMPLETE = "Task %s is already complete!";
    private final String ERR_NO_TASKS = "No tasks available!";
    private final String ERR_INVALID_NUM = "Please provide a valid number! Usage: done <num>";
    private final String ERR_MAX_TASKS = "Sorry! You have reached maximum Task capacity.";
    private final int MAX_TASKS = 100;
    private final Map<String, Consumer<String>> commandsMap = Map.of(
        "list", (args) -> listTasks(),
        "done", (args) -> completeTask(args)
    );
    private List<Task> tasks = new ArrayList<>();

    private void addTask(String str) {
        if (tasks.size() > MAX_TASKS) {
            printDuke(ERR_MAX_TASKS);
            return;
        }
        tasks.add(new Task(str));
        printDuke(String.format("added: %s", str));
    }

    private void listTasks() {
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
                String.format("   %s", tasks.get(idx - 1))
            ));
        } catch (NumberFormatException e) {
            printDuke(ERR_INVALID_NUM);
            return;
        }
    }

    private void printDuke(String str) {
        System.out.printf(String.format("%s\n%s\n%s\n", BORDER, str, BORDER)
            .replaceAll("(?m)^", "\t"));
    }

    private void execute(String input) {
        String[] tmp =  input.split(" ", 2);
        String command = tmp[0];
        String args = tmp.length > 1 ? tmp[1] : "";
        commandsMap.getOrDefault(command, (str) -> addTask(input)).accept(args);
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
