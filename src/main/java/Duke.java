import java.util.List;
import java.util.Scanner;

public class Duke {

    private final String LINE = "____________________________________________________________";
    private final Scanner sc = new Scanner(System.in);
    private final TaskList tasks = new TaskList();
    private final Storage storage = new Storage();


    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        greet();
        while (true) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) break;
                else if (input.equals("list")) echo(tasks.toString());
                else if (input.contains("done")) {
                    String[] parseInput = input.split(" ");
                    if (parseInput.length <= 1) throw new DukeException("OOPS!!! You need to indicate a task for me to mark as done.");
                    int index = Integer.parseInt(parseInput[1]);
                    if (index <= 0 || index > tasks.size()) throw new DukeException("OOPS!!! Looks like there is no such task to be marked as done");
                    done(index);
                } else if (input.contains("todo")) {
                    if (input.length() <= 4) throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    input = input.substring(5);
                    add(new ToDo(input));
                } else if (input.contains("deadline")) {
                    if (input.length() <= 8) throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    input = input.substring(9);
                    String[] parsedInput = input.split(" /by ");
                    if (parsedInput.length <= 1) throw new DukeException("OOPS!!! You need to provide a /by date");
                    add(new Deadline(parsedInput[0], parsedInput[1]));
                } else if (input.contains("event")) {
                    if (input.length() <= 5) throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    input = input.substring(6);
                    String[] parsedInput = input.split(" /at ");
                    if (parsedInput.length <= 1) throw new DukeException("OOPS!!! you need to provide a /at date");
                    add(new Event(parsedInput[0], parsedInput[1]));
                } else if (input.contains("delete")) {
                    String[] parseInput = input.split(" ");
                    if (parseInput.length <= 1) throw new DukeException("OOPS!!! You need to indicate a task for me to delete.");
                    int index = Integer.parseInt(parseInput[1]);
                    if (index <= 0 || index > tasks.size()) throw new DukeException("OOPS!!! Looks like there is no such task to be deleted");
                    delete(index);
                } else throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException ex) {
                echo(ex.getMessage());
            }
        }
        exit();
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
