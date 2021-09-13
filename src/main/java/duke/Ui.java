package duke;

public class Ui {

    /**
     * Displays bye message.
     *
     * @return String message for the bye sequence.
     */
    public String byeMessage() {
        return "See ya again later!";
    }

    /**
     * Displays the help message for users.
     *
     * @return String message containing all the commands.
     */
    public String helpMessage() {
        String helpIntro = "List of commands you can use you dummy!\n\n";
        String todoHelp = "todo - 'todo <insert task name>'\n\n";
        String deadlineHelp = "deadline - 'deadline <insert task name> /by <dd/mm/yyyy> <HHmm>'\n\n";
        String eventHelp = "event - 'event <insert task name> /at <time of event>'\n\n";
        String doneHelp = "done - 'done <insert task number>'\n\n";
        String deleteHelp = "delete - 'delete <insert task number'\n\n";
        String findHelp = "find - 'find <insert search word>'\n\n";
        String listHelp = "list - Displays the tasks in your list\n\n";
        String byeHelp = "bye - Exit the program\n\n";
        String helpHelp = "help - Displays available commands\n\n";
        return helpIntro + todoHelp + deadlineHelp + eventHelp + doneHelp + deleteHelp + findHelp + listHelp + byeHelp
                + helpHelp;
    }

    /**
     * Returns an error message for the user.
     */
    public String invalidInput() {
        String response = "";
        try {
            throw new InputError("Invalid Input");
        } catch (InputError e) {
            response = "Here is the error boss. " + e.getMessage() + "\n" + "I'm not too sure what you meant.\n"
                    + "Try again with these keywords.\n" + "todo deadline event";
        }
        return response;
    }

    public String errorMessage(InputError e) {
        return "Here is the error boss. " + e.getMessage();
    }
}
