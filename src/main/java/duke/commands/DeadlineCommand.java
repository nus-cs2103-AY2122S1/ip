package duke.commands;

import duke.Deadline;
import duke.DukeException;
import duke.TaskList;

/**
 * Class that represents the command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String deadline;
    private Deadline newTask;

    /**
     * Constructor for a DeadlineCommand.
     *
     * @param input The user input.
     * @throws DukeException If user input is in an invalid format.
     */
    public DeadlineCommand(String input) throws DukeException {
        checkInput(input);
        description = getDescription(input);
        deadline = getDeadline(input);
        newTask = new Deadline(description, deadline);
    }

    private void checkInput(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length < 2) {
            throw new DukeException("OOPS!! The description of a deadline"
                    + " cannot be empty :(");
        } else if (!input.contains("/by")) {
            throw new DukeException("Something is missing...\n" + "Did you specify a deadline?");
        }
    }

    private String getDescription(String input) throws DukeException {
        String[] splitInput = input.split("/by");
        String descInput = splitInput[0].substring(9).strip();
        if (descInput.length() == 0) {
            throw new DukeException("Please specify a task description ><");
        } else {
            assert descInput.length() > 0 : "Improper input length for deadline description";
            return descInput;
        }
    }

    private String getDeadline(String input) throws DukeException {
        String[] splitInput = input.split("/by");
        if (splitInput.length < 2) {
            throw new DukeException("Please specify a deadline for this task");
        } else {
            String deadlineInput = splitInput[1].strip();
            assert deadlineInput.length() > 0 : "Improper input length for deadline";
            return deadlineInput;
        }
    }

    /**
     * Executes the command.
     *
     * @param taskList The list of tasks to add a deadline task to.
     * @return Response to be displayed in the GUI.
     */
    @Override
    public String execute(TaskList taskList) {
        taskList.add(newTask);
        int listLen = taskList.getLength();
        assert listLen > 0 : "There should be > 0 tasks in the list after adding";
        return "Just added:\n" + newTask.toString()
                + "\nYou currently have " + listLen + " tasks in the list.";
    }
}
