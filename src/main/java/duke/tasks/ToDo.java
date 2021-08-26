package duke.tasks;

import duke.exceptions.TodoException;
public class ToDo extends Task {
    public ToDo(String description) throws TodoException {
        super(description);
        if(description.equals("todo")) {
            throw new TodoException();
        }
    }

    public String getInfo() {
        return getDescription();
    }

    public String getType() {
        return "T";
    };

    @Override
    public String storeTask() {
        return "T/" + getDone() + "/" + getDescription();
    };

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
