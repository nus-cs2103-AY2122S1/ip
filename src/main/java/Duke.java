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
        String doneCmd = "done";

        String cmd;
        Scanner scanner = new Scanner(System.in);
        Tasklist tasklist = new Tasklist();

        start();
        do {
            cmd = scanner.next();
            if(cmd.equals(exitCmd)) {
                exit();
                break;
            } else if(cmd.equals(listCmd)) {
                tasklist.list();
            } else if(cmd.equals(doneCmd)){
                // Can include an error message if idx is not a number
                int idx = Integer.parseInt(scanner.next()) - 1;
                tasklist.getTask(idx).setStatus(true);
            } else if (cmd.equals("todo")) {
                String input = scanner.nextLine();
                Todo task = new Todo(input, false);
                tasklist.add(task);
            } else if (cmd.equals("deadline")) {
                String input = scanner.nextLine();
                String[] nameNTime = input.split("/by ");
                String name = nameNTime[0];
                String deadline = nameNTime[1];
                Deadline task = new Deadline(name, false, deadline);
                tasklist.add(task);
            } else if (cmd.equals("event")) {
                String input = scanner.nextLine();
                String[] nameNTime = input.split("/at ");
                String name = nameNTime[0];
                String event = nameNTime[1];
                Event task = new Event(name, false, event);
                tasklist.add(task);
            } else {
                System.out.println("Wrong input.");
                System.out.println(breakline);
            }
        } while (!cmd.equals(exitCmd));
    }
}

