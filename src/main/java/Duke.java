import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List list = new List();
        String command;
        String line = "    ____________________________________________________________\n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     What can I do for you?\n" + line);
        command = sc.next();
        while (!command.equals("bye")) {
            switch(command) {
            case "list":
                System.out.println(line + list.returnItems() + line);
                break;
            case "done":
                int index = sc.nextInt();
                System.out.println(line + "     Nice! I've marked this task as done:\n"
                        + list.markDone(index) + line);
                break;
            default:
                    command += sc.nextLine();
                list.addItem(command);
                System.out.println(line + "     added: " + command + "\n" + line);
                break;
            }
            command = sc.next();
        }
        System.out.println(line + "     Bye. Hope to see you again soon!\n" + line);
    }
}