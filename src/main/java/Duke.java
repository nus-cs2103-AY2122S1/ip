import java.util.Scanner;

public class Duke {


    private static String breakline = "____________________________________________________________";

    public static void start() {
        System.out.println(breakline);
        String greetingMsg = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetingMsg);
        System.out.println(breakline);
    }

    public static void exit() {
        String byeMsg = "Bye. Hope to see you again soon!";
        System.out.println(byeMsg);
        System.out.println(breakline);
    }

    public static void main(String[] args) {

        String exitCmd = "bye";
        String listCmd = "list";
        String cmd;
        Scanner scanner = new Scanner(System.in);
        Tasklist tasklist = new Tasklist();

        start();
        int count = 0;
        do {
            cmd = scanner.nextLine();
            if(cmd.equals(exitCmd)) {
                exit();
                break;
            } else if(cmd.equals(listCmd)) {
                tasklist.list();
            } else {
                Task task = new Task(cmd);
                tasklist.add(task);
                count++;
            }
        } while (!cmd.equals(exitCmd));
    }
}
