package dino.exception;

public class TaskNotFoundException extends DinoException{

    public TaskNotFoundException(String keyword) {
        super("🤨 Seems like there's no matching result for the keyword: " + keyword + "\n"
                + "Maybe try again with another keyword?");
    }
}
