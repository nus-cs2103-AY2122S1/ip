import java.util.Scanner;

public class Duke {
    private static boolean inSession;
    private static String greetMessage = "Hello! I'm Duke \nWhat can I do for you?";
    private static String exitMessage = "Bye. Hope to see you again soon!\n";
    private static Task[] toDoList = new Task[100];
    private static String addedMessage = "Got it. I've added this task:\n";
    private static int counter = 0;

    public static void start() {
        inSession = true;
        greet();
        Scanner sc = new Scanner(System.in);
        while (inSession) {
            String input = sc.nextLine();
            String action = input.split(" ", 2)[0].toLowerCase();
            switch (action) {
                case ("bye"):
                    exit();
                    break;
                case ("list"):
                    displayList();
                    break;
                case ("done"):
                    String taskNumber = input.split(" ")[1];
                    markAsDone(taskNumber);
                    break;
                case ("todo"):
                    try {
                        addTodo(input);
                    } catch (DukeException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case ("deadline"):
                    try {
                        addDeadline(input);
                    } catch (DukeException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case ("event"):
                    try {
                        addEvent(input);
                    } catch (DukeException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    unknownCommand();
            }
        }
    }

    public static void greet() {
        System.out.println(greetMessage);
    }

    public static void exit() {
        inSession = false;
        System.out.println(exitMessage);
    }

    public static void addTodo(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(info[1]);
        toDoList[counter] = newTodo;
        counter++;
        System.out.println(addedMessage + newTodo.toString() + "\nNow you have " + counter + " tasks in the list.");
    }

    public static void addDeadline(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] description = info[1].split("/by ", 2);
        if (description.length == 1) {
            throw new DukeException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
        }
        Deadline newDeadline = new Deadline(description[0], description[1]);
        toDoList[counter] = newDeadline;
        counter++;
        System.out.println(addedMessage + newDeadline.toString() + "\nNow you have " + counter + " tasks in the list.");
    }

    public static void addEvent(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] description = info[1].split("/at ", 2);
        if (description.length == 1) {
            throw new DukeException("☹ OOPS!!! The date of an event cannot be empty.");
        }
        Event newEvent = new Event(description[0], description[1]);
        toDoList[counter] = newEvent;
        counter++;
        System.out.println(addedMessage + newEvent.toString() + "\nNow you have " + counter + " tasks in the list.");
    }

    public static void displayList() {
        String listString = "Here are the tasks in your list:";
        for (Task t : toDoList) {
            if (t != null) {
                listString += "\n" + t.getId() + "." + t.toString();
            }
        }
        System.out.println(listString);
    }

    public static void markAsDone(String taskNumber) {
        int i = Integer.parseInt(taskNumber);
        for (Task t : toDoList) {
            if (t != null) {
                if (t.getId() == i) {
                    t.setDone();
                    System.out.println("Nice! I've marked this task as done:\n"
                            + "  " + t.toString());
                }
            }
        }
    }

    public static void unknownCommand() {
        System.err.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void main(String[] args) {
        Duke.start();
    }
}
