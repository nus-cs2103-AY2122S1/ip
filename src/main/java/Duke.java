import java.util.Scanner;

public class Duke {
    private static boolean inSession;
    private static String greetMessage = "Hello! I'm Duke \nWhat can I do for you?";
    private static String exitMessage = "Bye. Hope to see you again soon!";
    private static Task[] toDoList = new Task[100];
    private static int counter = 0;

    public static void start() {
        inSession = true;
        greet();
        while (inSession) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().toLowerCase();
            String action = input.split(" ")[0];
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
                default:
                    add(input);
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

    public static void add(String input) {
        toDoList[counter] = new Task(input);
        counter++;
        System.out.println("added: " + input);
    }

    public static void displayList() {
        String listString = "Here are the tasks in your list:";
        for (Task t : toDoList) {
            if (t != null) {
                listString += "\n" + t.getId() + "."
                        + t.getStatusIcon() + " " + t.getDescription();
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
                            + "  " + t.getStatusIcon() + " " + t.getDescription());
                }
            }
        }
    }

    public static void main(String[] args) {
        Duke.start();
    }
}
