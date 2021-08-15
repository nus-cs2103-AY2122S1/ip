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
        while (inSession) {
            Scanner sc = new Scanner(System.in);
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
                    String todoDescription = input.split(" ",2)[1];
                    addTodo(todoDescription);
                    break;
                case ("deadline"):
                    String[] deadlineInfo = input.split(" ", 2)[1]
                            .split("/by ", 2);
                    addDeadline(deadlineInfo[0], deadlineInfo[1]);
                    break;
                case ("event"):
                    String[] eventInfo = input.split(" ", 2)[1]
                            .split("/at ", 2);
                    addEvent(eventInfo[0], eventInfo[1]);
                    break;
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
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

    public static void addTodo(String description) {
        Todo newTodo = new Todo(description);
        toDoList[counter] = newTodo;
        counter++;
        System.out.println(addedMessage + newTodo.toString() + "\nNow you have " + counter + " tasks in the list.");
    }

    public static void addDeadline(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        toDoList[counter] = newDeadline;
        counter++;
        System.out.println(addedMessage + newDeadline.toString() + "\nNow you have " + counter + " tasks in the list.");
    }

    public static void addEvent(String description, String at) {
        Event newEvent = new Event(description, at);
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

    public static void main(String[] args) {
        Duke.start();
    }
}
