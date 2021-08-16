import java.util.ArrayList;
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
        String[] store = new String[100];
        int pointer = 1;
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("    ______________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ______________________________________");
                scanner.close();
                break;
            } else if (command.equals("list")){
                System.out.println("    ______________________________________");
                for (int i = 0; i < pointer - 1; i++) {
                    System.out.printf("     %d. %s\n", i + 1, store[i]);
                }
                System.out.println("    ______________________________________");
            } else {
                store[pointer - 1] = command;
                pointer = pointer += 1;
                System.out.println("    ______________________________________");
                System.out.printf("     added: %s\n",command);
                System.out.println("    ______________________________________");

            }
        }
    }
}
