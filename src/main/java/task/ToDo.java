package task;

import duke.DukeException;

public class ToDo extends Task {

    public ToDo(String name) throws DukeException {
        super(name);
    }

    @Override
    public String saveTask() {
        return "todo|" + this.getName() + (this.isDone() ? "|1|" : "|0|") + this.saveTags();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
