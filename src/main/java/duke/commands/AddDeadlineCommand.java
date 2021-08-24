package duke.commands;

import duke.exceptions.InvalidTimeStampException;

import duke.tasks.Deadline;
import duke.tasks.Task;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class AddDeadlineCommand extends AddTaskCommand {

    /**
     * AddDeadlineCommand constructor.
     *
     * @param keywords Array containing the deadline details and when it should be done by.
     * @throws InvalidTimeStampException Exception thrown when an invalid deadline is given.
     */
    public AddDeadlineCommand(String[] keywords) throws InvalidTimeStampException {
        super(new Deadline(keywords[0], keywords[1]));
    }
}
