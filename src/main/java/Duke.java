import java.util.Scanner;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final Scanner sc = new Scanner(System.in);
    private static final TaskList list = new TaskList();

    public static void main(String[] args) throws Exception {
        greet();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) break;
            else if (input.equals("list")) echo(list.toString());
            else if (input.contains("done")) {
                input = input.substring(5);
                int index = Integer.parseInt(input);
                done(index);
            }
            else if (input.contains("todo")) {
                input = input.substring(5);
                add(new ToDo(input));
            } else if (input.contains("deadline")) {
                input = input.substring(9);
                String[] parsedInput = input.split(" /by ");
                add(new Deadline(parsedInput[0], parsedInput[1]));
            } else if (input.contains("event")) {
                input = input.substring(6);
                String[] parsedInput = input.split(" /at ");
                add(new Event(parsedInput[0], parsedInput[1]));
            }
            else throw new Exception();
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

    private static void add(Task task) {
        list.addTask(task);
        int len = list.getLen();
        String message = len <= 1 ?
                String.format("Got it. I've added this task:\n  %s\nNow you have %d task in the list.", task.toString(), len) :
                String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task.toString(), len);
        echo(message);
    }

    private static void done(int index) {
        Task task = list.markTaskAsDone(index);
        String message = String.format("Nice! I've marked this task as done:\n  %s", task);
        echo(message);
    }
}
