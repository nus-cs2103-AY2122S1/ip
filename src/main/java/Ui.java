import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a horizontal line.
     */
    public void printHorizontalLine() {
        System.out.println("----------------------------------------------------");
    }

    /**
     * Prints a message to show users the number of tasks there are in the list.
     *
     * @param index index of items, i.e. the number of tasks
     */
    public void printTaskNum(int index) {
        System.out.printf("\nNow you have %d tasks in the list.\n", index);
    }

    /**
     * Prints a message to show the task that has been successfully added to the list.
     *
     * @param task task added to items list
     */
    public void printAddTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
    }

    /**
     * Prints the welcome message when the bot is first called.
     */
    public void printWelcomeMessage() {
        String logo = " ______       ____      __\n"
                + "|   _   \\    /    \\    |  |\n"
                + "|  |_|  /   /  /\\  \\   |  |\n"
                + "|  |_|  \\  /  ____  \\  |  |\n"
                + "|_______/ /__/    \\__\\ |__|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Bai.\n" +
                "What can I do for you?\n\n" +
                "Available commands:\n" +
                "   todo <description> - add todo item\n" +
                "   deadline <description> /by <date> - add a task to be completed by <date>\n" +
                "   event <description> /at <date> - add an event scheduled at <date>\n" +
                "   done <number> - mark task <number> as done\n" +
                "   delete <number> - delete the specified task <number>\n" +
                "   list - display the list of tasks");
        printHorizontalLine();

        //        String home = System.getProperty("user.home"); // /Users/xiaoyunwu
        //
        //        java.nio.file.Path path = java.nio.file.Paths.get(home, "my", "app", "dir"); // /Users/xiaoyunwu/my/app/dir
        //
        //        boolean directoryExists = java.nio.file.Files.exists(path); // false

    }

    public void printError(String err) {
        System.out.println(err);
    }
}
