package duke.commands;

import duke.tasks.TaskList;

/**
 * ByeCommand class used to represent a Bye Command.
 * Contains methods that
 * (i)    executes the ByeCommand
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super("bye");
    }

    /**
     * execute() method in ByeCommand to print leaving message.
     *
     * @param des   the user input into the Duke chat-box.
     * @param tList the TaskList object used to keep track of all tasks.
     * @return String object to describe execution of ByeCommand.
     */
    @Override
    public String execute(String des, TaskList tList) {
        return "Bye. Hope to see you again soon!\n";
    }
}
