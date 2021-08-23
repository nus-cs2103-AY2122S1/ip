public class Event extends TimedTask {

    Event(String name, String by, boolean isCompleted) throws DukeException {
        super(name, by, TaskType.D, isCompleted);
    }

    @Override
    protected String dateTimeString() {
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
