import java.util.Scanner;

public class Duke {

    private final String LINE = "____________________________________________________________";
    private final Scanner sc = new Scanner(System.in);
    private final TaskList tasks = new TaskList();

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = sc.nextLine();
                System.out.println(LINE);
                Command c = Parser.parse(input);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(LINE);
            }
        }
    }

    private void greet() {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println("Hi, I'm Duke, your Personal Assistant Chatbot\n" +
                "What can I do for you today?");
        System.out.println(LINE);
    }

    private void echo(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    private void exit() {
        System.out.println(LINE);
        System.out.println("Goodbye, hope to see you again soon!");
        System.out.println(LINE);
    }

    private void add(Task task) {
        tasks.addTask(task);
        int len = tasks.size();
        String message = String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                task.toString(),
                len,
                len <= 1 ? "task" : "tasks");
        echo(message);
    }

    private void done(int index) {
        Task task = tasks.markTaskAsDone(index);
        String message = String.format("Nice! I've marked this task as done:\n  %s", task);
        echo(message);
    }

    private void delete(int index) {
        Task task = tasks.deleteTask(index);
        int len = tasks.size();
        String message = String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.",
                task.toString(),
                len,
                len <= 1 ? "task" : "tasks");
        echo(message);
    }
}
