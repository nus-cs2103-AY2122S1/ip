package exception;

public class InvalidDescription extends DukeException {

    private String type;

    public InvalidDescription(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "Hey! I think you have missing fields needed to create the " + type;
    }
}
