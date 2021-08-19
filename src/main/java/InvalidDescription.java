public class InvalidDescription extends DukeException{
    InvalidDescription(String event) {
        super("☹ OOPS!!! The description of a " + event + " cannot be empty.");
    }
}