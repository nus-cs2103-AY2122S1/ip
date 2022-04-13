package dino.exception;

public class TaskNotFoundException extends DinoException {

    public TaskNotFoundException() {
        super("Seems like there's no matching result for the keyword you searched\n"
                + "Maybe try again with another keyword?");
    }
}
