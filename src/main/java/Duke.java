import java.util.Scanner;

public class Duke {

    /**
     * Main execution when Duke is run.
     *
     * @param args Will not be used
     */
    public static void main(String[] args) {

        String welcomeMsg = "What's up, I'm duke!";
        printMsg(welcomeMsg);

        Scanner sc = new Scanner(System.in);

        boolean exit = false;

        while(!exit) {
            String nextInput = sc.nextLine();
            if (nextInput.equals("bye")) {
                printMsg("Bye mate!");
                exit = true;
            } else if (nextInput.equals("list")){
                printTasks();
            } else {
                Task.addTask(nextInput);
                printMsg("added: " + nextInput);
            }
        }
    }

    /**
     * Print all tasks.
     */
    private static void printTasks() {
        printMsg(Task.getTaskStrings());
    }

    /**
     * Prints out an indented message with dividers.
     *
     * @param msg Message to be printed, as a string.
     */
    private static void printMsg(String msg) {
        System.out.println("    ---");
        System.out.println("    " + msg);
        System.out.println("    ---");
    }

    /**
     * Prints out an array of messages.
     *
     * @param msgs Messages to be printed, as a string array.
     */
    private static void printMsg(String[] msgs) {
        System.out.println("    ---");
        for (String msg : msgs) {
            System.out.println("    " + msg);
        }
        System.out.println("    ---");
    }
}
