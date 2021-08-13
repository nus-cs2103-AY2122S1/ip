import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        String line = "    ____________________________________________________________\n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     What can I do for you?\n" + line);
        command = sc.next();
        while (!command.equals("bye")) {
            System.out.println(line + "     " + command + "\n" + line);
            command = sc.next();
        }
        System.out.println(line + "     Bye. Hope to see you again soon!\n" + line);
    }
}
