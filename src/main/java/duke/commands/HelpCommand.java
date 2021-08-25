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
     *
     * @param des   the user input into the Duke chat-box.
     * @param tList the TaskList object used to keep track of all tasks.
     */
    @Override
    public void execute(String des, TaskList tList) {

        System.out.println("The possible commands are as follows:");
        System.out.println();

        System.out.println("1. bye  -------- exit the Duke chat-bot");
        System.out.println();

        System.out.println("2. list -------- list all tasks");
        System.out.println();

        System.out.println("3. done -------- Usage --> done x, where x is an integer.");
        System.out.println("               - Marks the corresponding task as completed");
        System.out.println();

        System.out.println("4. todo -------- Usage --> \"todo borrow book\"");
        System.out.println("               - Inputs the a ToDo task into the task list");
        System.out.println();

        System.out.println("5. deadline ---- Usage --> \"deadline submit essay /by Sunday \", "
                + "remember not to miss the \"/by\" symbol!");
        System.out.println("               - Inputs the an Deadline task into the task list");
        System.out.println();

        System.out.println("6. event ------- Usage --> \"event project meeting /at Mon 2-4pm \", "
                + "remember not to miss the \"/at\" symbol!");
        System.out.println("               - Inputs the an Event task into the task list");

        System.out.println("7. delete ------ Usage --> delete x, where x is an integer.");
        System.out.println("               - Deletes the corresponding task");
        System.out.println();
    }
}
