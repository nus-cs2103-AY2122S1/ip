package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Command {

    protected DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");

    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy, hh:mm a");

    /**
     * Displays the task added message according to the task input given.
     *
     * @param input The task input to be added to the list.
     * @param type  The type id. Todo: 1, Deadline: 2, Event: 3.
     * @param index The current index of the list.
     * @param time  The information regarding the task (In the format of "(By: ...)" or "(At: ...)", or "" for Todos)
     * @return The String sequence showing that the task has been added to the list.
     */
    public String addTask(String input, int type, int index, String time) {

        // Determine the string that displays the type of task
        String taskType;
        String prefix;
        if (type == 1) {
            taskType = "task (Todo)";
            prefix = "[T][ ]";
        } else if (type == 2) {
            taskType = "task (Deadline)";
            prefix = "[D][ ]";
        } else if (type == 3) {
            taskType = "task (Event)";
            prefix = "[E][ ]";
        } else {
            taskType = "";
            prefix = "";
        }

        // Return the message accordingly
        return "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Alright. I've added the following "
                + taskType
                + ":\n--> "
                + prefix
                + " "
                + input
                + (type == 2
                    ? "(By: " + LocalDateTime.parse(time, inputFormatter).format(outputFormatter) + ")"
                    : type == 3
                        ? "(At: " + LocalDateTime.parse(time, inputFormatter).format(outputFormatter) + ")"
                        : "")
                + "\n\n"
                + "You currently have "
                + (index + 1)
                + (index == 0 ? " task" : " tasks")
                + " in the list.\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
    }

    /**
     * An abstract function that creates and returns the reply according to the user input.
     *
     * @return A response corresponding to the user input / command.
     */
    abstract String reply() throws DukeException;
}