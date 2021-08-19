import java.lang.reflect.Array;
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
        Task[] tasks = new Task[100];
        int len = 0;
        while (true) {
            input = scanner.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    for (int i = 0; i < len; i++) {
                        System.out.printf("%d.%s\n", i + 1, tasks[i]);
                    }
                    break;
                default:
                    if (input.matches("done \\d+")) {
                        int i = Integer.parseInt(input.substring(5));
                        System.out.println("Nice! I've marked this task as done:");
                        Task t = tasks[i - 1];
                        t.markAsComplete();
                        System.out.println(t);
                    } else {
                        try {
                            if (input.matches("event.*")) {
                                int k = input.indexOf("/at");
                                if (k < 0) {
                                    throw new DukeException.MissingArgumentException("/at");
                                }
                                Task t  = new Event(input.substring(5, k).trim(),input.substring(k + 3).trim());
                                System.out.println("Got it. I've added this task:");
                                System.out.println(t);
                                tasks[len++] = t;
                            } else if (input.matches("deadline.*")) {
                                int k = input.indexOf("/by");
                                if (k < 0) {
                                    throw new DukeException.MissingArgumentException("/by");
                                }
                                Task t  = new Deadline(input.substring(8, k).trim(),input.substring(k + 3).trim());
                                System.out.println("Got it. I've added this task:");
                                System.out.println(t);
                                tasks[len++] = t;
                            } else if (input.matches("todo.*")) {
                                Task t = new Todo(input.substring(4));
                                System.out.println("Got it. I've added this task:");
                                System.out.println(t);
                                tasks[len++] = t;
                            } else {
                                throw new DukeException.UnknownInputException();
                            }
                            System.out.printf("Now you have %d tasks in the list\n", len);
                        } catch (Exception err) {
                            System.out.println(err.getMessage());
                        }


                    }

            }
        }
    }
}


