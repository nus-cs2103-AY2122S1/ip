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
            try {
                String nextInput = sc.nextLine();
                if (nextInput.equals("bye")) {
                    printMsg("Bye mate!");
                    exit = true;
                } else if (nextInput.equals("list")){
                    printTasks();
                } else if (nextInput.startsWith("done ")) {
                    printMsg(Task.markTask(Integer.parseInt(nextInput.substring(5)) - 1));
                } else if (nextInput.startsWith("todo")) {
                    if (nextInput.length() < 6) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    printMsg(Task.addTask(new Todo(nextInput.substring(5))));
                } else if (nextInput.startsWith("deadline")) {
                    if (nextInput.length() < 10) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    int byIndex = nextInput.indexOf("/by");
                    if (byIndex == -1) {
                        throw new DukeException("/by not found.");
                    }
                    printMsg(Task.addTask(new Deadline(
                            nextInput.substring(9, byIndex - 1),
                            nextInput.substring(byIndex + 4)
                    )));
                } else if (nextInput.startsWith("event")) {
                    if (nextInput.length() < 7) {
                        throw new DukeException("The description of an event cannot be empty.");
                    }
                    int atIndex = nextInput.indexOf("/at");
                    if (atIndex == -1) {
                        throw new DukeException("/at not found.");
                    }
                    printMsg(Task.addTask(new Event(
                            nextInput.substring(6, atIndex - 1),
                            nextInput.substring(atIndex + 4)
                    )));
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException e) {
                printMsg(e.getMessage());
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
