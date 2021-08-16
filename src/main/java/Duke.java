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

        String cmd;
        Scanner scanner = new Scanner(System.in);
        Tasklist tasklist = new Tasklist();

        start();
        do {
            cmd = scanner.next();
            if(cmd.equals("bye")) {
                exit();
                break;
            } else if(cmd.equals("list")) {
                tasklist.list();
            } else if(cmd.equals("done")){
                int idx = Integer.parseInt(scanner.next()) - 1;
                tasklist.getTask(idx).setStatus(true);
            } else {
                Task task = new Task(cmd);
                tasklist.add(task);
            }
        } while (!cmd.equals("bye"));
    }
}

