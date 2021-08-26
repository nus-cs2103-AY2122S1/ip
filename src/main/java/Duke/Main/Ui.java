package Duke.Main;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    /**
     * Constructor for the UI class, setting up a Scanner object
     */
    public Ui() {
        this.sc = new Scanner(System.in).useDelimiter("\n");
    }

    /**
     * Initializing Duke (Animation)
     */
    public void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        respondWith("Hello! I'm Duke. \nType in 'help' if you are new to us." +
                "\nWhat can I do for you?");
    }

    /**
     * Exiting Duke and stop the program
     */
    public void exit() {
        System.out.println("\nProgram exiting... \nBye. Hope to see you again soon!\n");
    }

    /**
     * Reply template for each command-reply cycle
     * @param input a String that represents the content of reply message
     */
    public void respondWith(String input) {
        System.out.println();
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(input);
        System.out.println();
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.print("Enter command: ");
    }

    /**
     * Read and scan the command entered by the user
     * @return the String entered by user
     */
    public String readCommand() {
        return sc.next().trim();
    }
}
