package exceptions;

public class DukeIndexOutOfBoundsException extends DukeException {
    public DukeIndexOutOfBoundsException(int index, int taskListSize) {
        super(String.format("Index %d is out of bounds of your task list size of %d.\nPlease make sure your index is at least 1.\nUse the list command to see task indices.", index + 1, taskListSize));
    }
}
