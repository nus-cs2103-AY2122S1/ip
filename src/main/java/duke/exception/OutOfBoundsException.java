package duke.exception;

import duke.TaskList;

public class OutOfBoundsException extends DukeException {

    private final int index;
    private final int size;

    public OutOfBoundsException(int index, TaskList taskList) {
        this.index = index;
        this.size = taskList.getSize();
    }

    @Override
    public String getMessage() {
        return "Oh no! Index " + index + " is out of bounds!\n"
                + "You currently have " + size + " tasks in your list!";
    }
}
