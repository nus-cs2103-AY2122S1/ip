import java.util.*;

/**
 * The Duke program implements a bot with a set of simple commands
 *
 * @author Calvin Tan
 */
public class Duke {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        start();
        Scanner sc = new Scanner(System.in);
        String command = sc.next();
        String arguments;

        while (!command.equals("bye")) {
            printBorder();
            try {
                switch (command) {
                    case "list":
                        println("Here are the tasks in your list:");
                        if (tasks.isEmpty()) {
                            println("  You currently have no tasks. Why not add a task?");
                        } else {
                            for (int i = 0; i < tasks.size(); i++) {
                                Task currTask = tasks.get(i);
                                println((i + 1) + ". " + currTask);
                            }
                        }
                        break;
                    case "delete":
                    case "done":
                        arguments = sc.nextLine().stripLeading();
                        if (arguments.isEmpty()) {
                            throw new DukeException("No index was keyed in. Please try again.");
                        }
                        int index = Integer.parseInt(arguments);
                        if (index < 1 || index > tasks.size()) {
                            throw new DukeException("The index you entered is invalid. Please try again.");
                        }
                        if (command.equals("delete")) {
                            Task taskDeleted = tasks.remove(index - 1);
                            println("Noted! I've removed this task:");
                            println("  " + taskDeleted);
                            println("Now you have " + tasks.size() +
                                    (tasks.size() == 1 ? " task" : " tasks")
                                    + " in your list.");
                        } else {
                            Task taskToBeMarked = tasks.get(index - 1);
                            taskToBeMarked.markTaskAsDone();
                            println("Nice! I've marked this task as done:");
                            println("  " + taskToBeMarked);
                        }
                        break;
                    case "todo":
                    case "event":
                    case "deadline":
                        arguments = sc.nextLine().stripLeading();
                        if (arguments.isEmpty()) {
                            throw new DukeException(String.format("The description of a %s cannot be left empty. "
                                    + "Please try again.", command));
                        }
                        switch (command) {
                            case "todo": {
                                Todo newTask = new Todo(arguments);
                                tasks.add(newTask);
                                println("Got it. I've added this task:");
                                println("  " + newTask);
                                break;
                            }
                            case "deadline": {
                                String[] argArr = arguments.split("/by");
                                if (argArr.length == 1 || argArr[1].isEmpty()) {
                                    throw new DukeException("Arguments do not follow proper format. Don't forget the /by");
                                }
                                Deadline newTask = new Deadline(argArr[0], argArr[1]);
                                tasks.add(newTask);
                                println("Got it. I've added this task:");
                                println("  " + newTask);
                                break;
                            }
                            case "event": {
                                String[] argArr = arguments.split("/at");
                                if (argArr.length == 1 || argArr[1].isEmpty()) {
                                    throw new DukeException("Arguments do not follow proper format. Don't forget the /at");
                                }
                                Event newTask = new Event(argArr[0], argArr[1]);
                                tasks.add(newTask);
                                println("Got it. I've added this task:");
                                println("  " + newTask);
                                break;
                            }
                        }
                        println("Now you have " + tasks.size() +
                                (tasks.size() == 1 ? " task" : " tasks")
                                + " in your list.");
                        ;
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what \""
                                + (command + sc.nextLine()) + "\" means :-(");
                }
            } catch (DukeException e) {
                println(e.getMessage());
            }
            printBorder();
            command = sc.next();
        }

        end();
        sc.close();
    }

    public static void start() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t____________________________________________________________");
        System.out.println(logo);
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    public static void end() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void println(String message) {
        System.out.println("\t" + message);
    }

    public static void printBorder() {
        System.out.println("\t____________________________________________________________");
    }

}
