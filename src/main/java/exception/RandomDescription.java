package exception;

<<<<<<< HEAD
public class RandomDescription extends DukeException{
=======
/**
 * Handle exception of random unrelated descriptions
 * Allows users to output error message
 */
public class RandomDescription extends DukeException {
>>>>>>> 7f3ded1 (Follow Coding Standard)

    public RandomDescription(String message) {
        super(message);
    }

    @Override
    public String output_error() {
        return "OOPS!! I'm sorry, but I don't know what that means ;-(";
    }
}
