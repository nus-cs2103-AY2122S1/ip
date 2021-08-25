package duke.task;

import duke.DukeException;

public class Event extends TimedTask {

    public Event(String name, String by, boolean isCompleted) throws DukeException {
        super(name, by, TaskType.E, isCompleted);
    }

    @Override
    protected String getDateTimeString() {
        return " (at: " + this.getDateTime() + ")";
    }

    @Override
    public Task updateName(String input) {
        try {
            return new Deadline(input, this.getDateTimeInternal(), this.getCompleted());
        } catch(DukeException e) {
            System.out.println(e.getMessage());
            return Task.emptyTask();
        }
    }

    @Override
    public Task complete() {
        try {
            return new Deadline(this.getName(), this.getDateTimeInternal(), true);
        } catch(DukeException e) {
            System.out.println(e.getMessage());
            return Task.emptyTask();
        }
    }
}
