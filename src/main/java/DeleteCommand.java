package main.java;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    protected String input;

    protected ArrayList<Task> list;

    DeleteCommand(String input, ArrayList<Task> list) {
        this.input = input;
        this.list = list;
    }

    public String reply() throws DukeException {
        try {

            int index = Integer.parseInt(input.substring(7)) - 1;
            Task removedTask = list.get(index);
            list.remove(index);

            return "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                    + "Noted. I've removed the following task:\n"
                    + (index + 1)
                    + ". "
                    + removedTask.getTaskState()
                    + "\n"
                    + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";

        } catch (NumberFormatException e) {
            throw new DukeException("It looks like you did not enter a valid integer for the \"delete\" command. Please try again!");
        }
    }
}
