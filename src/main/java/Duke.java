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
        ArrayList<String> cmdList = new ArrayList<>();
        int count = 0;

        // Create a scanner to read from standard input.
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (!cmd.equals("list")) {
                System.out.println(line);
                cmdList.add(cmd);
                count++;
                System.out.println("added: " + cmd);
                System.out.println(line);
            } else {
                System.out.println(line);
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + cmdList.get(i));
                }
                System.out.println(line);
            }
            cmd = sc.nextLine();
        }

        System.out.println(line);
        System.out.println("bye friend!");
        System.out.println(line);
    }
}
