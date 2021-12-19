package abyss.command;

import abyss.Abyss;

/**
 * Represents a command to list the existing tasks.
 */
public class ListCommand implements Command {
    protected ListCommand() {}

    /**
     * Executes the list command.
     *
     * @return Response from executing the list command.
     */
    public String execute() {
        String response = Abyss.getTaskManager().list();
        return response;
    }
}
