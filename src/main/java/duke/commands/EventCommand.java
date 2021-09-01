package duke.commands;

import duke.DukeException;
import duke.Event;
import duke.TaskList;

/**
 * Class that represents the command to add an event task.
 */
public class EventCommand extends Command {
    private String description;
    private String time;
    private Event newTask;

    /**
     * Constructor for EventCommand.
     *
     * @param input The user input.
     * @throws DukeException If the user input is in an invalid format.
     */
    public EventCommand(String input) throws DukeException {
        checkInput(input);
        description = getDescription(input);
        time = getTime(input);
        newTask = new Event(description, time);
    }

    private void checkInput(String input) throws DukeException {
        int inputLen = input.length();
        if (inputLen < 2) {
            throw new DukeException("OOPS!! The description of an event"
                    + " cannot be empty :(");
        } else if (!input.contains("/at")) {
            throw new DukeException("Something is missing...\n" + "Did you specify a time?");
        }
    }

    private String getDescription(String input) throws DukeException {
        String[] splitInput = input.split("/at");
        String descInput = splitInput[0].substring(6).strip();
        if (descInput.length() == 0) {
            throw new DukeException("Please specify a task description ><");
        } else {
            return descInput;
        }
    }

    private String getTime(String input) throws DukeException {
        String[] splitInput = input.split("/at");
        String timeInput = splitInput[1].strip(); 
        if (timeInput.length() == 0) {
            throw new DukeException("Please specify a time for this task");
        } else {
            return timeInput;
        }
    }

    /**
     * Executes the command.
     *
     * @param taskList The list of tasks to add an event task to.
     * @return Response to be displayed in the GUI.
     */
    @Override
    public String execute(TaskList taskList) {
        taskList.add(newTask);
        return "Just added:\n" + newTask.toString()
                + "\nYou currently have " + taskList.getLength() + " tasks in the list.";
    }
}
