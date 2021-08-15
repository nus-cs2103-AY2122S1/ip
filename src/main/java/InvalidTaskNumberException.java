public class InvalidTaskNumberException extends DukeException {

    public InvalidTaskNumberException(int n) {
        super(n > 0
                ? String.format("Sorry, this task index is invalid! Please input an integer between 1-%d", n)
                : "You need to add tasks before you can mark them as completed!");
    }

}
