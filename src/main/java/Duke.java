import java.util.Scanner;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final Scanner sc = new Scanner(System.in);
    private static final TaskList list = new TaskList();

    public static void main(String[] args) {
        greet();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) break;
            else if (input.equals("list")) echo(list.toString());
            else if (input.contains("done")) {
                input = input.substring(5);
                int index = Integer.parseInt(input);
                markTaskAsDone(index);
            }
            else addTask(input);
        }
        exit();
    }

    private static void greet() {
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

    private static void echo(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    private static void exit() {
        System.out.println(LINE);
        System.out.println("Goodbye, hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void addTask(String task) {
        list.addTask(new Task(task));
        String message = String.format("added: %s", task);
        echo(message);
    }

    private static void markTaskAsDone(int index) {
        Task task = list.markTaskAsDone(index);
        String message = String.format("Nice! I've marked this task as done:\n   %s", task);
        echo(message);
    }
}
