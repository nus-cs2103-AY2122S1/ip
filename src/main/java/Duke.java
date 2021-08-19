import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "===========================";
        System.out.println(line);
        System.out.println("hello! i'm calico ☺");
        System.out.println("how can i assist you?");
        System.out.println(line);

        // List to store commands
        ArrayList<Task> cmdList = new ArrayList<>();
        int count = 0;

        // Create a scanner to read from standard input.
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {       // when command given is "list"
                System.out.println(line);
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + "." + cmdList.get(i));
                }
                System.out.println(line);
            } else if (cmd.matches("^done [0-9]+$")) {      // when command given is "done"
                String[] temp = cmd.split(" ");
                int itemNo = Integer.parseInt(temp[1]);

                // Make sure itemNo is within limit
                if ((itemNo <= count) && (itemNo > 0)) {
                    cmdList.get(itemNo - 1).markDone();
                    System.out.println(line);
                    System.out.println("congrats on completing this task! :");
                    System.out.println("  " + cmdList.get(itemNo - 1));
                } else {        // add command to list
                    System.out.println(line);
                    cmdList.add(new Task(cmd));
                    count++;
                    System.out.println("added: " + cmd);
                }
                System.out.println(line);
            } else if (cmd.matches("^todo .*$")
                || cmd.matches("^deadline .* /by .*$")
                || cmd.matches("^event .* /at .*$")) {
            String[] temp = cmd.split(" ", 2);

            System.out.println(line);
            System.out.println("noted! ive added this task:");

            // Separate into cases
            switch (temp[0]) {
                case "todo":
                    cmdList.add(new Todo(temp[1]));
                    System.out.println("  " + cmdList.get(count));
                    break;
                case "deadline":
                    String[] temp_deadline = temp[1].split(" /by ", 2);
                    cmdList.add(new Deadline(temp_deadline[0], temp_deadline[1]));
                    System.out.println("  " + cmdList.get(count));
                    break;
                case "event":
                    String[] temp_event = temp[1].split(" /at ", 2);
                    cmdList.add(new Event(temp_event[0], temp_event[1]));
                    System.out.println("  " + cmdList.get(count));
                    break;
            }
            count++;
            System.out.println("now you have " + count + " tasks in your list");
            System.out.println(line);
        } else {         // add command to list
                System.out.println(line);
                cmdList.add(new Task(cmd));
                count++;
                System.out.println("added: " + cmd);
                System.out.println(line);
            }
            cmd = sc.nextLine();
        }

        // when command given is "bye"
        System.out.println(line);
        System.out.println("bye friend!");
        System.out.println(line);
    }
}
