import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetEchoExit();
    }

    /**
     * Method for Duke to Greet the user, Echo what the user says and Exit when being told to.
     * Inputs are taken by a scanner from the user's keyboard.
     */
    public static void greetEchoExit() {
        System.out.println("-----------------------------------------");
        System.out.println(" Hello! I am Duke");
        System.out.println(" What can I do for you?");
        System.out.println("-----------------------------------------");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("    -----------------------------------------");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    -----------------------------------------");
                break;
            } else {
                System.out.println("    -----------------------------------------");
                System.out.println("     " + command);
                System.out.println("    -----------------------------------------");
                System.out.println();
            }
        }
        sc.close();
    }
}
