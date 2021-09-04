package duke.commands;

import duke.tasks.TaskList;

/**
 * HelpCommand class serves as an in-built user-guide that
 * furnishes the user with information on how to use and format all
 * the commands available.
 * * Contains methods that
 * * (i)    executes the HelpCommand
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    /**
     * Returns String object to inform user of all possible commands on Duke.
     *
     * @param des   User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return String representation of HelpCommand.
     */
    @Override
    public String execute(String des, TaskList tList) {
        checkValidDes(des);
        String result = "The possible commands are as follows:\n"
                + "\n"
                + "1. bye  -------- exit the Duke chat-bot\n"
                + "\n"
                + "2. list -------- list all tasks\n"
                + "\n"
                + "3. done -------- Usage --> done x, where x is an integer.\n"
                + "               - Marks the corresponding task as completed\n"
                + "\n"
                + "4. todo -------- Usage --> \"todo borrow book\"\n"
                + "               - Inputs a ToDo task into the task list\n"
                + "\n"
                + "5. deadline ---- Usage --> \"deadline submit essay /by 2021-06-03 23:59 \"\n"
                + "               - Remember not to miss the \"/by\" symbol!\n"
                + "               - Date should be input in YYYY-MM-DD format\n"
                + "               - Time that deadline is due by should be input in HH:MM format\n"
                + "               - Inputs a Deadline task into the task list\n"
                + "\n"
                + "6. event ------- Usage --> \"event project meeting /at 2021-06-03 09:00 13:00\"\n"
                + "               - Remember not to miss the \"/at\" symbol!\n"
                + "               - Date should be input in YYYY-MM-DD format\n"
                + "               - Start time and End time of event should be input in HH:MM format\n"
                + "               - Inputs an Event task into the task list\n"
                + "\n"
                + "7. doafter ----- Usage --> \"doafter complete homework /aft x\", where x is an integer.\n"
                + "               - Remember not to miss the \"/aft\" symbol!\n"
                + "               - Inputs a DoAfter task into the task list\n"
                + "\n"
                + "8. delete ------ Usage --> delete x, where x is an integer.\n"
                + "               - Deletes the corresponding task\n"
                + "\n"
                + "9. find -------- Usage --> find xxxxx\n"
                + "               "
                + "- Find a specific keyword, or a string of text and extract the relevant tasks from the TaskList\n"
                + "\n"
                + "10. help -------- list all possible commands available in Duke";
        return result;
    }
}
