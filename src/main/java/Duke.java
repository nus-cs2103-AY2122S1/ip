import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static boolean inSession;
    private static String greetMessage = "Hello! I'm Duke \nWhat can I do for you?";
    private static String exitMessage = "Bye. Hope to see you again soon!\n";
    private static ArrayList<Task> toDoList = new ArrayList<>();
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
                    try {
                        markAsDone(input);
                    } catch (DukeException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case ("delete"):
                    try {
                        delete(input);
                    } catch (DukeException e) {
                        System.err.println(e.getMessage());
                    }
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
            throw new DukeException("☹ OOPS!!! " +
                    "The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(info[1]);
        toDoList.add(newTodo);
        counter++;
        System.out.println(addedMessage + newTodo.toString() +
                "\nNow you have " + counter + " tasks in the list.");
    }

    public static void addDeadline(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The description of a deadline cannot be empty.");
        }
        String[] description = info[1].split("/by ", 2);
        if (description.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The deadline of a deadline cannot be empty.");
        }
        Deadline newDeadline = new Deadline(description[0], description[1]);
        toDoList.add(newDeadline);
        counter++;
        System.out.println(addedMessage + newDeadline.toString() +
                "\nNow you have " + counter + " tasks in the list.");
    }

    public static void addEvent(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The description of an event cannot be empty.");
        }
        String[] description = info[1].split("/at ", 2);
        if (description.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "The date of an event cannot be empty.");
        }
        Event newEvent = new Event(description[0], description[1]);
        toDoList.add(newEvent);
        counter++;
        System.out.println(addedMessage + newEvent.toString() +
                "\nNow you have " + counter + " tasks in the list.");
    }

    public static void displayList() {
        String listString = "Here are the tasks in your list:";
        for (int i = 0; i < toDoList.size(); i++) {
            listString += "\n" + (i + 1) + "." + toDoList.get(i).toString();
        }
        System.out.println(listString);
    }

    public static void markAsDone(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "Please enter the task number you would like to mark as done.");
        }
        int i;
        try {
            i = Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! " +
                    "Please enter an integer for the task you would like to mark as done.");
        }
        if (i > toDoList.size()) {
            throw new DukeException("☹ OOPS!!! " +
                    "There isn't a task number " + i + ".");
        }
        Task t = toDoList.get(i - 1);
        t.setDone();
        System.out.println("Nice! I've marked this task as done:\n"
                            + "  " + t.toString());
    }

    public static void delete(String input) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length == 1) {
            throw new DukeException("☹ OOPS!!! " +
                    "Please enter the task number you would like to delete.");
        }
        int i;
        try {
            i = Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! " +
                    "Please enter an integer for the task you would like to delete.");
        }
        if (i > toDoList.size()) {
            throw new DukeException("☹ OOPS!!! " +
                    "There isn't a task number " + i + ".");
        }
        Task t = toDoList.remove(i - 1);
        System.out.println("Noted! I've removed this task:\n"
                + "  " + t.toString() + "\nNow you have " + toDoList.size()  + " tasks in the list.");
    }

    public static void unknownCommand() {
        System.err.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void main(String[] args) {
        Duke.start();
    }
}
