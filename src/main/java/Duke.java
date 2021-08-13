import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List list = new List();
        String command;
        String line = "    ____________________________________________________________\n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     What can I do for you?\n" + line);
        command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(line + list.returnItems() + line);
            } else {
                list.addItem(command);
                System.out.println(line + "     added: " + command + "\n" + line);
            }
            command = sc.nextLine();
        }
        System.out.println(line + "     Bye. Hope to see you again soon!\n" + line);
    }
}