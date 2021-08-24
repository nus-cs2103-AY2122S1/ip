package duke.commands;

import duke.exceptions.InvalidTimeStampException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.tasks.Event;
import duke.utils.Ui;

public class AddEventCommand extends AddTaskCommand {

    private String[] keyWords;
    public AddEventCommand(String[] keywords) throws InvalidTimeStampException {
        super(new Event(keywords[0], keywords[1]));
    }
}
