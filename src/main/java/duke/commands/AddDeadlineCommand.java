package duke.commands;

import duke.exceptions.InvalidTimeStampException;

import duke.tasks.Deadline;
import duke.tasks.Task;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class AddDeadlineCommand extends AddTaskCommand {

    public AddDeadlineCommand(String[] keywords) throws InvalidTimeStampException {
        super(new Deadline(keywords[0], keywords[1]));
    }
}
