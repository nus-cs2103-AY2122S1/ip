package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Deadline;

public class DeadlineCommand extends Command {
    public DeadlineCommand() {
        setCommandString("deadline");
    }

    /**
     * Parses the user input for a name and a date,
     * then creates the deadline and adds it into the taskList
     *
     * @param input Full user input
     * @throws DukeException Any exception caught when executing this command
     */
    @Override
    public void parse(String input) throws DukeException {
        if (input.length() <= getCommandLength()) {
            throw new DukeException("Please input the deadline's name and date!");
        }

        String[] inputs = input.substring(getCommandLength()).split("/by");

        if (inputs.length < 2) {
            // /by not specified
            throw new DukeException("Please input when the deadline is to be done by!");
        } else if (inputs.length > 2) {
            // more than one /by
            throw new DukeException("Please input only one deadline!");
        }

        String name = inputs[0].strip();
        if (name.equals("")) {
            throw new DukeException("Please input the deadline's name!");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(inputs[1].strip());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input your date in the format YYYY-MM-DD");
        }

        Deadline deadline = new Deadline(name, date);
        Duke.taskList.addTask(deadline);
    }
}
