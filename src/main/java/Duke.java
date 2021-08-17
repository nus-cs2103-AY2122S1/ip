import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String NAME = "Tze Henn";
    static final ArrayList<Task> list = new ArrayList<>();

    public static void lineGenerator() {
        System.out.println("____________________________________________________________");
    }

    public static void run() {
        try {
            lineGenerator();
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(logo);
            System.out.println("Hello! I'm " + NAME);
            System.out.println("What can I do for you?");
            lineGenerator();

            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter command: ");
            String input = sc.nextLine();


            while (!input.equals("bye")) {
                lineGenerator();
                Task t;
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                } else if (input.startsWith("done")) {
                    System.out.println("Nice! I've marked this task as done: ");
                    String[] splitStr = input.split("\\s+");
                    System.out.print("  ");
                    list.get(Integer.parseInt(splitStr[1]) - 1).markTaskDone();
                } else if (input.startsWith("delete")) {
                    System.out.println("Noted! I've removed this task: ");
                    String[] splitStr = input.split("\\s+");
                    System.out.print("  ");
                    System.out.println(list.get(Integer.parseInt(splitStr[1]) - 1));
                    list.remove(Integer.parseInt(splitStr[1]) - 1);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else {
                    if (input.startsWith("todo")) {
                        String description = input.substring(4).trim();
                        if (description.isEmpty()) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty :-(");
                        }
                        t = new Todo(description);
                    } else if (input.startsWith("deadline")) {
                        int slashPosition = input.indexOf('/');
                        String description = input.substring("deadline".length() + 1, slashPosition);
                        String by = input.substring(slashPosition + 4);
                        t = new Deadline(description, by);
                    } else if (input.startsWith("event")) {
                        int slashPosition = input.indexOf('/');
                        String description = input.substring("event".length() + 1, slashPosition);
                        String at = input.substring(slashPosition + 4);
                        t = new Event(description, at);
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Got it. I've added this task: ");
                    list.add(t);
                    System.out.println("  " + t);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }
                lineGenerator();
                System.out.print("\nEnter command: ");
                input = sc.nextLine();
            }
            lineGenerator();
            System.out.println("Bye. Hope to see you again soon!");
            lineGenerator();
        } catch (DukeException e) {
            System.out.println(e);
            lineGenerator();
        }
    }

    public static void main(String[] args) {
        run();
    }
}
