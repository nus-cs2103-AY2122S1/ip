package duke.ui;

/**
 * Represents an Ui class that is responsible for the interaction with user.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class Ui {

    /**
     * Shows a welcome message from chat bot.
     */
    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Duke: Hello from\n" + logo);
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Shows a goodbye message when user enter exit command.
     *
     * @return String of the goodbye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }
}
