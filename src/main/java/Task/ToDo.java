package Task;

import Duke.DukeException;

public class ToDo extends Task {
    public ToDo(String name) throws DukeException {
        super(name);
    }

    @Override
    public String saveTask(){
        return "TODO|" + this.getName() + (this.isDone() ? "|1" : "|0");
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
