package exception;

public class RandomDescription extends DukeException{

    public RandomDescription(String message) {
        super(message);
    }

    @Override
    public String output_error() {
        return "OOPS!! I'm sorry, but I don't know what that means ;-(";
    }
}
