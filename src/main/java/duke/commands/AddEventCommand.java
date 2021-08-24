package duke.commands;

import duke.exceptions.InvalidTimeStampException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class AddEventCommand extends AddTaskCommand {

    public AddEventCommand(String[] keywords) throws InvalidTimeStampException {
        super(new Event(keywords[0], keywords[1]));
    }
}
