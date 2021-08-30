package dino.exception;

public class IndexNotSpecifiedException extends DinoException {
    public IndexNotSpecifiedException() {
        super("😕 Please specify the index of the task!");
    }
}
