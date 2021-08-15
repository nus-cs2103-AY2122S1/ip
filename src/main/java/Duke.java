import java.util.Scanner;

public class Duke {
    private static boolean inSession;
    private static String greetMessage = "Hello! I'm Duke \nWhat can I do for you?";
    private static String exitMessage = "Bye. Hope to see you again soon!";
    private static String[] toDoList = new String[100];
    private static int counter = 0;

    public static void start() {
        inSession = true;
        greet();
        while (inSession) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().toLowerCase();
            switch (input) {
                case ("bye"):
                    exit();
                    break;
                case ("list"):
                    displayList();
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
        toDoList[counter] = input;
        counter++;
        System.out.println("added: " + input);
    }

    public static void displayList() {
        int count = 1;
        for (String s : toDoList) {
            if (s != null) {
                System.out.println(count + ". " + s);
                count++;
            }
        }
    }

    public static void main(String[] args) {
        Duke.start();
    }
}
