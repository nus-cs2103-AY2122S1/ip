import java.util.Scanner;

public class Duke {
    /**
     * A public static method to print message with certain indentation and format.
     * Receive an array of String, and output one String per line.
     *
     * @param messages
     */
    public static void printMessage(String messages[]) {
        System.out.println("    ____________________________________________________________");
        int n = messages.length;
        for (int i = 0; i < n; i++) {
            System.out.print("     ");
            System.out.println(messages[i]);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    /**
     * A public static method to output the greeting message.
     */
    public static void greet() {
        String greetMessage[] = new String[2];
        greetMessage[0] = "Hello! I'm Duke";
        greetMessage[1] = "What can I do for you?";
        printMessage(greetMessage);
    }

    /**
     * A public static method to repeat commands entered by the user,
     * and exits when the user input "bye" is detected.
     */
    public static void echoAndExit() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String inputMessage = sc.nextLine();
            if (inputMessage.equals("bye")) {
                printMessage(new String[] {"Bye. Hope to see you again soon!"});
                return;
            } else {
                printMessage(new String[] {inputMessage});
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echoAndExit();
    }
}
