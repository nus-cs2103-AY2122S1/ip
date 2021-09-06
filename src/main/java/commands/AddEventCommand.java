package commands;

import tasks.Event;

public class AddEventCommand extends AddTaskCommand {

    public AddEventCommand(String desc, Boolean isDone, String time) {
        super(new Event(desc, isDone, time));
    }

}
