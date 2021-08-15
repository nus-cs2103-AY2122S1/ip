import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String indent = "    ____________________________________________________________\n";
        String tab = "     ";
        System.out.println(indent + tab + "Hello! I'm Duke\n" + tab + "What can I do for you?\n" + indent);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println(indent + tab + "Bye. Hope to see you again soon!\n" + indent);
                break;
            }
            System.out.println(indent + tab + input + "\n" + indent);
        }
    }
}
