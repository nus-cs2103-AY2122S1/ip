import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Greetings
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // Take in user input
        while (true) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            System.out.println("    ____________________________________________________________");
            if (str.equals("bye")){
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            }
            System.out.println("     " + str);
            System.out.println("    ____________________________________________________________");
        }
    }
}
