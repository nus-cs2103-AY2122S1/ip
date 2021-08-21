import java.util.ArrayList;

public class Ui {
    private static final String divider = "\t--------------------------------------------------------";

    private static final String banner =
            "____    __    ____  _______  __        ______   ______   .___  ___.  _______    .___________.  ______       _______   __    __   __  ___  _______  __  \r\n"
                    + "\\   \\  /  \\  /   / |   ____||  |      /      | /  __  \\  |   \\/   | |   ____|   |           | /  __  \\     |       \\ |  |  |  | |  |/  / |   ____||  | \r\n"
                    + " \\   \\/    \\/   /  |  |__   |  |     |  ,----'|  |  |  | |  \\  /  | |  |__      `---|  |----`|  |  |  |    |  .--.  ||  |  |  | |  '  /  |  |__   |  | \r\n"
                    + "  \\            /   |   __|  |  |     |  |     |  |  |  | |  |\\/|  | |   __|         |  |     |  |  |  |    |  |  |  ||  |  |  | |    <   |   __|  |  | \r\n"
                    + "   \\    /\\    /    |  |____ |  `----.|  `----.|  `--'  | |  |  |  | |  |____        |  |     |  `--'  |    |  '--'  ||  `--'  | |  .  \\  |  |____ |__| \r\n"
                    + "    \\__/  \\__/     |_______||_______| \\______| \\______/  |__|  |__| |_______|       |__|      \\______/     |_______/  \\______/  |__|\\__\\ |_______|(__) \r\n"
                    + "                                                                                                                                                       ";

    /**
     * Formats message passed in and prints it out to the screen.
     *
     * @param message Message to be pretty printed.
     */
    public static void prettyPrint(String message) {
        System.out.printf("%s\r\n\t%s\r\n%s\r\n", divider, message, divider);
    }

    /** Prints the welcome message when a user uses the bot for the first time. */
    public void printWelcomeMessage() {
        System.out.println(banner);
        prettyPrint(
                "Hello! I'm Duke, your personal CLI bot!\r\n\t"
                        + "I now function as a simple ToDoList.\r\n\t"
                        + "I can keep track of 3 different types of tasks:\r\n\t"
                        + "\t- ToDo: To add a new todo task, type 'todo' followed by a task description.\r\n\t"
                        + "\t- Deadline: To add a new deadline, type 'deadline' followed by the task description "
                        + "and specify the deadline using '/by <dateTime>'\r\n\t"
                        + "\t- Event: To add a new event, type 'event' followed by the event description "
                        + "and specify the timing using '/by <dateTime>'\r\n\t"
                        + "To see all your task currently, type 'list'.\r\n\t"
                        + "To delete a task, type 'delete' followed by the index of the task you wish to "
                        + "delete (e.g delete 2).\r\n\t"
                        + "You can also mark tasks as done by typing 'done' followed by the index of the "
                        + "task you completed (e.g done 2).\r\n\t"
                        + "Once you are done, just type 'exit' to quit the program.");
    }

    /** Prints the exit message when user types in the exit command. */
    public void printExitMessage() {
        prettyPrint("Bye bye! See you again soon!");
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println(divider);
        // Custom message for when user types 'list' when nothing is added.
        if (list.size() == 0) {
            System.out.println("\tYou are all done for the day :-)");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.printf("\t%s. %s\r\n", i, list.get(i - 1));
            }
        }
        System.out.println(divider);
    }
}
