import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.function.Consumer;

public class Duke implements Runnable {
    private final String BORDER = "--------------------------------------------";
    private final String GREETING = "Hello! I'm Duke, what can I do for you?";
    private final String FAREWELL = "Bye. Hope to see you again soon!";
    private final String ERR_MAX_TASKS = "Sorry! You have reached maximum Task capacity.";
    private final int MAX_TASKS = 100;
    private final Map<String, Consumer<String>> commandsMap = Map.of(
        "list", (args) -> listTasks()
    );
    private List<String> tasks = new ArrayList<>();

    private void addTask(String str) {
        if (tasks.size() > MAX_TASKS) {
            printDuke(ERR_MAX_TASKS);
            return;
        }
        tasks.add(str);
        printDuke(String.format("added: %s", str));
    }

    private void listTasks() {
        printDuke(String.join("\n", IntStream.range(0, tasks.size())
            .mapToObj(i -> String.format("%d. %s", i + 1, tasks.get(i)))
            .toArray(String[]::new)));
    }

    private void printDuke(String str) {
        System.out.printf(String.format("%s\n%s\n%s\n", BORDER, str, BORDER)
            .replaceAll("(?m)^", "\t"));
    }

    private void execute(String input) {
        String[] tmp =  input.split(" ", 2);
        String command = tmp[0];
        commandsMap.getOrDefault(command, (str) -> addTask(str)).accept(input);
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
