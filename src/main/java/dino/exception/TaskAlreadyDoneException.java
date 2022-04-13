package dino.exception;

public class TaskAlreadyDoneException extends DinoException {
    public TaskAlreadyDoneException() {
        super("Hey you have already done this task!");
    }
}
