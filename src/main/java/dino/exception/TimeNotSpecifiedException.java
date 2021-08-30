package dino.exception;

public class TimeNotSpecifiedException extends DinoException {
    public TimeNotSpecifiedException(String type) {
        super("😛 Please specify the time of your " + type + "!");
    }
}
