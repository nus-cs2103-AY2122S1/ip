import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String input;
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            input = scanner.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d.%s\n", i + 1, tasks.get(i));
                    }
                    break;
                default: // changes made
                    if (input.matches("done \\d+")) {
                        int i = Integer.parseInt(input.substring("done ".length()));
                        System.out.println("Nice! I've marked this task as done:");
                        Task t = tasks.get(i - 1);
                        t.markAsComplete();
                        System.out.println(t);
                    } else if (input.matches("delete \\d+")) {
                        int i = Integer.parseInt(input.substring("delete ".length()));
                        System.out.println("Noted. I've removed this task:");
                        Task t = tasks.remove(i - 1);
                        System.out.println(t);
                    } else {
                        try {
                            Task t;
                            if (input.matches("event.*")) {
                                int k = input.indexOf("/at");
                                if (k < 0) {
                                    throw new DukeException.MissingArgumentException("/at");
                                }
                                t = new Event(input.substring("event".length(), k).trim(), input.substring(k + 3).trim());
                            } else if (input.matches("deadline.*")) {
                                int k = input.indexOf("/by");
                                if (k < 0) {
                                    throw new DukeException.MissingArgumentException("/by");
                                }
                                t = new Deadline(input.substring("deadline".length(), k).trim(), input.substring(k + 3).trim());
                            } else if (input.matches("todo.*")) {
                                t = new Todo(input.substring("todo".length()));
                            } else {
                                throw new DukeException.UnknownInputException();
                            }
                            tasks.add(t);
                            System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n", t, tasks.size());
                        } catch (Exception err) {
                            System.out.println(err.getMessage());
                        }
                    }
            }
        }
    }

    public void addTask(Task t) {

    }
}


