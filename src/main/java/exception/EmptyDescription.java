package exception;
public class EmptyDescription extends DukeException{

    public EmptyDescription(String message) {
        super(message);
    }

    @Override
    public String output_error() {
        return "OOPS! The description of " + this.get_message() + " cannot be empty.";
    }

}
