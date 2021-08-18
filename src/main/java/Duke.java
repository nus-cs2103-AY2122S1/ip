import java.util.Scanner;

public class Duke {
    public final static String lines = "----------------------------------------------------\n";

    public static void printFormattedMessage(String message) {
        System.out.println(lines + message + lines);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings friend! I am your personal assistant,\n" + 
                            logo + 
                            "\nWhat can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String command;
        
        do {
            command = sc.nextLine();
            printFormattedMessage(command + "\n");
        } while (!command.equals("bye"));

        printFormattedMessage("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
