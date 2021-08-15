public class InvalidTaskNumberException extends DukeException {

    public InvalidTaskNumberException(int n) {
        super(String.format("Sorry, this task index is invalid! Please input an integer between 0-%d", n));
    }

}
