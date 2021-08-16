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
            else addTask(input);
        }
        exit();
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
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
        list.addTask(task);
        String message = String.format("added: %s", task);
        echo(message);
    }
}
