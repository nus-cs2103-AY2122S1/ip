import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____   ___  _        \n"
                + "|  _ \\/  _ \\| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\___/|_|\\_\\___|\n";
        System.out.println("----------------------------------------");
        System.out.println(logo);
        System.out.println("Hello! I'm Doke\nWhat do you want??");
        System.out.println("----------------------------------------\n");
        echoMessage();
    }

    /**
     * Reads messages from input, echos back if message is not bye, exits otherwise
     */
    public static void echoMessage() {
        Scanner messageReader = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            String message = messageReader.nextLine();
            System.out.println("\n----------------------------------------");
            if (message.equals("bye")) {
                System.out.println("    Bye. Hope to see you again!");
                end = true;
            } else {
                System.out.println("    " + message);
            }
            System.out.println("----------------------------------------\n");
        }

    }
}
