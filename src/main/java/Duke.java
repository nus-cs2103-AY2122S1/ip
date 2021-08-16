import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello FROM\n" + logo);
        greetings();
    }

    private static void greetings() {
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.next();
            if (!command.equals("bye")) {
                System.out.println("    ______________________________________");
                System.out.printf("     %s\n",command);
                System.out.println("    ______________________________________");
            } else {
                System.out.println("    ______________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ______________________________________");
                scanner.close();
                break;
            }
        }
    }


}
