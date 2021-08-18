import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class Duke {
    private static final List<Task> list = new ArrayList<>();
    private static final String divider = "____________________________________________________________";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // TODO: handle more errors
    // TODO: use enum
    private enum Action {
        LIST, DONE, REMOVE, TODO, DEADLINE, EVENT
    }

    private static void launch() {
        System.out.println(divider);
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(divider);
    }

    private static void close() {
        System.out.println(divider + "\n" + "Bye. Hope to see you again soon!" + "\n" + divider);
    }

    private static void handleAction(Action action, Scanner sc) throws DukeException {
        switch (action) {
            case LIST: {
                int index = 1;
                for (Task task : list) {
                    System.out.println(index + ". " + task);
                    index++;
                }
                break;
            }
            case DONE: {
                int index = sc.nextInt();
                if (index < 1 || index > list.toArray().length) {
                    System.out.println(list.toArray().length > 0
                            ? "OOPS!!! I'm sorry, index is out of range! " +
                            "Please try with a number from 1 to " + list.toArray().length
                            : "OOPS!!! I'm sorry, there is nothing in the list yet.");
                } else {
                    Task task = list.get(index - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + task);
                }
                break;
            }
            case REMOVE: {
                int index = sc.nextInt();
                if (index < 1 || index > list.toArray().length) {
                    System.out.println(list.toArray().length > 0
                            ? "OOPS!!! I'm sorry, index is out of range! " +
                            "Please try with a number from 1 to " + list.toArray().length
                            : "OOPS!!! I'm sorry, there is nothing in the list yet.");
                } else {
                    Task task = list.remove(index - 1);
                    System.out.println("Noted. I've removed this task:\n  " +
                            task +
                            "\nNow you have " + list.toArray().length + " tasks in the list.");
                }
                break;
            }
            case TODO: {
                String description = sc.nextLine();
                Task task = new Todo(description);
                list.add(task);
                System.out.println("Got it. I've added this task:\n  " +
                        task +
                        "\nNow you have " + list.toArray().length + " task(s) in the list.");
                break;
            }
            case DEADLINE: {
                String input2 = sc.nextLine();
                String[] line = input2.split(" /by ");
                if (line.length != 2) {
                    throw new DukeException(
                            "☹ OOPS!!! Seems like you have entered a wrong format for a deadline task. " +
                                    "Try this instead: deadline <description> /by <date>"
                    );
                }
                Task task = new Deadline(line[0], line[1]);
                list.add(task);
                System.out.println("Got it. I've added this task:\n  " +
                        task +
                        "\nNow you have " + list.toArray().length + " task(s) in the list.");
                break;
            }
            case EVENT: {
                String input2 = sc.nextLine();
                String[] line = input2.split(" /at ");
                if (line.length != 2) {
                    throw new DukeException(
                            "☹ OOPS!!! Seems like you have entered a wrong format for an event task. " +
                                    "Try this instead: event <description> /at <date>"
                    );
                }
                Task task = new Event(line[0], line[1]);
                list.add(task);
                System.out.println("Got it. I've added this task:\n  " +
                        task +
                        "\nNow you have " + list.toArray().length + " task(s) in the list.");
                break;
            }
        }

    }

    public static void main(String[] args) {
        launch();
        try (Scanner sc = new Scanner(System.in)) {
            String input = sc.next();
            while (!input.equals("bye")) {
                try {
                    System.out.println(divider);
                    switch (input) {
                        case "list": {
                            handleAction(Action.LIST, sc);
                            break;
                        }
                        case "done": {
                            handleAction(Action.DONE, sc);
                            break;
                        }
                        case "remove": {
                            handleAction(Action.REMOVE, sc);
                            break;
                        }
                        case "todo": {
                            handleAction(Action.TODO, sc);
                            break;
                        }
                        case "deadline": {
                            handleAction(Action.DEADLINE, sc);
                            break;
                        }
                        case "event": {
                            handleAction(Action.EVENT, sc);
                            break;
                        }
                        default: {
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println(divider);
                    // TODO: clear scanner buffer
                    input = sc.next();
                }
            }
            close();
        } catch (NoSuchElementException e) {
            // happens in unit test
        }
    }
}