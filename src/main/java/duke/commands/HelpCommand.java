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
     * execute() method in HelpCommand to add a Help task into the TaskList.
     * @param des   the user input into the Duke chat-box.
     * @param tList the TaskList object used to keep track of all tasks.
     * @return String object to describe execution of HelpCommand.
     */
    @Override
    public String execute(String des, TaskList tList) {
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
                + "               - Inputs the a ToDo task into the task list\n"
                + "\n"
                + "5. deadline ---- Usage --> \"deadline submit essay /by Sunday \"\n"
                + "               - Remember not to miss the \"/by\" symbol!\n"
                + "               - Inputs the an Deadline task into the task list\n"
                + "\n"
                + "6. event ------- Usage --> \"event project meeting /at Mon 2-4pm \"\n"
                + "               - Remember not to miss the \"/at\" symbol!\n"
                + "               - Inputs the an Event task into the task list\n"
                + "\n"
                + "7. delete ------ Usage --> delete x, where x is an integer.\n"
                + "               - Deletes the corresponding task\n"
                + "\n";
        return result;
    }
}
